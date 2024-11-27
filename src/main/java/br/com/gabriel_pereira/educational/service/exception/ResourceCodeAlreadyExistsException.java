package br.com.gabriel_pereira.educational.service.exception;

public class ResourceCodeAlreadyExistsException extends RuntimeException{
    public ResourceCodeAlreadyExistsException(String msg) {
        super(msg);
    }
}