package ru.job4j.departments;

/*
  В организации ввели справочник подразделений.
  Коды подразделений представлены в виде массива строк вида:
  “K1\SK1”
  “K1\SK2”
  “K1\SK1\SSK1”
  “K1\SK1\SSK2”
  “K2”
  “K2\SK1\SSK1”
  “K2\SK1\SSK2”
  <p>
  где каждая строка имеет следующую структуру:
  каждая строка включает в себя код данного подразделения,
  а также все коды подразделений, которые включают в свою структуру
  данное подразделение (к примеру департамент K1 включает в себя
  службу SK1, включающую в себя отдел SSK1).
  <p>
  Реализована возможность сортировки массива кодов подразделений по
  возрастанию и убыванию, при которых сохраняется иерархическая структура,
  т.к. отсортированный массив используется для отображения категорий пользователю.
 */

import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

public class DepartmentsSorter {

    /*Сортирует список подразделений в прямом порядке.*/
    public void sortRight(List<String> list) {
        editDept(list); //дополняем список подразделений
        list.sort(Comparator.naturalOrder());
    }

    /*Сортирует список подразделений в обратном порядке,
    с сохранением иерархической структуры.*/
    public void sortBack(List<String> list) {
        editDept(list); //дополняем список подразделений
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int result;

                /*Сравниваем длины двух названий подразделений, если они одинаковые, то сраниваем их
                 * например [K1/SK1] и [K1/SK2]
                 * Если разные, то идем дальше в else.
                 * Далее, выбираем минимальную длину символов в названии и получаем подстроки для сравнения.
                 * Например, [K2/SK1] с [K2/SK1/SSK1] -> минимум из 6 и 11 это 6.
                 * Поэтому обрезаем и получаем подстроки: [K2/SK1] с [K2/SK1]. Далее сравниваем их. И как
                 * result получаем 1 или -1.
                 * А если бы было сравнение [K1/SK2] и [K3/SK1/SSK2], то мы прошли в последний else и
                 * получили бы сравнение result = o2.compareTo(o1), т.е. как при обратной последовательности.
                 * */

                if (o1.length() == o2.length()) {
                    result = o2.compareTo(o1);
                } else {
                    int min = Math.min(o1.length(), o2.length());
                    String first = o1.substring(0, min);
                    String second = o2.substring(0, min);
                    if (first.compareTo(second) == 0
                            && o1.length() > o2.length()) {
                        result = 1;
                    } else if (first.compareTo(second) == 0
                            && o1.length() < o2.length()) {
                        result = -1;
                    } else {
                        result = o2.compareTo(o1);
                    }
                }
                return result;
            }
        });
    }

    /*Проверяет пирамиду названий подразделения.
     * Добавляет недостающие названия.*/
    private void editDept(List<String> list) {
        for (int i = 0; i < list.size(); i++) {

            /* Разбиение названия подразделения на части -> массив частей (юниты)
             * на выходе получаем список массивов:
             * [K1, SK1] -> разбили K1/SK1
             * [K1, SK2] -> разбили K1/SK2
             * [K1, SK1, SSK1] -> разбили K1/SK1/SSK1 и т.д.
             *
             * Pattern.quote("/") - так мы описали разделитель
             * */
            String[] units = list.get(i).split(Pattern.quote("/"));

            /* Заходим внутрь каждого массива, [0] элемент задаем переменной temp
             * и проверяем наличие такого названия во всем списке названий департаментов.
             * Если такого нет, то записываем его туда -> list.add(temp)
             * Потом через "/" добавляем (конкатенируем) следующий элемент и проверяем такое название
             * подразделения в полном списке департаментов, если нет то и его записываем туда.
             * */
            if (units.length > 1) {
                String temp = units[0];
                for (int in = 1; in < units.length; in++) {
                    if (!list.contains(temp)) {
                        list.add(temp);
                    }
                    temp += "/" + units[in];
                }
            }
        }
    }
}
