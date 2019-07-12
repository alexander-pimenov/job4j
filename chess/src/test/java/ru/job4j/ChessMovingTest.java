package ru.job4j;

import org.junit.Test;
import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.black.*;
import ru.job4j.chess.figures.white.KnightWhite;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ChessMovingTest {
    /**
     * Проверка движения слона
     */
    @Test
    public void whenBishopBlackMove() throws ImpossibleMoveException {
        BishopBlack bishopBlack = new BishopBlack(Cell.C8);
        Cell[] steps = new Cell[]{Cell.D7, Cell.E6};
        assertThat(bishopBlack.way(Cell.C8, Cell.E6), is(steps));
    }
    /**
     * Проверка движения слона по длинной диагонали
     */

    @Test
    public void testBishop() {
        BishopBlack bishop = new BishopBlack(Cell.A1);
        Cell[] actual = bishop.way(bishop.position(), Cell.H8);
        Cell[] expected = {Cell.B2, Cell.C3, Cell.D4, Cell.E5, Cell.F6, Cell.G7, Cell.H8};
        assertThat(actual, is(expected));
    }

    /**
     * Проверка движения пешки, на одну клетку вперед
     */
    @Test
    public void whenPawnBlackMove() throws ImpossibleMoveException {
        PawnBlack pawnBlack = new PawnBlack(Cell.C7);
        Cell[] steps = new Cell[]{Cell.C6};
        assertThat(pawnBlack.way(Cell.C7, Cell.C6), is(steps));
    }

    /**
     * Проверка движения ладьи
     */
    @Test
    public void whenRookBlackMove() throws ImpossibleMoveException {
        RookBlack rookBlack = new RookBlack(Cell.A8);
        Cell[] steps = new Cell[]{Cell.A7, Cell.A6};
        assertThat(rookBlack.way(Cell.A8, Cell.A6), is(steps));
    }

    /**
     * Вызов исключения в случае неправильного движения ладьи
     */
    @Test(expected = ImpossibleMoveException.class)
    public void whenRookBlackWrongMove() throws ImpossibleMoveException {
        RookBlack rookBlack = new RookBlack(Cell.A8);
        Cell[] steps = new Cell[]{Cell.A7, Cell.A6, Cell.B6};
        assertThat(rookBlack.way(Cell.A8, Cell.B6), is(steps));
    }

    /**
     *  Проверка движения короля
     */
    @Test
    public void whenKingBlackMove() throws ImpossibleMoveException {
        KingBlack kingBlack = new KingBlack(Cell.E8);
        Cell[] steps = new Cell[] {Cell.F7};
        assertThat(kingBlack.way(Cell.E8, Cell.F7), is(steps));
    }

    /**
     *  Проверка движения ферьзя
     */
    @Test
    public void whenQueenBlackMove() throws ImpossibleMoveException {
        QeenBlack queenBlack = new QeenBlack(Cell.D8);
        Cell[] steps = new Cell[] {Cell.D7, Cell.D6, Cell.D5};
        assertThat(queenBlack.way(Cell.D8, Cell.D5), is(steps));
    }

    /**
     *  Проверка движения коня
     */
    @Test
    public void whenKnightBlackMove() throws ImpossibleMoveException {
        KnightBlack knightBlack = new KnightBlack(Cell.B8);
        Cell[] steps = new Cell[] {Cell.C6};
        assertThat(knightBlack.way(Cell.B8, Cell.C6), is(steps));
    }

    @Test
    public void whenKnightWhiteMove() throws ImpossibleMoveException {
        KnightWhite knightWhite = new KnightWhite(Cell.G1);
        Cell[] steps = new Cell[] {Cell.H3};
        assertThat(knightWhite.way(Cell.G1, Cell.H3), is(steps));
    }

    /**
     *  Вызов исключения в случае неправильного движения коня
     */
    @Test (expected = ImpossibleMoveException.class)
    public void whenKnightBlackWrongMove() throws ImpossibleMoveException {
        KnightBlack knightBlack = new KnightBlack(Cell.B8);
        Cell[] steps = new Cell[] {Cell.C7, Cell.D6};
        assertThat(knightBlack.way(Cell.B8, Cell.D6), is(steps));
    }

}
