package ru.job4j.tictactoe.logic;

import ru.job4j.tictactoe.model.IBoard;

import java.util.function.Predicate;

public class Logic {

    private IBoard board;

    public Logic(IBoard board) {
        this.board = board;
    }

    public boolean isWinnerX() {
        return isWin(character -> character == board.getCell().getSignX());
    }

    public boolean isWinnerO() {
        return isWin(character -> (character == board.getCell().getSignO()));
    }

    public boolean isWin(Predicate<Character> winCondition) {
        boolean result = false;
        for (int i = 0; i < board.getBoard().length; i++) {
            if (fillBy(winCondition, i, 0, 0, 1)
                    || fillBy(winCondition, 0, i, 1, 0)
                    || fillBy(winCondition, 0, 0, 1, 1)
                    || fillBy(winCondition, board.size() - 1, 0, -1, 1)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public boolean fillBy(Predicate<Character> predicate, int startX, int startY, int deltaX, int deltaY) {
        boolean result = true;
        for (int index = 0; index != board.size(); index++) {
            char cell = board.getBoard()[startX][startY];
            startX += deltaX;
            startY += deltaY;
            if (!predicate.test(cell)) {
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * Метод проходит по всем ячейкам игровой таблицы и,
     * если они все заняты, возвращает true. Если хотя
     * бы одна ячейка ещё свободна, возвращается false.
     *
     * @return boolean result
     */
    public boolean isTableFull() {
        for (int row = 0; row < board.size(); row++) {
            for (int col = 0; col < board.size(); col++) {
                if (board.getBoard()[row][col] == board.getCell().getSignEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Метод, определяющий валидность ячейки.
     *
     * @param x координата ячейки по X
     * @param y координата ячейки по Y
     * @return boolean result.
     */
    public boolean isCellValidForHuman(int x, int y) {
        if (x < 0 || y < 0 || x >= board.size() || y >= board.size()) {
            System.out.println("Вы вышли за рамки игрового поля. Try again.");
            return false;
        }
        if (board.getBoard()[x][y] != board.getCell().getSignEmpty()) {
            System.out.println("Ячейка занята. Введите другие координаты.");
            return false;
        }
        return true;
    }

    /**
     * Метод, определяющий валидность ячейки для хода
     * компьютера. Немного отчличается от метода для
     * хода человека.
     *
     * @param x координата ячейки по X
     * @param y координата ячейки по Y
     * @return boolean result.
     */
    public boolean isCellValidForRobot(int x, int y) {
        if (board.getBoard()[x][y] != board.getCell().getSignEmpty()) {
            return false;
        }
        return true;
    }
}