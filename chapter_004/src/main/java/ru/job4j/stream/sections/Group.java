package ru.job4j.stream.sections;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * В этом задании нужно произвести группировку студентов по интересам.
 * <p>
 * У каждого студента есть список секций, которые он посещает.
 */
public class Group {
    public static void main(String[] args) {

        //Наши студенты
        Student ivanov = new Student("Ivanov", Set.of("Biology", "Aeromodelling", "Tennis"));
        Student petrov = new Student("Petrov", Set.of("Tennis", "Aeromodelling", "Badminton"));
        Student sidorov = new Student("Sidorov", Set.of("Badminton", "Robotics", "Biology"));
        Student nikolaev = new Student("Nikolaev", Set.of("Biology", "Fencing", "Philosophy"));
        Student orlova = new Student("Orlova", Set.of("Philosophy", "Tennis", "Robotics"));
        Student peterson = new Student("Peterson", Set.of("Philosophy", "Badminton"));
        Student proctor = new Student("Proctor", Set.of("Tennis", "Biology", "Fencing"));

        //Заполним список студентов
        List<Student> students = Arrays.asList(ivanov, petrov, sidorov, nikolaev, orlova, peterson, proctor);

        //Получим список секций из списка студентов
        System.out.println("##### Список секций #####");

        var listUnits = students.stream()
                .flatMap(student -> student.getUnits().stream())
                .distinct()
                .collect(Collectors.toList());
        System.out.println(listUnits);
        System.out.println("**********************");

        System.out.println("##### method group students by sections #####");
        Map<String, Set<String>> groupBySections = sections(students);
        groupBySections.entrySet().forEach(System.out::println);
        System.out.println("*****Красивый вывод*****");
        //Или так красиво выводим результат
        for (Map.Entry<String, Set<String>> itemGroup : groupBySections.entrySet()) {
            System.out.println("-= " + itemGroup.getKey() + " =-");
            for (String names : itemGroup.getValue()) {
                System.out.println(names);
            }
        }
    }

    /**
     * Метод, который группирует студентов по секциям.
     * Создали доп.класс Holder для хранения в нем названий секций и имен студентов.
     * Создаем стрим из Студентов, и преобразуем его в поток Holder, используя метод flatMap.
     * Вытаскиваем из Student название секций, преобразуем их в stream(),
     * далее с помощью distinct() убираем дубликаты, и упаковываем с помощью map() в Holder название секции в
     * String key и имя студента в String value. Имеем на выходе Stream<Holder>.
     * Далее весь поток нужно собрать в карту - для этого используйте метод collect.
     * Внутри метода используем метод groupingBy. Этот метод позволяет сделать
     * группировку по ключу. В нашем случае Holder::getKey.
     * Далее используя mapping(), мы можем сначала выполнить группировку на основе всего объекта Holder,
     * а затем сопоставить его с именами студентов. Ну и далее эти имена собираем в Сет.
     * <p>
     * Примечание:
     * (Как вариант, мы можем внутри самого метода groupingBy создать свой аккумулятор через
     * метод Collector.of. Это нужно сделать, так как метод groupingBy
     * собирает группы с типом входной коллекции.
     * Например, если тип List<User>, то groupingBy будет собирать элементы по User.
     * В нашем случае там нужны только имена студентов.)
     */
    //public static Map<String, Set<String>> sections(List<Student> students) {
    public static Map<String, Set<String>> sections(List<Student> students) {
        Map<String, Set<String>> collect = students.stream()
                .flatMap(student -> student.getUnits().stream()
                        .map(b -> new Holder(b, student.getName()))) // собираем объект Holder с unit и name
                //.peek(System.out::println) //нужно лишь, чтобы видеть поток Holder
                .collect(       //собираем карту
                        Collectors.groupingBy(Holder::getKey, //определяем группировку - группируем по названию секции
                                Collectors.mapping((Holder::getValue), //собираем имена студентов в Сет.
                                        Collectors.toSet())));
        return collect;
    }
}


//Collector.of(
//      HashSet::new, //поставщик, supplier()
//      (set, el) -> set.add(el),//аккумулятор accumulator(), накопление, как добавлять данные или через HashSet::add;
//      (left, right) -> {
//                      left.addAll(right);
//                      return left;
//                       } //для агрегации, combiner(), сумматор
//       )));
