package ru.job4j.benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/*

Пример JMH Mode.AverageTime для измерения производительности
различных методов зацикливания для зацикливания `List`, содержащего
10 миллионов строк.
https://mkyong.com/java/java-jmh-benchmark-tutorial/
http://hg.openjdk.java.net/code-tools/jmh/file/tip/jmh-samples/src/main/java/org/openjdk/jmh/samples/
*/

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
//@Warmup(iterations = 3) //итерация прогрева
//@Measurement(iterations = 8) //итерация измерения
public class BenchmarkLoop {
    @Param({"10000000"})
    private int numberLoop;

    private List<String> dataForTesting;

    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(BenchmarkLoop.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }

    @Setup
    public void setup() {
        dataForTesting = createData();
    }

    @Benchmark
    public void loopFor(Blackhole bh) {
        for (int i = 0; i < dataForTesting.size(); i++) {
            String s = dataForTesting.get(i); //take out n consume, fair with foreach
            bh.consume(s);
        }
    }

    @Benchmark
    public void loopWhile(Blackhole bh) {
        int i = 0;
        while (i < dataForTesting.size()) {
            String s = dataForTesting.get(i);
            bh.consume(s);
            i++;
        }
    }

    @Benchmark
    public void loopForEach(Blackhole bh) {
        for (String s : dataForTesting) {
            bh.consume(s);
        }
    }

    @Benchmark
    public void loopIterator(Blackhole bh) {
        Iterator<String> iterator = dataForTesting.iterator();
        while (iterator.hasNext()) {
            String s = iterator.next();
            bh.consume(s);
        }
    }

    private List<String> createData() {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < numberLoop; i++) {
            data.add("Number : " + i);
        }
        return data;
    }
}
