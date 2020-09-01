package ru.job4j.io.chat;

import java.io.*;
import java.net.URL;
import java.util.*;

public class Chat {
    private Scanner input = new Scanner(System.in);
    private static final String LN = System.lineSeparator();
    private static final String STOP = "стоп";
    private static final String FINISH = "закончить";
    private static final String CONTINUE = "продолжить";

    public static void main(String[] args) {

        Chat chat = new Chat();
        chat.doChat();
    }

    public void doChat() {
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
            }
        } while (!userMessage.equalsIgnoreCase(FINISH));
    }

    private void writeLog(String who, String message) {
        String log = "log.txt";
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(log, true))) {
            bw.write(new Date() + " " + who + ": " + message + LN);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getPhrase() {
        List<String> phrases = new ArrayList<>();
        URL text = Chat.class.getResource("/response_phrases.txt");
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
        Random rnd = new Random();
        return phrases.get(rnd.nextInt(phrases.size()));
    }
}
