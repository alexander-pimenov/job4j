package ru.job4j.chess.figures.black;

import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

import java.util.Arrays;

/**
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class QeenBlack implements Figure {
    private final Cell position;

    public QeenBlack(final Cell position) {
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
        if (!(source.x == dest.x || source.y == dest.y || Math.abs(deltaX) == Math.abs(deltaY))){
            throw new ImpossibleMoveException(String.format("%s не может так ходить!", this.getClass().getSimpleName()));
        }
        int length = Math.max(Math.abs(deltaX), Math.abs(deltaY));
        steps = new Cell[length];
        deltaX = Integer.compare(dest.x, source.x);
        deltaY = Integer.compare(dest.y, source.y);
        //deltaX = (deltaX > 0) ? 1 : ((deltaX < 0) ? -1 : deltaX);
        //deltaY = (deltaY > 0) ? 1 : ((deltaY < 0) ? -1 : deltaY);
        for (int i=0; i<steps.length; i++){
            steps[i] = Cell.values()[(source.x + deltaX * (i + 1)) * 8 + (source.y + deltaY * (i + 1))];
        }
        //return steps;
        return Arrays.copyOf(steps, length);

    }

    @Override
    public Figure copy(Cell dest) {
        return new QeenBlack(dest);
    }
}