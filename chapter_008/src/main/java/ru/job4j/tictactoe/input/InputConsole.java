package ru.job4j.tictactoe.input;

import java.util.Scanner;

public class InputConsole implements Input {

    private Scanner scanner = new Scanner(System.in);

    @Override
    public int ask(String name) {
        System.out.print(name);
        return inInt();
    }

    /**
     * Метод контролирующий, что введеные данные это
     * целое положительное число, а не другой символ.
     *
     * @return целое положительное число.
     */
    private int inInt() {
        //Крутимся в бесконечном цикле пока
        //не будет введено целое число.
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Not an integer! Try again.");
            }
        }
    }
}