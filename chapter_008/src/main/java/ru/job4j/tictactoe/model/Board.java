package ru.job4j.tictactoe.model;

public class Board implements IBoard {

    private static final int DEFAULT_SIZE = 3;
    private int size;
    private char[][] board;
    private Cell cell;

    public Board() {
        this(DEFAULT_SIZE);
    }

    public Board(int size) {
        this.size = size;
        this.cell = new Cell();
        board = new char[size][size];
        initBoard();
    }

    @Override
    public void initBoard() {
        for (int row = 0; row < board.length; row++)
            for (int col = 0; col < board.length; col++)
                board[row][col] = cell.getSIGN_EMPTY();
    }

    @Override
    public int size() {
        return size;
    }


    @Override
    public void printBoard() {
        System.out.print(" ");
        for (int i = 0; i < size; i++) {
            System.out.print(" " + (i + 1));
        }
        System.out.println();
        for (int row = 0; row < size; row++) {
            System.out.print(row + 1 + " ");
            for (int col = 0; col < size; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    @Override
    public char[][] getBoard() {
        return board;
    }

    @Override
    public Cell getCell() {
        return cell;
    }
}