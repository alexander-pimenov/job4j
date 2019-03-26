package ru.job4j.loop;

/**
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Paint {
    public String pyramid(int height) {
        // Буфер для результата.
        StringBuilder screen = new StringBuilder();
        // ширина
        int width = 2 * height - 1;
        // внешний цикл двигается по строкам.
        for (int row = 0; row != height; row++) {
            // внутренний цикл определяет положение ячейки в строке.
            for (int column = 0; column != width; column++) {
                // если строка равна ячейке, то рисуем галку.
                // в данном случае строка определяет, сколько галок будет в строке
                if ((row >= height - column - 1) && (row + height - 1 >= column)) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            // добавляем перевод строки.
            screen.append(System.lineSeparator());
        }
        // Получаем результат.
        return screen.toString();
    }
}







