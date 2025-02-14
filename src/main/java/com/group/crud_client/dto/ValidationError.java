package com.group.crud_client.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends CustomError{

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Instant timestamp, Integer status, String error, String path) {
        super(timestamp, status, error, path);
    }

    public void addErrors(FieldMessage error) {
        errors.add(error);
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }
}
