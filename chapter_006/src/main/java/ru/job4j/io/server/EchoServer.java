package ru.job4j.io.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Класс java.net.Socket позволяет получить сообщение и передать его.
 */
public class EchoServer {
    public static void main(String[] args) throws IOException {
        /*Создаем Сервер.
         * ServerSocket создает сервер.
         * 9000 - это порт. По умолчанию адрес будет localhost*/
        try (ServerSocket server = new ServerSocket(9000)) {
            while (true) {
                /*server.accept() - ожидает, когда к нему обратиться клиент.
                Программа переходит в режим ожидания.*/
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                        /*В программе читается весь входной поток.*/
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    /*В ответ мы записываем строчку.*/
                    String str;
                    while (!(str = in.readLine()).isEmpty()) {
                        System.out.println("=====начало цикла=====");
                        System.out.println(str);
                        System.out.println("=====конец цикла=====");
                    }
                    out.write("HTTP/1.1 200 OK\r\n\\".getBytes());
                }
            }
        }
    }
}
