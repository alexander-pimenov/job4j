package ru.job4j.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CountingFunctionsTest {

    @Test
    public void whenLinearFunctionThenLinearResult() {
        CountingFunctions function = new CountingFunctions();
        //Линейная функция: y=2*x+1
        List<Double> result = function.diapason(5, 8, x -> 2 * x + 1);

        //2*5+1=11
        //2*6+1=13
        //2*7+1=15
        List<Double> expected = Arrays.asList(11D, 13D, 15D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenQuadraticFunctionThenQuadraticResult() {
        CountingFunctions function = new CountingFunctions();
        //Простейшая квадратичная функция: y=x*x
        List<Double> result = function.diapason(5, 8, x -> x * x);

        //5*5=25
        //6*6=36
        //7*7=49
        List<Double> expected = Arrays.asList(25D, 36D, 49D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenLogarithmicFunctionThenLogarithmicResult() {
        CountingFunctions function = new CountingFunctions();
        //Логарифмическая функция:  y=log(x)
        List<Double> result = function.diapason(5, 8, x -> Math.log(x));

        //log(5)=1.6094379124341003
        //log(6)=1.791759469228055
        //log(7)=1.9459101490553132
        List<Double> expected = Arrays.asList(1.6094379124341003, 1.791759469228055, 1.9459101490553132);
        assertThat(result, is(expected));
    }

}