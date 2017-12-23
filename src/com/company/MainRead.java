package com.company;

import com.company.fildname.DisplayName;
import com.company.fildname.FieldName;
import validation.Length;
import validation.NotBlank;
import validation.Password;
import validation.Validator;
import java.io.*;
import java.util.Map;
import java.util.NoSuchElementException;

public class MainRead {
    @DisplayName(name="Insert name")
    @NotBlank
    @Length(minValue = 2,maxValue = 50)
    public static String name;
    @DisplayName(name="Insert password")
    @Password
    public static String password;

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
        try (BufferedReader ois = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println(FieldName.getFieldName(MainRead.class.getField("name")));
            name = ois.readLine();
            new Validator().DoValidateField(MainRead.class.getField("name"),name);
            System.out.println(FieldName.getFieldName(MainRead.class.getField("password")));
            password = ois.readLine();
            new Validator().DoValidateField(MainRead.class.getField("password"),password);
            //   throw new RuntimeException("User allredy exists");
            try {
                User user = User.getUsers().entrySet().stream().filter(x->x.getKey().equals(name)&&x.getValue().password.equals(password) ).findFirst().get().getValue();
                System.out.println(user);
            }
            catch (NoSuchElementException e)
            {
                System.out.println("Элемент не найден");
            }

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }


}