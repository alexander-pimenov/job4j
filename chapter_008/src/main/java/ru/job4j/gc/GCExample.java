package ru.job4j.gc;

public class GCExample {

    private static final long KB = 1024; //коэффициент килло
    private static final long MB = KB * KB; //коэффициент мега
    private static final Runtime ENVIRONMENT = Runtime.getRuntime();

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        infoInMB();
        infoInBytes();
//        System.out.println(new User(1, "name" + 1));
//        System.out.println(new User(2, "name" + 2));

        for (int i = 0; i < 10; i++) {
//            System.out.println(new User(i, "name" + i));
            System.out.println(new UserEmpty());
        }

        infoInMB();
        infoInBytes();
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
