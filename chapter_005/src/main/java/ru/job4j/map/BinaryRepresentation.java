package ru.job4j.map;

public class BinaryRepresentation {
    public static void main(String[] args) {

        int k = 255;
        System.out.println(binary(k));
        System.out.println(binary(k >>> 16));
        System.out.println(binary(k ^ (k >>> 16)));

        int h = 123456789;
        System.out.println(binary(h));
        System.out.println(binary(h >>> 16));
        System.out.println(binary(h ^ (h >>> 16)));

        int max = Integer.MAX_VALUE;
        System.out.println(binary(max));
        System.out.println(binary(max >>> 16));
        System.out.println(binary(max ^ (max >>> 16)));
    }

    /**
     * Метод выводит битовое представление числа.
     *
     * @param num целое числ
     * @return строковое представление двоичного кода числа
     */
    public static String binary(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 31; i++) {
            sb.append(num % 2 == 0 ? 0 : 1);
            sb.append((i + 1) % 8 == 0 ? " " : "");
            num /= 2;
        }
        return sb.reverse().toString();
    }
}
