package ru.job4j.chess.figures;

public interface Figure {
    Cell position();

//    boolean isMovementPossible (Cell source, Cell dest);

    Cell[] way(Cell source, Cell dest);

    default String icon() {
        return String.format(
                "%s.png", this.getClass().getSimpleName()
        );

    }

    Figure copy(Cell dest);

}
