package ru.job4j.basepatterns.structural.decorator;

/**
 * добавим класс Декоратор, который будет печатать кавычки с двух сторон
 * Декоратор реализует интерфейс PrinterInterface
 * Это будет 1-1 Декоратор. ConcreteDecorator
 * Это мы создаем оболочку вокруг конкретного класса Printer
 */
public class QutesDecorator extends Decorator {

    //конструктор имеет ссылку на наш интерфейс PrinterInterface
    //из родительского класса
    public QutesDecorator(PrinterInterface component) {
        super(component);
    }

    /**
     * этот метод super.print() оставляем т.к. в него мы можем что-нибудь дописать
     * например добавить кавычку до и кавычку после того что надо печатать
     */
    @Override
    public void print() {
        System.out.print("\""); //кавычка до
        super.print(); //вызов родительского print()
        System.out.print("\""); //кавычка после
    }
}
