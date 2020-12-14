package ru.job4j.synch.nosharedresources;

/**
 * В классе UserCache нити будут работать с локальным объект user.
 * Изменение этого объекта не влечет изменений в самом кеше.
 * Т.к. при вызове User.of() будет создаваться новый user.
 * Чтобы каждый поток работал со своей копией, то в кеш нужно
 * добавлять копию объекта и возвращать копию.
 * Методы add и findById работают с копиями объекта User.
 * Метод findAll() тоже вернет копию списка имен объектов users.
 */
public class ShareNotSafe {
    public static void main(String[] args) throws InterruptedException {
        UserCache cache = new UserCache();
        User user = User.of("Name");
        User user2 = User.of("Name2");
        User user3 = User.of("Name3");
        cache.add(user);
        cache.add(user2);
        cache.add(user3);
        /*В этом случае нить first работает с локальным объект user.
         * Изменение этого объекта не влечет изменений user в самом кеше.*/
        Thread first = new Thread(
                () -> {
                    user.setName("reName");
                    System.out.println(cache.findAll());

                }
        );
        /*В этом случае нить second работает с локальным объект user.
         * Изменение этого объекта не влечет изменений user в самом кеше.*/
        Thread second = new Thread(
                () -> {
                    user.setName("NameRename");
                    System.out.println(cache.findAll());
                }
        );
        first.start();
        Thread.sleep(100);
        second.start();
        Thread.sleep(100);
        first.join();
        second.join();
        System.out.println(cache.findById(1).getName());
        System.out.println(cache.findAll());
    }
}
