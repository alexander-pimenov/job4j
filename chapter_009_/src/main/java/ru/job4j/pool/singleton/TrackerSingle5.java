package ru.job4j.pool.singleton;

/*
 * При реализации шаблона Singleton необходимо учитывать проблему с visibility (Видимость).
 * Эту проблему можно решать двумя способами используя volatile или сразу
 * публикуя объект через final.
 * Многопоточные реализации аналогичны не многопоточным.
 * Их можно разделить на две группы:
 * 1. энергичные
 * 2. ленивые
 *
 * Ленивые с помощью Holder:
 *
 * Его работа стабильна и не влияет не производительность системы !!!!
 *
 * Вывод.
 * Если у вас нет необходимости в ленивой загрузке, используются шаблоны из
 * первой группы. Например, инициализация кеша или базы данных.
 * Если в приложении есть затратные ресурсы нужно использовать шаблоны с
 * ленивой загрузкой.
 * Здесь можно использовать только один шаблон - это Holder. Другие шаблоны
 * будут отрицательно влиять на производительность системы.
 * */
public class TrackerSingle5 {
    private TrackerSingle5() {
    }

    public static TrackerSingle5 getInstance() {
        return Holder.INSTANCE;
    }

    private static final class Holder {
        private static final TrackerSingle5 INSTANCE = new TrackerSingle5();
    }

    public static void main(String[] args) {
        TrackerSingle5 tracker = TrackerSingle5.getInstance();
    }
}
