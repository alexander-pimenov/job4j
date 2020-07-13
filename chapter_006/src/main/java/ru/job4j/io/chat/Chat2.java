package ru.job4j.io.chat;

import java.io.*;
import java.net.URL;
import java.util.*;

public class Chat2 {
    private Scanner input = new Scanner(System.in);
    private static final String LN = System.lineSeparator();
    private static final String STOP = "стоп";
    private static final String FINISH = "закончить";
    private static final String CONTINUE = "продолжить";
    private List<String> phrases = new ArrayList<>(); //храним фразы ответов
    private Random rnd = new Random();
    private List<Entry> listEntry = new ArrayList<>(); //храним запись сообщений чата

    public static void main(String[] args) {

        Chat2 chat = new Chat2();
        chat.doChat();
    }

    private void doChat() {
        initPhrases();
        StringJoiner joiner = new StringJoiner(LN); //указал разделитель
        joiner.add("Добрый день! Я - Бот, Ваш помощник!");
        joiner.add("Введите Ваш вопрос.");
        joiner.add("<<<<< Чтобы остановить помощника, введите «Стоп» и он замолчит.");
        joiner.add("Введите «Продолжить» и он продолжит общение.");
        joiner.add("Введите «Закончить» для выхода из программы. >>>>>");
        System.out.println(joiner);

        boolean chatWithBot = true;
        String userMessage;
        String botMessage = "Давайте начнем!";
        System.out.println(botMessage);
        writeLog("Bot", botMessage);

        do {
            userMessage = input.nextLine();
            writeLog("User", userMessage);
            if (FINISH.equalsIgnoreCase(userMessage)) {
                botMessage = "До свидания!";
                System.out.println(botMessage);
                writeLog("Bot", botMessage);
                chatWithBot = false;
            }
            if (STOP.equalsIgnoreCase(userMessage)) {
                chatWithBot = false;
                botMessage = "Я замолкаю. Если вы хотите продолжить, введите «Продолжить».";
                System.out.println(botMessage);
                writeLog("Bot", botMessage);
            }
            if (chatWithBot) {
                botMessage = getPhrase();
                System.out.println(botMessage);
                writeLog("Bot", botMessage);
            }
            if (CONTINUE.equalsIgnoreCase(userMessage)) {
                chatWithBot = true;
                botMessage = "Продолжаем разговор!";
                System.out.println(botMessage);
                writeLog("Bot", botMessage);

            }
        } while (!userMessage.equalsIgnoreCase(FINISH));
        writeToLogFile();
    }

    /*Метод записывающий список сообщений чата в лог-файл на диске.*/
    private void writeToLogFile() {
        String log = "log2.txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(
                log, true))) {
            for (Entry entry : listEntry) {
                bw.write(entry.toString());
                bw.write(LN);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*Метод сохраняющий в список сообщения из чата.*/
    private void writeLog(String who, String message) {
        listEntry.add(new Entry(new Date(), who, message));
    }

    /*Метод вычитывающий весь текстовый файл фраз-ответов в список.*/
    private List<String> initPhrases() {
        URL text = Chat2.class.getResource("/response_phrases.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(
                text.getFile()))
        ) {
            String line;
            while ((line = br.readLine()) != null) {
                phrases.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    /*Метод достающий из списка фразу-ответ в случайном порядке.*/
    private String getPhrase() {
        return phrases.get(rnd.nextInt(phrases.size()));
    }
}

/*Класс, хранящий в себе данные для записи в лог-файл*/
class Entry {
    private Date date;
    private String who;
    private String phrase;

    Entry(Date date, String who, String phrase) {
        this.date = date;
        this.who = who;
        this.phrase = phrase;
    }

    @Override
    public String toString() {
        return date
                + ", < " + who + " >"
                + " : '" + phrase + '\'';
    }
}

