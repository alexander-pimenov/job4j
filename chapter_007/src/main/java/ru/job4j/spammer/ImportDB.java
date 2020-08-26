package ru.job4j.spammer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

/**
 * Класс переводящий данные из файла dump.txt в базу данных
 * spammer таблицу users.
 */
public class ImportDB {
    private static final String CREATE_TABLE_QUERY =
            "CREATE TABLE IF NOT EXISTS users(" +
                    "id serial primary key," +
                    "name varchar(255)," +
                    "email varchar(255));";
    private Properties cfg;
    private String dump;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            /* rd.lines().forEach(...); */
            rd.lines().forEach(line -> {
                String[] elements = line.split(";");
                System.out.println(elements[0]);
                System.out.println(elements[1]);
                users.add(new User(elements[0], elements[1]));
            });
        }
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("jdbc.driver"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password")
        )) {
            final Statement statement = cnt.createStatement();
            statement.execute(CREATE_TABLE_QUERY);

            for (User user : users) {
                try (PreparedStatement ps = cnt.prepareStatement("INSERT INTO users (name, email)" +
                        "values (?, ?);")) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                }
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }


    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
//        try (FileInputStream in = new FileInputStream("./app1.properties")) {
        try (FileInputStream in = new FileInputStream(
                Objects.requireNonNull(ImportDB.class.getClassLoader().getResource("app1.properties")).getFile())) {
            cfg.load(in);

        }
        ImportDB db = new ImportDB(cfg, (
                Objects.requireNonNull(ImportDB.class.getClassLoader().getResource("dump.txt")).getPath()));
        db.save(db.load());
    }
}
