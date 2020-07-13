package ru.job4j.io.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer3 {
    public static void main(String[] args){
        try (ServerSocket server = new ServerSocket(9000)) {
            boolean flag = false;
            while (!flag) {
                Socket socket = server.accept();
                Thread.sleep(100);
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(
                                     socket.getInputStream()))) {
                    String str = in.readLine();
                    if (!str.isEmpty()) {
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
                        out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                        out.write((answer + "\r\n").getBytes());
                        System.out.println(str);
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
