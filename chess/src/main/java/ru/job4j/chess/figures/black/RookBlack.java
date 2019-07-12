package ru.job4j.chess.figures.black;

import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

/**
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class RookBlack implements Figure {
    private final Cell position;

    public RookBlack(final Cell position) {
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
        return new RookBlack(dest);
    }


}

//    public Cell[] moveInline(Cell source, Cell dest) throws ImpossibleMoveException {
//        boolean valid = false;
//        Cell[] steps = new Cell[0];
//        int deltaX = Integer.compare(dest.x, source.x);
//        int deltaY = Integer.compare(dest.y, source.y);
//        int moveX = Math.abs(source.x - dest.x);
//        int moveY = Math.abs(source.y - dest.y);
//        int size = Math.max(moveX, moveY);
//        if (source.y == dest.y + moveY && source.x == dest.x
//                || source.y == dest.y - moveY && source.x == dest.x
//                || source.y == dest.y && source.x == dest.x + moveX
//                || source.y == dest.y && source.x == dest.x - moveX) {
//            steps = new Cell[size];
//            steps[0] = Cell.findCell(source.x + deltaX, source.y + deltaY);
//            for (int index = 1; index < steps.length; index++) {
//                steps[index] = Cell.findCell(steps[index - 1].x + deltaX, steps[index - 1].y + deltaY);
//            }
//            valid = true;
//        }
//        if (!valid) {
//            throw new ImpossibleMoveException();
//        }
//        return steps;
//    }