package ru.job4j.io;

import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class AnalizyTest {
    private Analizy analizy;

    @Before
    public void setUp() {
        analizy = new Analizy();
        try (PrintWriter out = new PrintWriter(new FileOutputStream("server.log"))) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("200 10:59:01");
            out.println("500 11:01:02");
            out.println("200 11:02:02");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenServerUnavailable() {
        analizy.unavailable("server.log", "target.csv");

        try (BufferedReader reader = new BufferedReader(new FileReader("target.csv"))) {
            assertThat(reader.readLine(), is("10:57:01;10:59:01"));
            assertThat(reader.readLine(), is("11:01:02;11:02:02"));
            assertNull(reader.readLine());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}