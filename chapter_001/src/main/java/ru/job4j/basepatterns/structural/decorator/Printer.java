package ru.job4j.basepatterns.structural.decorator;

/**
 * Конкретный принтер реализующий функцию печати
 */
public class Printer implements PrinterInterface {

    String value; //даем принтеру текст

    public Printer(String value) {
        this.value = value;
    }

    //говорим принтеру "печатай" наш текст value
    @Override
    public void print() {
        System.out.print(value);
    }
}
