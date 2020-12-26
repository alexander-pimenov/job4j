package ru.job4j.design.srp.sequencegenerator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
 * Класс, который генерируют последовательность случайных чисел и выводит ее.
 * Реализация интерфейса SequenceGenerator<T> и Output.
 */
public class SimpleSequenceGenerator implements SequenceGenerator<Integer>, Output {
    private NumberGenerator<Integer> numberGenerator;

    /*
     * Последовательность зависит от генерации числа.
     */
    public SimpleSequenceGenerator(NumberGenerator<Integer> numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    @Override
    public List<Integer> generate(int size) {
        return IntStream.range(0, size)
                .map(i -> numberGenerator.generate()).boxed()
                .collect(Collectors.toList());
    }

    @Override
    public void print(String msg) {
        System.out.println(msg);
    }

//    @Override
//    public List<Integer> generate(int size) {
//        Random random = new Random();
//        return IntStream.range(0, size)
//                .map(i -> random.nextInt()).boxed()
//                .collect(Collectors.toList());
//    }

//    @Override
//    public void print(List<Integer> numbers) {
//        numbers.forEach(System.out::println);
//    }
}
