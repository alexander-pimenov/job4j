package ru.job4j.condition;

import org.junit.Test;

import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;


public class TriangleTest {

    @Test
    public void whenAreaSetThreePointsThenTriangleArea() {
        // Создаем объект треугольник.
        Triangle triangle = new Triangle();
        // Вычисляем площадь.
        double result = triangle.area(0, 0, 0, 2, 2, 0);
        // Задаем ожидаемый результат.
        double expected = 2D;
        //Проверяем результат и ожидаемое значение.
        assertThat(result, closeTo(expected, 0.1));
    }


/* Вычисляем площадь по координатам:
    @Test
    public void whenAreaSetThreePointsThenTriangleArea() {
        // Создаем объект треугольник.
        Triangle triangle = new Triangle();
        // Вычисляем площадь.
           // double result = triangle.area(0, 0, 0, 2, 2, 0);
        double result = triangle.area(0, 0, 0, 2, 3, 0);
        // Задаем ожидаемый результат.
           //double expected = 2D;
        double expected = result;
        //Проверяем результат и ожидаемое значение.
        assertThat(result, closeTo(expected, 0.1));
        System.out.println("area = "+result);
    }
*/

}
