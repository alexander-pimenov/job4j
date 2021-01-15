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
 * Энергичные с помощью поля final:
 *
 * Вывод.
 * Если у вас нет необходимости в ленивой загрузке, используются шаблоны из
 * первой группы. Например, инициализация кеша или базы данных.
 * Если в приложении есть затратные ресурсы нужно использовать шаблоны с
 * ленивой загрузкой.
 * Здесь можно использовать только один шаблон - это Holder. Другие шаблоны
 * будут отрицательно влиять на производительность системы.
 * */
public class TrackerSingle2 {
    private static final TrackerSingle2 INSTANCE = new TrackerSingle2();

    private TrackerSingle2() {
    }

    public static TrackerSingle2 getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        TrackerSingle2 tracker = TrackerSingle2.getInstance();
    }
}
