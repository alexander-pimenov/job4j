package ru.job4j.basepatterns.creational.factory;

/**
 * Класс  Program - это класс клиент
 * <p>
 * Изначально у нас было два разработчика JavaDeveloper и CppDeveloper.
 * Чтобы добавить например Php-разработчика создаем класс PhpDeveloper, который имплементирует Developer
 * и создаем фабрику PhpDeveloperFactory, которая имплементирует DeveloperFactory
 * и метод этого класса createDeveloper возвращает PhpDeveloper
 * И потом конечно вносим небольшие изменения в программе-клиент, точнее
 * в createDeveloperBySpecialty.
 *
 * Developer может отличатся друг от друга. Потому и необходим индивидуальный DeveloperFactory.
 *
 * И часто имеет смысл вынести функционал создания объектов в отдельный класс.
 */
public class Program {
    public static void main(String[] args) {
        DeveloperFactory developerFactory = createDeveloperBySpecialty("php");
        Developer developer = developerFactory.createDeveloper();

        developer.writeCode();

    }

    /*Чтобы программа была более гибкой создадим статический метод
     * который будет возвращать DeveloperFactory
     * Назначение: чтобы возвращать определенную фабрику разработчиков
     * в зависимости от специальности, которая нам необходима*/
    static DeveloperFactory createDeveloperBySpecialty(String specialty) {
        if (specialty.equalsIgnoreCase("java")) {
            return new JavaDeveloperFactory();
        } else if (specialty.equalsIgnoreCase("c++")) {
            return new CppDeveloperFactory();

            //эту строчку добави позже, когда понадобился PhpDeveloperFactory
        } else if (specialty.equalsIgnoreCase("php")) {
            return new PhpDeveloperFactory();

            /*если вводим специальность, которая у нас не предусмотренна,
             * то мы бросаем exception*/
        } else {
            throw new RuntimeException(specialty + " is unknown specialty.");
        }
    }
}
