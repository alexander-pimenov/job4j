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
public class RookWhite implements Figure {
    private final Cell position;

    public RookWhite(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest)throws ImpossibleMoveException {
        Cell[] steps;
        int deltaX = dest.x - source.x;
        int deltaY = dest.y - source.y;
        if (source.x != dest.x & source.y != dest.y){
            throw new ImpossibleMoveException(String.format("%s не может так ходить!", this.getClass().getSimpleName()));
        }
        int length = Math.max(Math.abs(deltaX), Math.abs(deltaY));
        //deltaX = (source.x == dest.x) ? 0 : (source.x > dest.x ? -1 : 1);
        //deltaY = (source.y == dest.y) ? 0 : (source.y > dest.y ? -1 : 1);
        deltaX = Integer.compare(dest.x, source.x);
        deltaY = Integer.compare(dest.y, source.y);
        steps = new Cell[length];
        for (int i=0; i<steps.length; i++){
            steps[i] = Cell.values()[(source.x + deltaX * (i + 1)) * 8 + (source.y + deltaY * (i + 1))];
        }
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new RookWhite(dest);
    }
}
