package com.company;
import java.io.*;
import java.util.Random;

public class MainWrite {
    public static String FILE="Users.dat";
    public static void main(String[] args) {
        generateRandom();
        writeToFile();
    }

    private static void generateRandom() {
        Random rand = new Random();
        for (int i = 0; i < 10000;) {
            try {
                User user=new User("User" + rand.nextInt(20000),
                        String.format("%8d", rand.nextInt(100000000)),
                        "firstName" + rand.nextInt(100),
                        "SecondName" + rand.nextInt(100),
                        "mail" + rand.nextInt(100) + "@gmail.com");
                i++;
                System.out.println(user.getUserName() +" "+ user.getPassword());
            }
            catch (Exception e) {System.out.println("fail");};
        }
    }



    private static void writeToFile() {

        try(ObjectOutputStream oos= new ObjectOutputStream( new FileOutputStream(FILE)) ){
                oos.writeObject(User.getUsers());
            }
         catch (IOException e) {
            e.printStackTrace();
        }
    }
}
