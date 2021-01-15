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
 * Ленивые с помощью Single checked locking:
 *
 * Инициализация и проверка экземпляра происходит внутри критической секции.
 * Этот шаблон деградирует производительность.
 * Использовать этот шаблон не рекомендуется!!!!!!!
 *
 * Вывод.
 * Если у вас нет необходимости в ленивой загрузке, используются шаблоны из
 * первой группы. Например, инициализация кеша или базы данных.
 * Если в приложении есть затратные ресурсы нужно использовать шаблоны с
 * ленивой загрузкой.
 * Здесь можно использовать только один шаблон - это Holder. Другие шаблоны
 * будут отрицательно влиять на производительность системы.
 *
 * */
public class TrackerSingle3 {
    private static TrackerSingle3 INSTANCE;

    private TrackerSingle3() {
    }

    public static synchronized TrackerSingle3 getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TrackerSingle3();
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        TrackerSingle3 tracker = TrackerSingle3.getInstance();
    }
}
