package ru.job4j.generic.animal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Generics {
    public static void main(String[] args) {
        Generics gen = new Generics();
        List<Animal> first = new ArrayList<>();
        List<Predator> second = new ArrayList<>();
        List<Tiger> third = new ArrayList<>();
        first.add(new Animal());
        second.add(new Predator());
        third.add(new Tiger());

        gen.printObject(first);
        gen.printObject(second);
        gen.printObject(third);
        System.out.println();

//        gen.printBoundedWildCard(first);
        gen.printBoundedWildCard(second);
        gen.printBoundedWildCard(third);
        System.out.println();

        gen.printLowerBoundedWildCard(first);
        gen.printLowerBoundedWildCard(second);
//        gen.printLowerBoundedWildCard(third);
    }

    /*1-ый метод - работает без ограничений,
     * т.е. в него можно передавать коллекцию, которая хранит любые типы.*/
    public void printObject(List<?> list) {
        for (Iterator<?> it = list.iterator(); it.hasNext(); ) {
            Object next = it.next();
            System.out.println("Текущий элемент: " + next);
        }
    }

    /*2-ой метод - должен иметь ограничение сверху и ограничиваться классом Predator.
     * Т.е. можно сохранять в коллекции только объекты класса Predator и его наследники.
     * Объекты класса Animal не смогут сюда попасть.*/
    public void printBoundedWildCard(List<? extends Predator> list) {
        for (Iterator<? extends Predator> it = list.iterator(); it.hasNext(); ) {
            Object next = it.next();
            System.out.println("Текущий элемент: " + next);
        }
    }

    /*3-ий метод - должен иметь ограничение снизу и ограничиваться классом Predator.
     * Т.е. не сможем сохранять объекты наследники класса Predator, а вот его родителя
     * Animal сможем.*/
    public void printLowerBoundedWildCard(List<? super Predator> list) {
        for (Iterator<? super Predator> it = list.iterator(); it.hasNext(); ) {
            Object next = it.next();
            System.out.println("Текущий элемент: " + next);
        }
    }
}
