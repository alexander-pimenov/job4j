package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.tracker.start.StubInput;
import ru.job4j.tracker.start.ValidateInput;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * //TODO add comments.
 *
 * @author Alexander Pimenov (pimalex1978@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class ValidateInputTest {

    // буфер для хранения данных вывода, результата
    private final ByteArrayOutputStream mem = new ByteArrayOutputStream();
    // поле ссылки на дефолтный вывод в консоль
    private final PrintStream out = System.out;

    @Before
    public void loadMem() {
        System.out.println("execute before method");
        System.setOut(new PrintStream(this.mem));
    }

    @After
    public void loadSys() {
        System.setOut(this.out);
        System.out.println("execute after method");
    }

    @Test
    public void whenInvalidInput() {
        ValidateInput input = new ValidateInput(
                new StubInput(Arrays.asList("invalid", "1"))
        );
        input.ask("Enter", Arrays.asList(1));
        assertThat(
                this.mem.toString(),
                is(
                        String.format("Please enter correct data again.%n")
                )
        );
    }


    @Test
    public void whenInvalidInput10() {
        ValidateInput input = new ValidateInput(
                new StubInput(Arrays.asList("-9", "1"))
        );
        input.ask("Enter", Arrays.asList(1));
        assertThat(
                this.mem.toString(),
                is(
                        String.format("Please select key from menu.%n")
                )
        );
    }
}
