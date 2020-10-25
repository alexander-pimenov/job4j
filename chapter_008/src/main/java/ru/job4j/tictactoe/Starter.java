package ru.job4j.tictactoe;

import ru.job4j.tictactoe.input.Input;
import ru.job4j.tictactoe.input.InputConsole;
import ru.job4j.tictactoe.logic.Logic;
import ru.job4j.tictactoe.model.Board;
import ru.job4j.tictactoe.model.IBoard;
import ru.job4j.tictactoe.player.Choose;
import ru.job4j.tictactoe.player.ChoosePlayers;
import ru.job4j.tictactoe.player.Player;

public class Starter {

    public Starter() {
    }

    private void game() {
        int winCounter = 0;
        int xWins = 0;
        int oWins = 0;
        int gameCounter = 1;

        System.out.println("Welcome to the game TicTacToe!");
        System.out.println("Выберите размер игрового поля.");
        System.out.println("Например, 3х3 или 5х5 или 7х7 или другое.");
        Input input = new InputConsole();
        IBoard board;
        int sizeBoard = input.ask("Для этого введите целое число от 3 до 9: ");
        if (sizeBoard > 2 && sizeBoard <= 9) {
            System.out.println("Выбрано игровое поле: " + sizeBoard + "x" + sizeBoard);
            board = new Board(sizeBoard);
        } else {
            System.out.println("Выбрано стандартное игровое поле: 3х3");
            board = new Board();
        }
        board.initBoard();
        board.printBoard();

        Logic logic = new Logic(board);

        System.out.println("До скольки побед будете играть? ");
        winCounter = input.ask("Введите целое число. Например, 3, т.е. игра до 3-х побед, или другое: ");
        if (winCounter == 0) {
            winCounter = 1;
            System.out.println("Играем до " + winCounter + " победы.");
        } else {
            System.out.println("Играем до " + winCounter + " побед(ы).");
        }

        System.out.println("Кто первый ходит, тот играет крестиками.");

        Choose choose = new ChoosePlayers();
        Player[] players = choose.getPlayers(input, board, logic);

        boolean exit = false;
        while (!exit) {
            System.out.println(String.format("Партия № %s, счет (X %s:%s O). Игра до %s побед.",
                    gameCounter, xWins, oWins, winCounter));
            board.initBoard();
            board.printBoard();
            while (true) {
                players[0].move(board.getCell().getSIGN_X());
                board.printBoard();
                if (logic.isWinnerX()) {
                    System.out.println("Победили крестики!");
                    xWins++;
                    gameCounter++;
                    if (xWins == winCounter) {
                        System.out.println(String.format("Игра закончена, победили крестики, счет (X %s:%s O)",
                                xWins, oWins));
                        exit = true;
                    }
                    break;
                }
                if (logic.isTableFull()) {
                    System.out.println("Ничья! Начните новую партию.%n");
                    gameCounter++;
                    break;
                }
                players[1].move(board.getCell().getSIGN_O());
                board.printBoard();
                if (logic.isWinnerO()) {
                    System.out.println("Победили нолики!");
                    oWins++;
                    gameCounter++;
                    if (oWins == winCounter) {
                        System.out.println(String.format("Игра закончена, победили нолики, счет (X %s:%s O)",
                                xWins, oWins));
                        exit = true;
                    }
                    break;
                }
                if (logic.isTableFull()) {
                    System.out.println("Ничья! Начните новую партию.%n");
                    gameCounter++;
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Starter starter = new Starter();
        starter.game();
    }
}