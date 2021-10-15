package ru.itsjava.services;

import lombok.SneakyThrows;
import ru.itsjava.domain.User;

import java.io.PrintWriter;
import java.net.Socket;

public class ClientServiceImpl implements ClientService {
    public final static int PORT = 8081;
    public final static String HOST = "localhost";
    public User user;



    @SneakyThrows
    @Override
    public void start() {
        Socket socket = new Socket(HOST, PORT);
        if (socket.isConnected()) {
            new Thread(new SocketRunnable(socket, user)).start();

            PrintWriter serverWriter = new PrintWriter(socket.getOutputStream());
            MessageInputService messageInputService = new MessageInputServiceImpl(System.in);

                System.out.println("Введите свой логин:");
                String login = messageInputService.getMessage();


                System.out.println("Введите свой пароль:");
                String password = messageInputService.getMessage();

//            !autho!login:password
                serverWriter.println("!autho!" + login + ":" + password);
                serverWriter.flush();


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
    }
