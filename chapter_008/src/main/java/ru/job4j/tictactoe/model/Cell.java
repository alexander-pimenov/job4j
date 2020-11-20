package ru.job4j.tictactoe.model;

public class Cell {

    private final char signX = 'x';
    private final char signO = 'o';
    private final char signEmpty = '.';

    public char getSignX() {
        return signX;
    }

    public char getSignO() {
        return signO;
    }

    public char getSignEmpty() {
        return signEmpty;
    }

}