package ru.job4j.trackerSql;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Connection, which rollback all commits.
 * It is used for integration test.
 * <p>
 * В JDK 1.8 ввели новый класс Proxy. Он позволяет создаеть объект на
 * основании интерфейса.
 * <p>
 * Сделаем фабричный метод, который создает Connection, в котором метод
 * close работает с вызовом rollback.
 */
public class ConnectionRollback {
    /**
     * Create connection with autocommit=false mode and rollback call, when connection is closed.
     *
     * @param connection connection.
     * @return Connection object.
     * @throws SQLException possible exception.
     */
    public static Connection create(Connection connection) throws SQLException {
        connection.setAutoCommit(false);
        return (Connection) Proxy.newProxyInstance(
                ConnectionRollback.class.getClassLoader(),
                new Class[]{Connection.class},
                (proxy, method, args) -> {
                    Object rsl = null;
                    if ("close".equals(method.getName())) {
                        connection.rollback();
                        connection.close();
                    } else {
                        rsl = method.invoke(connection, args);
                    }
                    return rsl;
                }
        );
    }
}
