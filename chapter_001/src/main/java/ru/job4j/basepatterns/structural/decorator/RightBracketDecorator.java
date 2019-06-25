package ru.job4j.basepatterns.structural.decorator;

/**
 * класс Декоратор, который будет печатать скобку справа
 * Это будет 3-й Декоратор. ConcreteDecorator
 * Это мы создаем оболочку вокруг конкретного класса Printer
 */
public class RightBracketDecorator extends Decorator {

//    /*эта ссылка на интерфейс PrinterInterface уже не нужна,
//    * т.к. она наследуется от Decorator*/
//    PrinterInterface component; //добавляем ссылку на интерфейс PrinterInterface

    //конструктор имеет ссылку на наш интерфейс PrinterInterface
    //из родительского класса
    public RightBracketDecorator(PrinterInterface component) {
        super(component);
    }

    /**
     * этот метод super.print() оставляем т.к. в него мы можем что-нибудь дописать
     * например добавить скобку справа после того что надо печатать
     */
    @Override
    public void print() {
        super.print(); //вызов родительского print()
        System.out.print("]"); //скобка справа
    }
}
