package ru.job4j.gc;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class CacheSoftRef {
//    private static final Logger log = LoggerFactory.getLogger(CacheSoftRef.class);

    public static void main(String[] args) throws InterruptedException {
        //Создаем два объекта User
        User user1 = new User("TEST-1");
        User user2 = new User("TEST-2");
        WeakReference<User> weakUser = new WeakReference<>(user1); //создали слабую ссылку на user
        SoftReference<User> softUser = new SoftReference<>(user2); //создали мягкую ссылку на user
        user1 = null; //обнуляем strong-ссылку
        user2 = null; //обнуляем strong-ссылку

        System.gc(); //вызываем GC принудительно

        System.out.println(user1); //выводим данные по strong-ссылке
        System.out.println(user2); //выводим данные по strong-ссылке

        /*------Это блок для какой то работы-------*/
        Thread.sleep(2000);
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 10_000; i++) {
            userList.add(new User(i, "user-" + i));
        }
        /*------------------------------------------*/

        System.out.println(weakUser.get()); //выводим данные по weak-ссылке
        System.out.println(softUser.get()); //выводим данные по soft-ссылке
        /*
         * Если GC вызвать, но память heap еще не заканчивается, то результат
         * работы будет таким:
         *
         * null
         * null
         * Deleting (finalize) object User: id=0, name=TEST-1
         * null
         * User: id=0, name=TEST-2
         *
         * Т.е. говорит о том что объект User("TEST-2") НЕ удаляется из heap
         * пока на него есть ссылка SoftReference или память не заканчивается,
         * а объект с WeakReference user1 благополучно удаляется, как и в
         * примере CacheWeakRef
         *
         * Если же блоке для работы положить в список не 10_000 а 15_000_000
         * объектов, т.е. память будет на пределе заполнения и GC будет вызываться
         * не однократно, то получим такой результат:
         *
         * null
         * null
         * Deleting (finalize) object User: id=0, name=TEST-1
         * null
         * Deleting (finalize) object User: id=0, name=TEST-2
         * null
         *
         * т.е. объект с Soft-ссылкой на его всё же будет удален.
         *
         * */
    }
}
