package com.tinoco.userapi.error;

public class AccessException extends RuntimeException {

    public AccessException(String msj) {
        super(msj);
    }
}
