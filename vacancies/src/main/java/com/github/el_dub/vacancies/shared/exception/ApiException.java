package com.github.el_dub.vacancies.shared.exception;

public abstract class ApiException extends RuntimeException{

    public ApiException(String message) {
        super(message);
    }
}
