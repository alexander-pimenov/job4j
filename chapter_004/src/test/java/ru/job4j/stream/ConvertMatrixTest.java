package ru.job4j.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class ConvertMatrixTest {

    @Test
    public void whenIntegerMatrixThenIntegerList() {
        Integer[][] matrix = {{1, 2, 3}, {4, 5, 6}};
        ConvertMatrix convertMatrix = new ConvertMatrix();
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6);
        assertThat(convertMatrix.convert(matrix), is(expected));
    }
}
