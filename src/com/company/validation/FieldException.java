package com.company.validation;

import java.lang.reflect.Field;

class FieldException
{
    Exception exception;
    Field field;

    public FieldException(Exception exception, Field field) {
        this.exception = exception;
        this.field = field;
    }

    public Exception getException() {
        return exception;
    }

    public Field getField() {
        return field;
    }
}