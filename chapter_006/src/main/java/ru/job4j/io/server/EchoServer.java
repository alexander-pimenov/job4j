package ru.job4j.io.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Класс java.net.Socket позволяет получить сообщение и передать его.
 * Тестирование класса EchoServer производим с помощью cUrl.
 * cUrl используем в качестве клиента.
 * Запустив cUrl, мы вводим команду:
 * curl -i http://localhost:9000/?msg=Hello
 * В консоли видим пришедшие на сервер сообщения от клиента cUrl.
 */
public class EchoServer {
    public static void main(String[] args) throws IOException {
        /*Создаем Сервер.
         * ServerSocket создает сервер.
         * 9000 - это порт. По умолчанию адрес будет localhost*/
        try (ServerSocket server = new ServerSocket(9000)) {
            System.out.println("Server started");
            while (true) {
                /*server.accept() - ожидает, когда к нему обратиться клиент.
                 * Программа переходит в режим ожидания.
                 * Сервер ждёт пока кто-либо не захочет подсоединится к нему,
                 * и когда это происходит возвращает объект типа Socket, то есть
                 * воссозданный клиентский сокет. И вот когда сокет клиента создан
                 * на стороне сервера, можно начинать двухстороннее общение. */
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                        /*В программе читается весь входной поток.*/
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    /*В ответ мы записываем строчку.*/
                    String str;
                    str = in.readLine();
                    while (str != null && !str.isEmpty()) {
                        System.out.println("=====начало цикла=====");
                        System.out.println(str);
                        System.out.println("=====конец цикла=====");
                        str = in.readLine();
                    }
                    out.write("HTTP/1.1 200 OK\r\n\\".getBytes());
                }
            }
        }
    }
}
