package ru.job4j.io.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Сервер работает пока не будет введено Bye.
 * При вводе Bye вызываем server.close().
 * Тестирование класса EchoServer производим с помощью cUrl.
 * cUrl используем в качестве клиента.
 * Запустив cUrl, мы вводим команду:
 * curl -i http://localhost:9000/?msg=Hello
 * В консоли видим пришедшие на сервер сообщения от клиента cUrl.
 */

public class EchoServer2 {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            System.out.println("Server started");
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str = in.readLine();
                    System.out.println(str);
                    if (str.startsWith("GET") && ((str.contains("msg=Bye")) || str.contains("msg=bye"))) {
                        out.write("Server close.\r\n".getBytes());
                        System.out.println("Server close.");
                        server.close();
                    } else {
                        /*Вычитывает оставшиеся строчки из потока in и выводит их*/

                        while (str != null && !str.isEmpty()) {
                            str = in.readLine();
                            System.out.println(str);
                        }
                    }
                    out.write("HTTP/1.1 200 OK\r\n\\".getBytes());
                }
            }
        }
    }
}
