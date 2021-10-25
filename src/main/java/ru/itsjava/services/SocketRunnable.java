package ru.itsjava.services;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;


import java.net.Socket;



@AllArgsConstructor
public class SocketRunnable implements Runnable {
    private final Socket socket;


    @SneakyThrows
    @Override
    public void run() {
        MessageInputService serverReader = new MessageInputServiceImpl(socket.getInputStream());


        while (true) {
                System.out.println(serverReader.getMessage());

        }
    }
}