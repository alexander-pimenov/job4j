package ru.job4j.array;

/**
 * Обертка над строкой.
 * Метод toCharArray() создает из строки массив чаров (от англ. - char).
 */
public class ArrayChar {
    private char[] data;

    public ArrayChar(String line) {
        this.data = line.toCharArray();
    }

    /**
     * Проверяет, что слово начинается с префикса.
     * проверяем, что массив data имеет первые элементы, одинаковые с value
     * @param prefix префикс.
     * @return если слово начинается с префикса
     */
    public boolean startWith(String prefix) {
        boolean result = true;
        char[] value = prefix.toCharArray();

        for (int i = 0; i < value.length; i++) {
            if (data[i] != value[i]) {
                result = false;
                break;
            }
        }
        return result;
    }
}



