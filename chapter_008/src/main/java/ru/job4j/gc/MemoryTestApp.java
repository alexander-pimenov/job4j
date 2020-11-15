package ru.job4j.gc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MemoryTestApp {
    //    private static final Logger log = LoggerFactory.getLogger(MemoryTestApp.class);
    private static final Runtime ENVIRONMENT = Runtime.getRuntime();
    private static final long KB = 1024; //коэффициент киллобайт
    private static final long MB = KB * KB; //коэффициент мегабайт

    public static void main(String[] args) {
        System.out.println("-=start=-");
        info();
        User user = new User("test");
        System.out.println(user);
        user = null;
        System.gc();
//        System.out.println(user); //объект будет null, т.е. удален с помощью GC
        info();
        System.out.println("-=finish=-");
    }

    /**
     * В методе выводятся некоторые характеристики памяти переведенные в МегаБайты:
     * - freeMemory(), возвращает количество свободной памяти
     * - totalMemory(), возвращает общее количество памяти, которое может быть использовано
     * - maxMemory(), возвращает максимальное количество памяти, которое может быть использовано
     * - usedMemory(), возвращает количество использованной памяти
     */
    public static void info() {
        long totalMemory = ENVIRONMENT.totalMemory();
        long maxMemory = ENVIRONMENT.maxMemory();
        long freeMemory = ENVIRONMENT.freeMemory();
        long usedMemory = totalMemory - freeMemory;
        System.out.println("=== Heap utilization statistics [MByte]===");
        System.out.printf("Used memory: %d Mb%n", usedMemory / MB);
        System.out.printf("Free memory: %d Mb%n", freeMemory / MB);
        System.out.printf("Total memory: %d Mb%n", totalMemory / MB);
        System.out.printf("Max memory: %d Mb%n", maxMemory / MB);
        System.out.println("======= end of block =======");
    }
}
