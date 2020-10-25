package ru.job4j.tictactoe.player;

import ru.job4j.tictactoe.input.Input;
import ru.job4j.tictactoe.logic.Logic;
import ru.job4j.tictactoe.model.IBoard;

public class ChoosePlayers implements Choose {
    /**
     * Метод инициализирующий игроков.
     * Таких как Человек и Компьютер.
     * В разных вариациях.
     *
     * @param input ввод координат
     * @param board игровое поле
     * @param logic логика игры
     * @return массив игроков
     */
    @Override
    public Player[] getPlayers(Input input, IBoard board, Logic logic) {
        Player[] players = new Player[2];
        Player player1 = null;
        Player player2 = null;
        System.out.println("Выберите игру...");
        System.out.println("1 - играет человек-человек");
        System.out.println("2 - играет человек-робот");
        System.out.println("3 - играет робот-человек");
        System.out.println("4 - играет робот-робот");
        boolean escapeLoop = false;
        while (!escapeLoop) {
            int number = input.ask("Введите число от 1 до 4: ");
            switch (number) {
                case 1:
                    player1 = new PlayerHuman(input, board, logic);
                    player2 = new PlayerHuman(input, board, logic);
                    escapeLoop = true;
                    break;
                case 2:
                    player1 = new PlayerHuman(input, board, logic);
                    player2 = new PlayerAI(board, logic);
                    escapeLoop = true;
                    break;
                case 3:
                    player1 = new PlayerAI(board, logic);
                    player2 = new PlayerHuman(input, board, logic);
                    escapeLoop = true;
                    break;
                case 4:
                    player1 = new PlayerAI(board, logic);
                    player2 = new PlayerAI(board, logic);
                    escapeLoop = true;
                    break;
                default:
                    break;
            }
        }
        players[0] = player1;
        players[1] = player2;
        return players;
    }
}