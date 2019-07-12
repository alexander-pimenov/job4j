package ru.job4j.chess.figures.black;

import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

/**
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class PawnBlack implements Figure {
    private final Cell position;

    public PawnBlack(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        Cell[] steps;
        int deltaX = dest.x - source.x;
        int deltaY = dest.y - source.y;
        if (((source.y == dest.y + 1) || (source.y == 6 & source.y == dest.y + 2)) & (source.x == dest.x)) {
            int length = Math.max(Math.abs(deltaX), Math.abs(deltaY));
            deltaX = Integer.compare(dest.x, source.x);
            deltaY = Integer.compare(dest.y, source.y);
            steps = new Cell[length];
            for (int i = 0; i < steps.length; i++) {
                steps[i] = Cell.values()[(source.x + deltaX * (i + 1)) * 8 + (source.y + deltaY * (i + 1))];
            }
        } else {
            throw new ImpossibleMoveException(String.format("%s не может так ходить!", this.getClass().getSimpleName()));
        }
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new PawnBlack(dest);
    }
}
