package ru.job4j.loop;

import org.junit.Test;
//Этот класс выполняет такие же задачи, как и класс StringBuilder, но он имеет более удобный интерфейс использования.
import java.util.StringJoiner;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;
/**
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class PaintTest {
    @Test
    public void whenPyramid4Right() {
        Paint paint = new Paint();
        String rst = paint.pyramid(4);
        System.out.println(rst);
        assertThat(rst,
                is(
                        new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                                .add("   ^   ")
                                .add("  ^^^  ")
                                .add(" ^^^^^ ")
                                .add("^^^^^^^")
                                .toString()
                )
        );
    }
}

