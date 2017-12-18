package com.company;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class User implements Serializable{
    String userName;
    String password;
    String firstName;
    String secondName;
    String email;

    private static Map<String,User> Users= new HashMap<>();

    public  User(String userName, String password, String firstName, String secondName,String email) throws Exception {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email=email;
        if (Users.get(this.userName)==null)
        { Users.put(this.userName, this);}
         else
        {
            Exception e = new Exception();
            throw e;
        }
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public static Map<String, User> getUsers() {
        return Users;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static void setUsers(Map<String, User> users) {
        Users = users;
    }
}
