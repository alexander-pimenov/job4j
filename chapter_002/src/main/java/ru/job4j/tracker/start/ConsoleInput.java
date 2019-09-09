package ru.job4j.tracker.start;

import java.util.List;
import java.util.Scanner;

/**
 * Консольный ввод данных
 */

public class ConsoleInput implements Input {

    private Scanner scanner = new Scanner(System.in);

    /**
     * Метод возвращает ответ на вопрос
     * @param question вопрос
     * @return ответ
     * */
    public String ask(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }

    /**
     * В методе проверяем, что введенный ключ входит в диапазон range нашего меню
     */
    @Override
    public int ask(String question, List<Integer> range) throws MenuOutException {
        int key = Integer.valueOf(this.ask(question));
        boolean exist = false;
        for (int value : range) {
            if (value == key) {
                exist = true;
                break;
            }
        }
        if (!exist) {
            throw new MenuOutException("Out of menu range.");
        }
        return key;
    }
}
