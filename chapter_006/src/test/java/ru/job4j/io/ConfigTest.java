package ru.job4j.io;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ConfigTest {
    @Test
    public void whenPairWithoutComment() {
        String path = "./src/main/resources/app2.properties";
//        String path = ".\\src\\main\\resources\\app2.properties";
        Config config = new Config(path);
        config.load();
        //System.out.println(config.toString());
        assertThat(
                config.value("name"),
                is("Petr Arsentev")
        );
        assertThat(
                config.value("name1"),
                is("Alex")
        );
    }

    @Test
    public void whenPairWitComment() {
        String path = "./src/main/resources/app1.properties";
        //chapter_006/src/main/resources/app1.properties
//        String path = ".\\src\\main\\resources\\app1.properties";
        Config config = new Config(path);
        config.load();
        //System.out.println(config.toString());
        assertThat(config.value("hibernate.connection.url"),
                is("jdbc:postgresql://127.0.0.1:5432/trackstudio"));
        assertThat(config.value("hibernate.connection.username"), is("postgres"));
    }

}