package com.company;

import java.io.*;
import java.util.Map;
import java.util.NoSuchElementException;

public class MainRead {

    public static String FILE = "users.dat";

    public static void main(String[] args) {
        try {
            readFile();
            findToFile();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static void readFile() throws IOException, ClassNotFoundException {

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE))) {
            User.setUsers((Map<String, User>) ois.readObject());
        }

    }

    private static void findToFile() throws IOException {
        System.out.println("Введите имя:");
        try (BufferedReader ois = new BufferedReader(new InputStreamReader(System.in))) {
            String name = ois.readLine();
            try {
                User user = User.getUsers().entrySet().stream().filter(x->x.getKey().equals(name)).findFirst().get().getValue();
                System.out.println(user);
            }
            catch (NoSuchElementException e)
            {
                System.out.println("Элемент не найден");
            }

        }
    }
}