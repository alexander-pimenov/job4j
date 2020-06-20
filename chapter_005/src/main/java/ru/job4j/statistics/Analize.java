package ru.job4j.statistics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Класс Analize содержит метод, возвращающий статистику об изменении коллекции
 * объектов User.
 */
public class Analize {
    public Info diff(List<User> previous, List<User> current) {
        Info info = new Info();
        Map<Integer, User> map = new HashMap<>();

        for (User user : current) {
            map.put(user.id, user);
        }
        for (User user : previous) {
            if (!map.containsKey(user.id)) {
                info.deleted++;
            }
            User newUser = map.get(user.id);
            if (newUser != null && !newUser.equals(user)) {
                info.changed++;
            }
            info.added = current.size() - previous.size() + info.deleted;
        }
        return info;
    }

    public static class User {
        private int id;
        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            User user = (User) o;
            return id == user.id &&
                    Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }

        @Override
        public String toString() {
            return "User: " +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '.';
        }
    }

    /**
     * Класс Info содержит в себе статистику.
     * Поля добавление, изменение, удаление.
     */
    public static class Info {

        private int added;
        private int changed;
        private int deleted;

        public Info() {
        }

        public Info(int added, int changed, int deleted) {
            this.added = added;
            this.changed = changed;
            this.deleted = deleted;
        }

        public int getAdded() {
            return added;
        }

        public int getChanged() {
            return changed;
        }

        public int getDeleted() {
            return deleted;
        }
    }
}
