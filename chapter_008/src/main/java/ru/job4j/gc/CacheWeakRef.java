package ru.job4j.gc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class CacheWeakRef {
//    private static final Logger log = LoggerFactory.getLogger(CacheWeakRef.class);

    public static void main(String[] args) throws InterruptedException {
        User user = new User("TEST");
        WeakReference<User> weakUser = new WeakReference<>(user); //создали слабую ссылку на user
        user = null; //обнуляем strong-ссылку

        System.gc(); //вызываем GC принудительно

        System.out.println(user); //выводим данные по strong-ссылке

        /*------Это блок для какой то работы-------*/
        Thread.sleep(3000);
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            userList.add(new User(i, "user-" + i));
        }
        /*------------------------------------------*/

        System.out.println(weakUser.get()); //выводим данные по weak-ссылке
        /*
         * Если GC не вызывать, то результат работы может быть таким:
         *
         * null
         * User: id=0, name=test
         *
         * Это говорит о том, что не смотря на то что сильная ссылка обнулена,
         * слабая ссылка осталась на объект User, т.к. GC еще не был вызван.
         * А если GC отработает, то результат будет:
         *
         * null
         * null
         *
         * Если GC вызвать, то результат работы может быть таким:
         *
         * null
         * Deleting (finalize) object User: id=0, name=test
         * null
         *
         * Т.е. говорит о том что объект User("TEST") удаляется и никаких
         * ссылок на него не остается.
         *
         * */
    }
}
