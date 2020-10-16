package ru.job4j.tdd;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class GeneratorTest {

    @Test
    @Ignore
    public void testProduce() {
        Generator generator = new SimpleGenerator();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> values = Map.of("name", "Petr Arsentev", "subject", "you");
        String result = generator.produce(template, values);
        String expected = "I am a Petr Arsentev, Who are you?";
        assertThat(result, is(expected));
    }

    @Test(expected = IllegalStateException.class)
    @Ignore
    public void whenArgsInTheMapNotEqualInTheTemplateThenAnException() {
        Generator generator = new SimpleGenerator();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> values = Map.of("na", "Petr Arsentev", "subj", "you");
        String result = generator.produce(template, values);
    }

    @Test(expected = IllegalArgumentException.class)
    @Ignore
    public void whenMoreArgsInTheMapThenAnException() {
        Generator generator = new SimpleGenerator();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> values = Map.of(
                "name", "Petr Arsentev",
                "subject", "you",
                "country", "Russia"
        );
        String result = generator.produce(template, values);
    }

    @Test(expected = IllegalArgumentException.class)
    @Ignore
    public void whenArgsNotEnoughInMap() {
        Generator generator = new SimpleGenerator();
        String template = "I am a ${name}, Who are ${subject}?";
        Map map = Map.of(
                "name", "Ivan"
        );
        String out = generator.produce(template, map);
    }

}