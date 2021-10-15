package ru.itsjava.services;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import ru.itsjava.domain.User;

import java.net.Socket;



@AllArgsConstructor
public class SocketRunnable implements Runnable {
    private final Socket socket;
    public User user;

    @SneakyThrows
    @Override
    public void run() {
        MessageInputService serverReader = new MessageInputServiceImpl(socket.getInputStream());


        while (true) {
            String input = serverReader.getMessage();
            if (input.startsWith(user.getLogin())) {
                System.out.println(input);
            }
        }
    }
}