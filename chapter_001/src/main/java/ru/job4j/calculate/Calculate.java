package ru.job4j.calculate; //ru.job4j.calculate - является элементом программы, показывающим полный путь
/**
 * Описание Класса
 * Class Calculate для вычисления арифметических операций + - * / 
 * @author parsentev (parsentev@yandex.ru)
 * @since 11.03.2019
 * @version 1.0
 */
public class Calculate {    //название класса, в котором набор команд описывающих поведение программы, код

    /**
     * Описание Метода
     * Конструктор, вывод строки в консоль
     * @param args = args
     */
    public static void main(String[] args) {
        /*проверяю в какое число преобразуются буквы*/
        int i,j,k;
        i='Ф';
        j='п';
        k='д';

        System.out.println("Hello World");
        System.out.format("мы ввели: %s,  %s,  %s %n",i,j,k);
       // System.out.println();

        //int key = Integer.valueOf('i');
        int key = 'i';
        System.out.println(key);
    }

    /**
     * Method echo.
     * @param name Your name.
     * @return Echo plus your name.
     */
    public String echo(String name) {
        return "Echo, echo, echo : " + name;
    }



}