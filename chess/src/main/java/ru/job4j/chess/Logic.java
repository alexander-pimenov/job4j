package ru.job4j.chess;

import ru.job4j.chess.exceptions.FigureNotFoundException;
import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.exceptions.OccupiedWayException;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

/**
 * //TODO add comments.
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Logic {
    private final Figure[] figures = new Figure[32];
    private int index = 0;


    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }

    public boolean move(Cell source, Cell dest) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        boolean rst = false;                  //для проверки возможности перемещения фигуры
        int index = this.findBy(source);      //проверим, что фигура существует и сравним ее позицию, при ошибке получим -1
        try {
            if (index != -1) {
                //Ищем шаги, получаем массив ячеек пути перемещения фигуры
                Cell[] steps = this.figures[index].way(source, dest);
                //Проверяем занятость пути другими фигурами
                for (Cell step : steps) {
                    if (findBy(step) != -1 || this.findBy(dest) != -1) {
                        throw new OccupiedWayException("Путь зоблокирован. Попробуйте другой путь.");
                    }

                    //если нас все устраивает, то переместим фигуру простым копированием,
                    //иначе оставляем фигуру на месте
                    if (step.equals(dest)) {
                        rst = true;
                        this.figures[index] = this.figures[index].copy(dest);
                    }
                }
            } else {
                throw new FigureNotFoundException("Фигура не найдена!");
            }
        } catch (ImpossibleMoveException ime) {
            System.out.println(ime.getMessage());
        } catch (OccupiedWayException owe) {
            System.out.println(owe.getMessage());
        }
        return rst; //если фигура перенесена, то возвращаем true
    }

    //метод удаления фигуры после переноса/удаления с поля.
    public void clean() {
        for (int position = 0; position != this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }

    private int findBy(Cell cell) {
        int rst = -1;
        for (int index = 0; index != this.figures.length; index++) {
            if (this.figures[index] != null && this.figures[index].position().equals(cell)) {
                rst = index;
                break;
            }
        }
        return rst;
    }
}
