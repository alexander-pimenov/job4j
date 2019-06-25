package ru.job4j.basepatterns.structural.decorator;

/**
 * класс Декоратор, который будет печатать скобку слева
 * Декоратор реализует интерфейс PrinterInterface
 * Это будет 2-й Декоратор. ConcreteDecorator
 * Это мы создаем оболочку вокруг конкретного класса Printer
 */
public class LeftBracketDecorator extends Decorator {

    //конструктор имеет ссылку на наш интерфейс PrinterInterface
    //из родительского класса
    public LeftBracketDecorator(PrinterInterface component) {
        super(component);
    }

    /**
     * этот метод super.print() оставляем т.к. в него мы можем что-нибудь дописать
     * например добавить скобку слева до того что надо печатать
     */
    @Override
    public void print() {
        System.out.print("["); //скобка слева
        super.print(); //вызов родительского print()
    }
}
