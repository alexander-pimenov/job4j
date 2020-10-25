package ru.job4j.tictactoe.model;

public interface IBoard {

    void initBoard();
    void printBoard();
    char[][] getBoard();
    Cell getCell();
    int size();
}