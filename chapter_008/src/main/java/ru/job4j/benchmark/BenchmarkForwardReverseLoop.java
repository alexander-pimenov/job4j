package ru.job4j.benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value = 1, jvmArgs = {"-Xms2G", "-Xmx2G"})
@Warmup(iterations = 5)
@Measurement(iterations = 10)
@State(Scope.Benchmark)
public class BenchmarkForwardReverseLoop {

    @Param({"1000000", "10000000"})
    private int numberLoop;

    private List<String> dataForTesting = createData();

    public static void main(String[] argv) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(BenchmarkForwardReverseLoop.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }

    @Setup
    public void setup() {
        dataForTesting = createData();
    }

    @Benchmark
    public void forwardLoop(Blackhole bh) {
        for (int i = 0; i < dataForTesting.size(); i++) {
            String s = dataForTesting.get(i);
            bh.consume(s);
        }
    }

    @Benchmark
    public void reverseLoop(Blackhole bh) {
        for (int i = dataForTesting.size() - 1; i >= 0; i--) {
            String s = dataForTesting.get(i);
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