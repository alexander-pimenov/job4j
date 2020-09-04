package ru.job4j.sql;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * В этом классе показаны примеры подключения к БД с помощью JDBC,
 * Чтение данных из БД,
 * Добавление данных,
 * Изменение данных, Удаление данных.
 */

public class SQLStorage {
    private static final Logger LOG = LoggerFactory.getLogger(SQLStorage.class);

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/tracker";
        String username = "postgres";
        String password = "qwerty";

        //А можно записать одной строкой, только одним url, в котором будет и DB и name и password:
        //String url = "jdbc:postgresql://localhost:5432/tracker?user=postgres&password=qwerty&ssl=true";

        Connection conn = null;

        System.out.println("#### Пример работы со Statement ####\r\n");
        try {
            //Создаем объект Connection, т.е. подключились к БД.
            //С помощью connection мы осуществляем всю работу с БД.
            conn = DriverManager.getConnection(url, username, password);

            //Рассмотрим как делать запросы к БД.
            //Создаем объект класса Statement.
            //Это статический запрос к БД.
            Statement st = conn.createStatement();

            //Объект ResultSet это итератор, а не коллекция,
            //который помагает проходиться по строкам таблицы.
            ResultSet rs = st.executeQuery("SELECT * FROM product");
//            while (rs.next()) {
//                System.out.print("Column 'name' returned: ");
//                //Всегда используй указание на имя колонки, а не индекс колонки
//                //чтобы избежать ошибок:
//                System.out.println(rs.getString("name"));
//                System.out.println("------------------------------");
//                System.out.println(String.format("%s, %d рубл.", rs.getString("name"),
//                        rs.getInt("price")));
//                System.out.println("==============================");
//            }

            while (rs.next()) {
                //Всегда используй указание на имя колонки, а не индекс колонки
                //чтобы избежать ошибок:
                System.out.println(String.format("id: %d, name: %s, категория: %d, срок годности: %s, цена: %d рубл.",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("type_id"),
                        rs.getDate("expired_date"),
                        rs.getInt("price")
                        )
                );
                System.out.println("==============================");
            }
            //Обязательно все ресурсы нужно закрывать.
            rs.close();
            st.close();
        } catch (Exception e) {
            /*Не забываем в логе писать и Message и StackTrace
             * Это правильно и удобно видна вся информация, если появилась
             * ошибка - LOG.error(e.getMessage(), e);*/
            LOG.error(e.getMessage(), e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    /*Не забываем в логе писать и Message и StackTrace
                     * Это правильно и удобно видна вся информация, если появилась
                     * ошибка - LOG.error(e.getMessage(), e);*/
                    LOG.error(e.getMessage(), e);
                }
            }
        }

        System.out.println("\n#### Пример работы с PreparedStatement ####\r\n");

        /*Рассмотрим пример работы с динамическими запросами к БД.
         * Т.е. будем использовать prepareStatement*/
        try {
            conn = DriverManager.getConnection(url, username, password);

            //Работаем с PreparedStatement, который позволяет ставлять параметры
            //через set__(), т.е. некий валидатор.
            PreparedStatement st = conn.prepareStatement("SELECT * FROM product as p WHERE p.id IN (? , ?, ?)");

            //Устанавливаем параметры под знаками ?, их индексация начинается с 1
            st.setInt(1, 3);
            st.setInt(2, 8);
            st.setInt(3, 11);

            //Выполним запрос к БД - executeQuery
            //Объект ResultSet это итератор, а не коллекция,
            //который помагает проходиться по строкам таблицы.
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                System.out.print("Column 'name' returned: ");
                //Всегда используй указание на имя колонки, а не индекс колонки
                //чтобы избежать ошибок:
                System.out.println(rs.getString("name"));
                System.out.println("------------------------------");
                System.out.println(String.format("%s, %d рубл.", rs.getString("name"),
                        rs.getInt("price")));
                System.out.println("==============================");
            }
            //Обязательно все ресурсы нужно закрывать.
            rs.close();
            st.close();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }

        System.out.println("\n#### Пример работы с PreparedStatement, Добавление данных. ####\r\n");
        try {
            conn = DriverManager.getConnection(url, username, password);
            //Когда мы добавим данные в таблицу product нам хорошо бы получить назад
            //сгенерированное id, чтобы потом можно было работать с ним.
            //Поэтому добавим специальный ключ
            PreparedStatement st = conn.prepareStatement("INSERT INTO product (name, type_id, expired_date, price) " +
                    "values (?,?,?,?) ", Statement.RETURN_GENERATED_KEYS);
            //Установим параметры
            st.setString(1, "Картофель");
            st.setInt(2, 5);
//            st.setDate(3, new Date(2020, 10, 25)); //устаревший способ, не верно выводит дату. Не использовать!!!
//            st.setDate(3, new Date(System.currentTimeMillis())); //вставляем текущую дату
//            st.setDate(3, getCurrentDate()); //вставляем текущую дату
            st.setDate(3, getOnlyDate("2020-12-25")); //вставляем любую дату
            st.setInt(4, 27);

            //Выполним запрос к БД - executeUpdate
            //не executeQuery(), а executeUpdate()
            st.executeUpdate();
            ResultSet generatedKeys = st.getGeneratedKeys();
            if (generatedKeys.next()) {
                System.out.println("Значение id Картофеля: " + generatedKeys.getInt("id"));
            }

            //Выведем все записи с name Картофель
            final PreparedStatement preparedStatement = conn.prepareStatement("select * from product as p WHERE p.name = ? ");
            preparedStatement.setString(1, "Картофель");
            final ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(String.format("%s, цена - %d рубл, %s", resultSet.getString("name"), resultSet.getInt("price"),
                        resultSet.getDate("expired_date")));
            }

            //Обязательно все ресурсы нужно закрывать.
            generatedKeys.close();
            preparedStatement.close();
            st.close();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }

        System.out.println("\n#### Пример работы с PreparedStatement, Обновление данных. ####\r\n");
        try {
            conn = DriverManager.getConnection(url, username, password);
            //Когда мы добавим данные в таблицу product нам хорошо бы получить назад
            //сгенерированное id, чтобы потом можно было работать с ним.
            //Поэтому добавим специальный ключ
            PreparedStatement st = conn.prepareStatement("UPDATE product SET price=? WHERE id=?");
            //Установим параметры
            st.setInt(1, 29); //ставим новую цену
            st.setInt(2, 26); //объект с id = 26

            //Выполним запрос к БД - executeUpdate
            st.executeUpdate();

            //Изменения проверим в БД через pgAdmin

            //Обязательно все ресурсы нужно закрывать.
            st.close();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }

        System.out.println("\n#### Пример работы с PreparedStatement, Удаление данных. ####\r\n");
        try {
            conn = DriverManager.getConnection(url, username, password);
            //Когда мы добавим данные в таблицу product нам хорошо бы получить назад
            //сгенерированное id, чтобы потом можно было работать с ним.
            //Поэтому добавим специальный ключ
            PreparedStatement st = conn.prepareStatement("DELETE FROM product WHERE id=?");
            //Установим параметры
            st.setInt(1, 31); //удалим элемент с индексом 28

            //Выполним запрос к БД - executeUpdate
            st.executeUpdate();

            //Изменения проверим в БД через pgAdmin

            //Обязательно все ресурсы нужно закрывать.
            st.close();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
    }

    /*Метод возвращающий java.sql.Date и его вставляем в таблицу БД.
     * java.util.Date и java.sql.Date не нужно путать.
     * Передаем текущую дату.*/
    private static Date getCurrentDate() {
        java.util.Date today = new java.util.Date();
        return new Date(today.getTime());
    }

    /*Метод возвращающий java.sql.Date и его вставляем в таблицу БД.
     * java.util.Date и java.sql.Date не нужно путать.
     * Передаем не текущую дату, а какую нужно в формате "yyyy-MM-dd"*/
    private static Date getOnlyDate(String strDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = null;
        Date sqlDate = null;
        try {
            if (strDate != null) {
                date = sdf.parse(strDate);
                sqlDate = new Date(date.getTime());
            } else {
                //Если дата не введена, то передадим текущую дату
                sqlDate = new Date(System.currentTimeMillis());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sqlDate;
    }
}
