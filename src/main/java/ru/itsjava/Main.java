package ru.itsjava;


import ru.itsjava.services.ClientService;
import ru.itsjava.services.ClientServiceImpl;
import ru.itsjava.services.SecurityEncryptor;
import ru.itsjava.services.SecurityEncryptorImpl;

public class Main {


    public static void main(String[] args){

        ClientService clientService = new ClientServiceImpl();
        clientService.start();
    }
}
