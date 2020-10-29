package ru.job4j.gc;

public class GCDemo {
    private static final long KB = 1000; //коэффициент килло
    private static final long MB = KB * KB; //коэффициент мега

    /**
     * Каждое приложение Java имеет единственный экземпляр
     * класса {@code Runtime}, который позволяет приложению
     * взаимодействовать со средой, в которой оно выполняется.
     * Текущее время выполнения можно получить из метода {@code getRuntime}.
     * Приложение не может создать собственный экземпляр этого класса.
     */
    private static final Runtime ENVIRONMENT = Runtime.getRuntime();

    /**
     * В методе выводятся некоторые характеристики памяти:
     * - freeMemory(), возвращает количество свободной памяти в байтах
     * - totalMemory(), возвращает общее количество памяти, которое может быть использовано
     * - maxMemory(), возвращает максимальное количество памяти, которое может быть использовано
     * <p>
     * Размер хипа для нашей программы можно задать с помощью ключей -XmxNm -XmsNm соответственно
     * максимальный и начальный размеры хипа.
     * Зададим для нашей программы размер хипа 4 мегабайта.
     * Для этого войдем в configuration и введем настройки
     * в поле VM options: -Xmx4m -Xms4m
     */
    public static void info() {
        final long freeMemory = ENVIRONMENT.freeMemory();
        final long totalMemory = ENVIRONMENT.totalMemory();
        final long maxMemory = ENVIRONMENT.maxMemory();
        System.out.println("=== Environment state ===");
        System.out.printf("Free: %d%n", freeMemory / MB);
        System.out.printf("Total: %d%n", totalMemory / MB);
        System.out.printf("Max: %d%n", maxMemory / MB);
    }

    public static void main(String[] args) throws InterruptedException {
        info();

        /*создаем в цикле 10000 объектов, при следующем вызове 1000
        и далее 10 и посмотрим, что выведется в консоль*/
        for (int i = 0; i < 10; i++) {
            new Person(i, "N" + i);
        }

        /*вызываем сборку мусора*/
        System.gc();
        info();
    }
}
