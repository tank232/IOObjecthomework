package com.company.validation;

import com.company.fildname.FieldName;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Validator {


    List<FieldException> listException;

    public Validator() {
        listException = new ArrayList<>();
    }

    public void doValidateFields(Field[] fields, Object o) throws IllegalAccessException {
        for (Field field : fields) {
            {
                field.setAccessible(true);
                Object value = field.get(o);
                DoValidateField(field, value);
            }

        }
    }




    public void DoValidateField(Field field, Object o) {
        try {
            if (field.isAnnotationPresent(Length.class)) {
                lengthValidator((String) o, field.getAnnotation(Length.class).minValue(), field.getAnnotation(Length.class).maxValue());
            }
        } catch (RuntimeException e) {
            listException.add(new FieldException(e, field));
        }

        try {
            if (field.isAnnotationPresent(Email.class)) {
                emailValidator((String) o);
            }
        } catch (RuntimeException e) {
            listException.add(new FieldException(e, field));
        }

        try {
            if (field.isAnnotationPresent(Password.class)) {
                PasswordValidator((String) o);
            }
        } catch (RuntimeException e) {
            listException.add(new FieldException(e, field));
        }

        try {
        if (field.isAnnotationPresent(NotBlank.class)) {
                notBlankValidator((String) o);
            }
        } catch (RuntimeException e) {
            listException.add(new FieldException(e, field));
        }


        for (FieldException fe : listException) {
            System.out.println(FieldName.getFieldName(fe.getField()) + " - " + fe.getException().getMessage());
        }
        if(listException.size()>0)
        {
            throw new RuntimeException("Validator exception");
        }

    }


    private void lengthValidator(String s,
                                 int min,
                                 int max) {
        if (s == null && min == 0) {
            return;
        }

        if (s == null) {
            throw new RuntimeException("Length is not valid. Value is null");
        }

        if (s.length() <= max &&
                s.length() >= min) {
            return;
        }

        throw new RuntimeException("Length is not valid. Length does not match/");
    }

    private void emailValidator(String s) {
        if (s == null) {
            return;
        }
        if (s.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")) {
            return;
        }

        throw new RuntimeException("email  exception");
    }

    private void notBlankValidator(String s) {
        if (s == null) {
            throw new RuntimeException("not blank exception");
        }
        if (s.matches("^[a-zA-Z_\\.]{1,50}$")) {
            return;
        }

        throw new RuntimeException("not blank exception");
    }


    private void PasswordValidator(String s) {
        if (s == null) {
            throw new RuntimeException("Password exception");
        }
        if (s.matches("(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$")) {
            return;
        }

        throw new RuntimeException("Password exception");
    }

}


