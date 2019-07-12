package ru.job4j.chess.exceptions;

public class FigureNotFoundException extends RuntimeException {
    public FigureNotFoundException() {
    }

    public FigureNotFoundException(String msg){
        super(msg);
    }
}
