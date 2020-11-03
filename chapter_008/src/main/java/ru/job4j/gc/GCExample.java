package ru.job4j.gc;

/**
 * Пример программы, которую запускаем с разными настройками для GC, устанавливаем их
 * поочереди в "VM options".
 * Чтобы выводить некую информацию о GC в VM options прописал команду: -verbose:gc
 * Мониторим состояние памяти с помощью утилитной программы MemoryUtil и
 * ее метода printUsage(true).
 * <p>
 * 1) запуск программы, когда все циклы закомментированны (калибровка)
 * 2) задаем размер памяти (heap) 4 mB (максимум и начальная)
 * * -Xmx4m -Xms4m
 * 3) запуск программы с установками по умолчанию
 * и потом устанавливаем такие параметры -Xmx12m -Xms3m -Xmn1m -XX:+UseG1GC
 * 4) вызываем Serial Collector -XX:+UseSerialGC
 * -Xmx12m -Xms3m -Xmn1m -XX:+UseSerialGC
 * 5) вызываем Parallel Collector -XX:+UseParallelGC
 * -Xmx12m -Xms3m -Xmn1m -XX:+UseParallelGC
 * 6) вызываем Parallel Compacting Collector -XX:+UseParallelOldGC
 * -Xmx12m -Xms3m -Xmn1m -XX:+UseParallelOldGC
 * 7) вызываем Concurrent Mark-Sweep (CMS) Collector -XX:+UseConcMarkSweepGC
 * -Xmx12m -Xms3m -Xmn1m -XX:+UseConcMarkSweepGC
 * <p>
 * -XX:+PrintGCDetails : Включает расширенный вывод информации о сборках мусора.
 * -Xlog:gc* : -XX:+PrintGCDetails is deprecated. Will use -Xlog:gc* instead.
 * <p>
 * -XX:+PrintFlagsFinal : При старте приложения выводит в stdout значения всех опций,
 * заданных явно или установленных самой JVM. Сюда же
 * попадают опции, относящиеся к сборке мусора. Часто
 * бывает полезно посмотреть на присвоенные им значения.
 */
public class GCExample {

    private static final long KB = 1024; //коэффициент килло
    private static final long MB = KB * KB; //коэффициент мега
    private static final Runtime ENVIRONMENT = Runtime.getRuntime();

    public static void main(String[] args) {
        MemoryUtil.printUsage(true);

        long start = System.currentTimeMillis();
//        infoInMB();
//        infoInBytes();
//        System.out.println(new User(1, "name" + 1));
//        System.out.println(new User(2, "name" + 2));
//        System.out.println("Создаются объекты ...");

        for (int i = 0; i < 10000; i++) {
            new User(i, "name" + i);
////            System.out.println(new User(i, "name" + i));
////            System.out.println(new UserEmpty());
        }
//        System.out.println("Объекты созданы.");
        System.gc();
//        infoInMB();
//        infoInBytes();
        MemoryUtil.printUsage(true);
        System.out.println("Lead time: " + (System.currentTimeMillis() - start));
    }

    /**
     * В методе выводятся некоторые характеристики памяти переведенные в МегаБайты:
     * - freeMemory(), возвращает количество свободной памяти
     * - totalMemory(), возвращает общее количество памяти, которое может быть использовано
     * - maxMemory(), возвращает максимальное количество памяти, которое может быть использовано
     * - usedMemory(), возвращает количество использованной памяти
     */
    private static void infoInMB() {
        long totalMemory = ENVIRONMENT.totalMemory();
        long maxMemory = ENVIRONMENT.maxMemory();
        long freeMemory = ENVIRONMENT.freeMemory();
        long usedMemory = totalMemory - freeMemory;
        System.out.println("=== Memory state [MByte]===");
        System.out.printf("Used memory: %d Mb%n", usedMemory / MB);
        System.out.printf("Free memory: %d Mb%n", freeMemory / MB);
        System.out.printf("Total memory: %d Mb%n", totalMemory / MB);
        System.out.printf("Max memory: %d Mb%n", maxMemory / MB);
        System.out.println("======= end of block =======");
    }

    /**
     * В методе выводятся некоторые характеристики памяти (в Байтах):
     * - freeMemory(), возвращает количество свободной памяти
     * - totalMemory(), возвращает общее количество памяти, которое может быть использовано
     * - maxMemory(), возвращает максимальное количество памяти, которое может быть использовано
     * - usedMemory(), возвращает количество использованной памяти
     */
    private static void infoInBytes() {
        long totalMemory = ENVIRONMENT.totalMemory();
        long maxMemory = ENVIRONMENT.maxMemory();
        long freeMemory = ENVIRONMENT.freeMemory();
        long usedMemory = totalMemory - freeMemory;
        System.out.println("=== Memory state [Byte]===");
        System.out.printf("Used memory: %d B%n", usedMemory);
        System.out.printf("Free memory: %d B%n", freeMemory);
        System.out.printf("Total memory: %d B%n", totalMemory);
        System.out.printf("Max memory: %d B%n", maxMemory);
        System.out.println("======= end of block =======");
    }
}
