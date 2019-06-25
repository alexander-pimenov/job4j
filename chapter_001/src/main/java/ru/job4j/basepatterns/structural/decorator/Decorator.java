package ru.job4j.basepatterns.structural.decorator;
/**
 * Используем абстрактный класс, но можно и интерфейс
 *  Decorator будет хранить в себе повторяющийся код из ConcreteDecorators
 *  это чтобы мы не делали копипас
 *  и наши ConcreteDecorators будут уже наследоваться не от PrinterInterface, а от Decorator
 *  т.е. получат родителя
 *  В этом классе реализуем все, что можем реализовать
 */
abstract class Decorator implements PrinterInterface {

    PrinterInterface component; //ссылка на интерфейс PrinterInterface


    //конструктор имеет ссылку на наш интерфейс PrinterInterface
    public Decorator(PrinterInterface component) {
        this.component = component;
    }

    @Override
    public void print() {
        component.print();
    }
}
