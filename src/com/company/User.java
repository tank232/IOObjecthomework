package com.company;

import com.company.fildname.DisplayName;
import com.company.fildname.FieldName;
import validation.*;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class User implements Serializable{
    @DisplayName(name="User name")
    @NotBlank
    @Length
    String userName;
    @DisplayName(name="Password")
    @Password
    String password;
    @DisplayName(name="First name")
    @Length(minValue = 2,maxValue = 50)
    String firstName;
    @DisplayName(name="Second name")
    @Length(minValue = 2,maxValue = 50)
    String secondName;
    @DisplayName(name="Email")
    @Email
    String email;

    private static Map<String,User> users = new HashMap<>();

    public  User(String userName, String password, String firstName, String secondName,String email) throws Exception {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email=email;
        new Validator().doValidateFields(this.getClass().getFields(),this);
        if (users.get(this.userName) ==null)
        {
            users.put(this.userName, this);
        }
        else {
            throw new RuntimeException("User allredy exists");
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
        Field[] Fields = this.getClass().getDeclaredFields();

        return FieldName.getFieldName(Fields[0])+ userName + '\'' +
                FieldName.getFieldName(Fields[1])+ password + '\'' +
                FieldName.getFieldName(Fields[2]) + firstName + '\'' +
                FieldName.getFieldName(Fields[3])+ secondName + '\'' +
                FieldName.getFieldName(Fields[4]) + email + '\'' +
                '}';
    }

    public static Map<String, User> getUsers() {
        return users;
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
        User.users = users;
    }
}
