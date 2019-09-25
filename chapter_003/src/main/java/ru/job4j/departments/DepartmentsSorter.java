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
        editDept(list);
        list.sort(Comparator.naturalOrder());
    }

    /*Сортирует список подразделений в обратном порядке,
    с сохранением иерархической структуры.*/
    public void sortBack(List<String> list) {
        editDept(list);
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int result;
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
            String[] units = list.get(i).split(Pattern.quote("/"));
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
