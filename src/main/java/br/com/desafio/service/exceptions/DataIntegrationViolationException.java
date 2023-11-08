package br.com.desafio.service.exceptions;

public class DataIntegrationViolationException extends RuntimeException{
    public DataIntegrationViolationException(String message) {
        super(message);
    }
}
