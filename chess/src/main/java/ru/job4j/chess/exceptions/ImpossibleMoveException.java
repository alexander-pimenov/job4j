package ru.job4j.chess.exceptions;

public class ImpossibleMoveException extends RuntimeException {
    public ImpossibleMoveException() {
    }

    public ImpossibleMoveException(String msg) {
        super(msg);
    }
}
