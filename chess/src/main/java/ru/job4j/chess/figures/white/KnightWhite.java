package ru.job4j.chess.figures.white;

import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

/**
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class KnightWhite implements Figure {
    private final Cell position;

    public KnightWhite(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        Cell[] steps;
        if ((Math.abs(source.x - dest.x) == 1 && Math.abs(source.y - dest.y) == 2)
                || (Math.abs(source.x - dest.x) == 2 && Math.abs(source.y - dest.y) == 1)) {
            steps = new Cell[]{dest};
        } else {
            throw new ImpossibleMoveException(String.format("%s не может так ходить!", this.getClass().getSimpleName()));
        }
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new KnightWhite(dest);
    }
}
