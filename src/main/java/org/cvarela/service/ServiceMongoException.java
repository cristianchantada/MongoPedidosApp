package org.cvarela.service;

public class ServiceMongoException extends RuntimeException{
    public ServiceMongoException(String message) {
        super(message);
    }

    public ServiceMongoException(String message, Throwable cause) {
        super(message, cause);
    }
}
