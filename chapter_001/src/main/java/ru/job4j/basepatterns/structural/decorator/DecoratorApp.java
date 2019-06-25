package ru.job4j.basepatterns.structural.decorator;

public class DecoratorApp {
    public static void main(String[] args) {
        // PrinterInterface printer = new Printer("Привет");

        /* сначала создаем экземпляр QutesDecorator (это наши кавычкм), затем в качестве аргумента передаем
         * экземпляр LeftBracketDecorator в него в качестве параметра помещаем RightBracketDecorator,
         * а в RightBracketDecorator потом наш Printer
         * Смотрим за процессом из нутри наружу:
         * т.е. сначала пишем Привет, обрамляем его правой скобокой, затем левой скобочкой, а затем все напечатаное
         * берем в кавычки*/
        PrinterInterface printer = new QutesDecorator(new LeftBracketDecorator(new RightBracketDecorator(new Printer("Привет"))));
        printer.print();
    }
}
