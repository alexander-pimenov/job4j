package ru.job4j.trackersql;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class SqlTrackerTest {
//    private static final Logger LOG = LogManager.getLogger(SqlTracker.class.getName());

    public Connection init() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void whenCreateItem() throws SQLException {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            tracker.add(new Item("name", "desc"));
            assertThat(tracker.findByName("name").size(), is(1));
        }
    }

    @Test
    public void whenReplaceItem() throws SQLException {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item item1 = new Item("name1", "desc1");
            Item item2 = new Item("name2", "desc2");
            final Item addedItem = tracker.add(item1);
            System.out.println(addedItem.getId());
            tracker.replace(item1.getId(), item2);
            final Item foundById = tracker.findById(item1.getId());
            assertThat(foundById.getName(), is("name2"));
            assertThat(tracker.findByName("name2"), is(notNullValue()));
        }
    }

    @Test
    public void whenDeleteItem() throws SQLException {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item item = new Item("name", "desc");
            tracker.add(item);
            tracker.delete(item.getId());
            assertNull(tracker.findById(item.getId()));
        }
    }

    @Test
    public void whenFindById() throws SQLException {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item item = new Item("name", "desc");
            tracker.add(item);
            tracker.findById(item.getId());
            assertThat(tracker.findById(item.getId()), is(item));
        }
    }

    @Test
    public void whenFindByName() throws SQLException {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item item1 = new Item("name", "desc1");
            Item item2 = new Item("name2", "desc2");
            Item item3 = new Item("name", "desc3");
            tracker.add(item1);
            tracker.add(item2);
            tracker.add(item3);
            final List<Item> rsl = tracker.findByName("name");
            assertThat(rsl.size(), is(2));
            assertThat(rsl.get(0).getName(), Matchers.isOneOf("name"));
            assertThat(rsl.get(1).getName(), Matchers.isOneOf("name"));
        }
    }
}