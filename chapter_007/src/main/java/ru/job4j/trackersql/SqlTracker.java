package ru.job4j.trackersql;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Этот класс будет подключаться к базе данных,
 * создавать в ней записи, редактировать, читать и удалять.
 * <p>
 * Памятка:
 * Для выполнения запроса PreparedStatement имеет три метода:
 * <p>
 * boolean execute(): выполняет любую SQL-команду
 * <p>
 * ResultSet executeQuery(): выполняет команду SELECT, которая
 * возвращает данные в виде ResultSet
 * <p>
 * int executeUpdate(): выполняет такие SQL-команды, как INSERT,
 * UPDATE, DELETE, CREATE и возвращает количество измененных строк
 * <p>
 * В отличие от методов Statement эти методы не принимают SQL-выражение.
 */

public class SqlTracker implements Store {
    private static final Logger LOG = LogManager.getLogger(SqlTracker.class.getName());
    //Объект Connection для соединения с БД
    private Connection cn;

    //Создадим отдельно запросы к БД
    private static final String CREATE_TABLE_ITEM = "CREATE TABLE IF NOT EXISTS item "
            + "(id serial primary key,"
            + "name varchar(255),"
            + "description text);";
    private static final String QUERY_INSERT = "INSERT INTO item (name, description) values (?,?);";
    private static final String QUERY_REPLACE = "UPDATE item SET name = ?, description = ? WHERE id = ?;";
    private static final String QUERY_DELETE_BY_ID = "DELETE FROM item WHERE id = ?;";
    private static final String QUERY_FIND_ALL = "SELECT * FROM item;";
    private static final String QUERY_FIND_BY_NAME = "SELECT * FROM item WHERE name = ?;";
    private static final String QUERY_FIND_BY_ID = "SELECT * FROM item WHERE id = ?;";

    //Вынося Connection в конструктор, позволит нам создать его вне класса SqlTracker.
    public SqlTracker(Connection cn) {
        this.cn = cn;
        createTableItem();

    }

    public SqlTracker() {
        init();
        createTableItem();

    }

    /*Для считывания файлов используем ClassLoader*/

    /**
     * Инициализация подключения с настройками в файле
     * ресурсов "app.properties"
     */
    private void init() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            if (in != null) {
                config.load(in);
                Class.forName(config.getProperty("driver-class-name"));
                cn = DriverManager.getConnection(
                        config.getProperty("url"),
                        config.getProperty("username"),
                        config.getProperty("password")
                );
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
//            throw new IllegalStateException(e);
        }
    }

    private void createTableItem() {
        try (PreparedStatement preparedStatement = cn.prepareStatement(CREATE_TABLE_ITEM)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Метод реализующий добавление заявки в БД
     *
     * @param item новая заявка
     * @return новая заявка
     */
    @Override
    public Item add(Item item) {
        try (PreparedStatement preparedStatement = cn.prepareStatement(
                QUERY_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, item.getName());
            preparedStatement.setString(2, item.getDesc());

            preparedStatement.executeUpdate();

            final ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                item.setId(String.valueOf(generatedKeys.getInt("id")));
                //System.out.println(generatedKeys.getInt("id"));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return item;
    }

    /**
     * Метод реализующий редактирование заявки.
     * находим заявку по её ID и записываем новую заявку.
     *
     * @param id   ID заявки
     * @param item Обновленная заявка
     * @return результат выполнения метода
     */
    @Override
    public boolean replace(String id, Item item) {
        boolean rsl = false;
        try (PreparedStatement preparedStatement = cn.prepareStatement(
                QUERY_REPLACE)) {
            preparedStatement.setString(1, item.getName());
            preparedStatement.setString(2, item.getDesc());
            preparedStatement.setInt(3, Integer.parseInt(id));
            final int i = preparedStatement.executeUpdate();
            if (i == 1) {
                rsl = true;
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return rsl;
    }

    /**
     * Метод реализующий удаление заявки по её ID.
     *
     * @param id ID заявки
     * @return результат выполнения метода
     */
    @Override
    public boolean delete(String id) {
        boolean rsl = false;
        try (PreparedStatement preparedStatement = cn.prepareStatement(
                QUERY_DELETE_BY_ID)) {
            preparedStatement.setInt(1, Integer.parseInt(id));
            final int i = preparedStatement.executeUpdate();
            if (i == 1) {
                rsl = true;
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return rsl;
    }

    /**
     * Метод реализующий получения списка всех заявок.
     *
     * @return список заявок
     */
    @Override
    public List<Item> findAll() {
        List<Item> rsl = new ArrayList<>();
        try (Statement st = cn.createStatement()) {
            final ResultSet resultSet = st.executeQuery(QUERY_FIND_ALL);
            while (resultSet.next()) {
                rsl.add(new Item(String.valueOf(resultSet.getInt("id")),
                        resultSet.getString("name"),
                        resultSet.getString("description")));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return rsl;
    }

    /**
     * Метод реализующий получения списка заявок по имени.
     * Собирает все items, соответсвующие ключу keyName, в отдельный список
     *
     * @param keyName имя-ключ
     * @return сптсок найденных заявок
     */
    @Override
    public List<Item> findByName(String keyName) {
        List<Item> rsl = new ArrayList<>();
        try (PreparedStatement preparedStatement = cn.prepareStatement(
                QUERY_FIND_BY_NAME)) {
            preparedStatement.setString(1, keyName);
            final ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                rsl.add(new Item(String.valueOf(resultSet.getInt("id")),
                        resultSet.getString("name"),
                        resultSet.getString("description")));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return rsl;
    }

    /**
     * Метод реализующий поиск заявки по её ID.
     *
     * @param id ID заявки
     * @return найденная заявка
     */
    @Override
    public Item findById(String id) {
        Item rsl = null;
        try (PreparedStatement preparedStatement = cn.prepareStatement(
                QUERY_FIND_BY_ID)) {
            preparedStatement.setInt(1, Integer.parseInt(id));
            final ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                rsl = new Item(String.valueOf(resultSet.getInt("id")),
                        resultSet.getString("name"),
                        resultSet.getString("description"));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return rsl;
    }

    @Override
    public void close() {
        try {
            if (cn != null) {
                cn.close();
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
        Input validate = new ValidateInput(
                new ConsoleInput()
        );
        try (SqlTracker sqlTracker = new SqlTracker()) {
//            sqlTracker.init();
//            sqlTracker.createTableItem();
            new StartUI(validate, sqlTracker, System.out::println)
                    .init();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }
}
