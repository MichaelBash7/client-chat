package ru.itsjava.services;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;


import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientServiceImpl implements ClientService {
    public final static int PORT = 8081;
    public final static String HOST = "localhost";


    @SneakyThrows
    @Override
    public void start() {
        Socket socket = new Socket(HOST, PORT);
        if (socket.isConnected()) {
            new Thread(new SocketRunnable(socket)).start();

            PrintWriter serverWriter = new PrintWriter(socket.getOutputStream());
            MessageInputService messageInputService = new MessageInputServiceImpl(System.in);
            SecurityEncryptor securityEncryptor = new SecurityEncryptorImpl();


            printMenu();
            System.out.println("Введите номер меню");
            int menuNum = Integer.parseInt(messageInputService.getMessage());

            if (menuNum == 1) {
                System.out.println("Вы выбрали авторизацию");
//            Авторизация:
                System.out.println("Введите свой логин:");
                String login = messageInputService.getMessage();

                System.out.println("Введите свой пароль:");
                String password = messageInputService.getMessage();
                String encryptedPassword = securityEncryptor.md5Encrypt(password);

//            !autho!login:password
                serverWriter.println("!autho!" + login + ":" + encryptedPassword);
                serverWriter.flush();
            } else if (menuNum == 2) {
                System.out.println("Вы выбрали регистрацию");
//            Регистрация:
                System.out.println("Придумайте свой логин:");
                String regLogin = messageInputService.getMessage();

                System.out.println("Придумайте свой пароль:");
                String regPassword = messageInputService.getMessage();
                String encryptedPassword = securityEncryptor.md5Encrypt(regPassword);

//            !reg!login:password
                serverWriter.println("!reg!" + regLogin + ":" + encryptedPassword);
                serverWriter.flush();

            }

                while (true) {
                    String consoleMessage = messageInputService.getMessage();
                    serverWriter.println(consoleMessage);
                    serverWriter.flush();


                    if (consoleMessage.contentEquals("exit")) {
                        System.exit(0);
                    }
                }
            }
        }


    @Override
    public void printMenu() {
        System.out.println("1 - Авторизация; 2 - Регистрация");
    }
}