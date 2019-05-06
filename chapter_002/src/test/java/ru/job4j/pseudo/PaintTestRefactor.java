package ru.job4j.pseudo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class PaintTestRefactor {


    // поле содержит дефолтный вывод в консоль.
    private final PrintStream stdout = System.out;
    // буфер для результата, для хранения вывода
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void loadOutput() {
        System.out.println("execute before method");
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
        System.out.println("execute after method");
    }

    /**
     * В разделе is() используем  StringBuilder. Обратить внимание на различие синтаксиса с StringJoiner
     */
    @Test
    public void whenDrawSquare() {
        new Paint().draw(new Square());
        assertThat(
                this.out.toString(),
                is(
                        new StringBuilder()
                                .append("+ + + +")
                                .append(System.lineSeparator())
                                .append("+     +")
                                .append(System.lineSeparator())
                                .append("+     +")
                                .append(System.lineSeparator())
                                .append("+ + + +")
                                .append(System.lineSeparator())
                                .append(System.lineSeparator())
                                .toString()
                )
        );
    }

    /**
     * В разделе is() используем  StringJoiner. Обратить внимание на различие синтаксиса с StringBuilder
     */
    @Test
    public void whenDrawTriangle() {
        new Paint().draw(new Triangle());
        assertThat(
                this.out.toString(),
                is(
                        new StringJoiner(
                                System.lineSeparator(), "",
                                System.lineSeparator())
                                .add("   +   ")
                                .add("  + +  ")
                                .add(" + + + ")
                                .add("+ + + +")
                                .add("")
                                .toString()
                )
        );
    }
}