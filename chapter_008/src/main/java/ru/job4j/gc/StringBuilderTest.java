package ru.job4j.gc;

/**
 * Класс для теста работа профайлера VisualVM.
 * Запускаем программу. Потом запускаем VisualVM.
 * Заходим во вкладку Sampler, тестим CPU нексколько секунд,
 * останавливаем и смотрим на snapshot.
 */
public class StringBuilderTest {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("O");
        for (int i = 0; i < 1_000_000; i++) {
            sb.delete(0, 1); //удаляем 1-й символ
            sb.append(i);
        }
        System.out.println(sb.length());
    }
}
