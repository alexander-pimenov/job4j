package ru.job4j.gc;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple Java program to demonstrate how to enable verbose Garbage Collection (GC) logging.
 * <p>
 * This simple program loads 3 million {@link java.lang.String} instances into a {@link java.util.HashMap}
 * object before making an explicit call to the garbage collector using <code>System.gc()</code>.
 * <p>
 * Finally, it removes 2 million of the {@link java.lang.String} instances from the {@link java.util.HashMap}.
 * We also explicitly use <code>System.out.println</code> to make interpreting the output easier.
 * <p>
 * Run this program with the following arguments to see verbose GC logging in its complete form:
 * <pre>
 * -XX:+UseSerialGC -Xms1024m -Xmx1024m -verbose:gc -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintGCDateStamps -Xloggc:/path/to/file/gc.log
 * </pre>
 * <pre>
 * -XX:+UseSerialGC -Xms1024m -Xmx1024m -verbose:gc -Xlog:gc*::time -Xlog:gc:C:\projects\job4j\chapter_008\gc.log
 * </pre>
 * Where:
 * <ul>
 *   <li><code>-XX:+UseSerialGC</code> - specify the serial garbage collector.</li>
 *   <li><code>-Xms1024m</code> - specify the minimal heap size.</li>
 *   <li><code>-Xmx1024m</code> - specify the maximal heap size.</li>
 *   <li><code>-verbose:gc</code> - activate the logging of garbage collection information in its simplest form.</li>
 *   <li><code>-XX:+PrintGCDetails</code> - activate detailed information about garbage collection (up to Java 9).</li>
 *   <li><code>-XX:+PrintGCTimeStamps</code> - include a timestamp in the output reflecting the real-time passed in seconds since the JVM started (up to Java 9).</li>
 *   <li><code>-XX:+PrintGCDateStamps</code> - include the absolute date and time at the start of each line (up to Java 9).</li>
 *   <li><code>-Xloggc</code> - by default the GC log is written to stdout. Specify an output file via this argument (up to Java 9).</li>
 *   <li><code>-Xlog:gc*</code> - in Java 9, activate detailed information about garbage collection.</li>
 *   <li><code>-Xlog:gc*::time</code> - in Java 9, to get detailed information about garbage collection and include the date and time information that will appear in our logs use this argument.</li>
 *   <li><code>-Xlog:gc:/path/to/file/gc.log</code> - in Java 9 by default the GC log is written to stdout. Specify an output file via this argument.</li>
 * </ul>
 * <p>
 * It should be noted that the first three arguments are not strictly necessary but for the purposes or the example
 * help really simplify things.
 * Thus, you need to specify the path to the log file:
 * -Xlog:gc:/path/to/file/gc.log => further, for my file => -Xlog:gc:C:\projects\job4j\chapter_008\gc.log or
 * -Xlog:gc:C:\projects\job4j\chapter_008\gc.txt
 * <p>
 * Arguments in this time:
 * -XX:+UseG1GC -Xms512m -Xmx512m -verbose:gc -Xlog:gc*::time -Xlog:gc:C:\projects\job4j\chapter_008\gc.txt
 * <p>
 * If we want to display information about loaded classes, use the key -verbose:class
 */
public class VerboseGarbageCollectorRunner {
    private static Map<String, String> stringContainer = new HashMap<>();

    public static void main(String[] args) {
        System.out.println("Start of program!");
        String stringWithPrefix = "stringWithPrefix";

        // Load Java Heap with 3 M java.lang.String instances
        for (int i = 0; i < 3_000_000; i++) {
            String newString = stringWithPrefix + i;
            stringContainer.put(newString, newString);
        }
        System.out.println("MAP size: " + stringContainer.size());

        // Explicit GC!
        System.gc();

        // Remove 2 M out of 3 M
        for (int i = 0; i < 2_000_000; i++) {
            String newString = stringWithPrefix + i;
            stringContainer.remove(newString);
        }

        System.out.println("MAP size: " + stringContainer.size());
        System.out.println("End of program!");
    }
}

//Результа итогового вывода:
//[2020-11-11T16:14:30.099+0300] Heap
//[2020-11-11T16:14:30.099+0300]  def new generation   total 157248K, used 127467K [0x00000000e0000000, 0x00000000eaaa0000, 0x00000000eaaa0000)
//[2020-11-11T16:14:30.099+0300]   eden space 139776K,  91% used [0x00000000e0000000, 0x00000000e7c7afa0, 0x00000000e8880000)
//[2020-11-11T16:14:30.099+0300]   from space 17472K,   0% used [0x00000000e8880000, 0x00000000e8880000, 0x00000000e9990000)
//[2020-11-11T16:14:30.099+0300]   to   space 17472K,   0% used [0x00000000e9990000, 0x00000000e9990000, 0x00000000eaaa0000)
//[2020-11-11T16:14:30.099+0300]  tenured generation   total 349568K, used 299747K [0x00000000eaaa0000, 0x0000000100000000, 0x0000000100000000)
//[2020-11-11T16:14:30.099+0300]    the space 349568K,  85% used [0x00000000eaaa0000, 0x00000000fcf58c28, 0x00000000fcf58e00, 0x0000000100000000)
//[2020-11-11T16:14:30.099+0300]  Metaspace       used 1311K, capacity 4609K, committed 4864K, reserved 1056768K
//[2020-11-11T16:14:30.100+0300]   class space    used 115K, capacity 424K, committed 512K, reserved 1048576K

