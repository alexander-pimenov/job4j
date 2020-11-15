package ru.job4j.gc;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.List;

public class CachePhantomRef {

    public static void main(String[] args) throws InterruptedException {
        User user = new User("TEST-1"); //strong reference

        ReferenceQueue<User> referenceQueue = new ReferenceQueue<>(); //
        PhantomReference<User> phantomReference = new PhantomReference<>(user, referenceQueue); //

        System.out.println(phantomReference); //выводим данные по phantom-ссылке

        user = null; //обнуляем strong-ссылку
        System.out.println(user); //выводим данные по strong-ссылке

//        System.gc(); //вызываем GC принудительно

        //Лучше вызовем GC через утилитный класс GcUtils
        GcUtils.fullFinalization();//это гарантирует, что GC точно выполнит полный цикл очистки и завершения.

        /*------Это блок для какой то работы-------*/
        Thread.sleep(2000);
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 10_000; i++) {
            userList.add(new User(i, "user-" + i));
        }
        /*------------------------------------------*/

        System.out.println(phantomReference.get()); //выводим данные по phantom-ссылке
        System.out.println(referenceQueue.poll()); //выводим данные по ссылке в очереди

        /*
         * Если GC вызвать, то результат
         * работы будет таким:
         *
         * java.lang.ref.PhantomReference@7eda2dbb
         * null
         * Deleting (finalize) object User: id=0, name=TEST-1
         * null
         * java.lang.ref.PhantomReference@7eda2dbb
         *
         * Т.е. говорит о том что объект User("TEST-1") удаляется из heap
         * но на него есть ссылка PhantomReference, которую можно достать только
         * из очереди referenceQueue.
         * Когда объект-референт PhantomReference, удаляется из памяти, сборщик
         * мусора ставит phantomReference в очередь referenceQueue, и мы можем
         * поросить эту ссылку из этой очереди.
         * Просто ссылка с PhantomReference через get() будет выдавать NULL,
         * для того чтобы мы не смогли воскресить почти удаленный объект.
         *
         * */
    }
}
