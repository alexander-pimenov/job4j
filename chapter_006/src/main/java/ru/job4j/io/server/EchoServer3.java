package ru.job4j.io.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer3 {
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) { //Создав объект типа  ServerSocket необходимо выяснить, что с сервером кто-то хочет соединиться.
            boolean flag = false;
            System.out.println("Server started");
            while (!flag) {
                /*server.accept() - ожидает, когда к нему обратиться клиент.
                 * Здесь программа переходит в режим ожидания.
                 * Искомый ждёт пока кто-либо не захочет подсоединится к нему,
                 * и когда это происходит возвращает объект типа Socket, то есть
                 * воссозданный клиентский сокет. И вот когда сокет клиента создан
                 * на стороне сервера, можно начинать двухстороннее общение. */
                Socket socket = server.accept();
                Thread.sleep(100);
                try (BufferedWriter out =
                             new BufferedWriter(
                                     new OutputStreamWriter(
                                             socket.getOutputStream()));
                     BufferedReader in =
                             new BufferedReader(
                                     new InputStreamReader(
                                             socket.getInputStream()))) {

                    String str = in.readLine(); //Читаем первую строку сообщения от Клиента
                    System.out.println(str);
                    //Парсим первую строку запроса
                    if (str != null && !str.isEmpty()) { //
                        String[] line = str.split("\\s");
                        int index = line[1].lastIndexOf('=');
                        String argument = line[1].substring(index + 1);
                        String answer;
                        if ("Hello".equalsIgnoreCase(argument)) {
                            answer = "Hello, dear friend.";
                        } else if ("Exit".equalsIgnoreCase(argument)) {
                            answer = "Exit. Server shut down.";
                            flag = true;
                        } else {
                            answer = argument;
                        }

                        /*Вывод информации о пришедшем сообщении, о request от Клиента.*/
                        System.out.println("=====start of request information=====");
                        while (!str.isEmpty()) {
                            System.out.println(str);
                            str = in.readLine();
                        }
                        System.out.println("=====end of request information=====");

                        /*Для того, чтобы браузер выводил ответ сервера,
                         * оформим ответ в соответствии с правилами ответа HTTP протокола.
                         */
                        String response =
                                "HTTP/1.1 200 OK\r\n"
                                        + "Content-Type: text/html\r\n"
                                        + "\r\n" //пустая строка
                                        + "<html>\r\n"
                                        + "<head>\r\n"
                                        + "</head>\r\n"
                                        + "<body>\r\n"
                                        + "<h2>Hello from Server.</h2>\r\n"
                                        + "<p>" + answer + "</p>\r\n"
                                        + "<p> #This is the server response# </p>\r\n"
                                        + "</body>\r\n"
                                        + "</html>\r\n";
                        out.write(response); //Отправляет ответ(response) Клиенту
                        out.flush();

//                        out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
//                        out.write((answer + "\r\n").getBytes());
//                        System.out.println(str);
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
