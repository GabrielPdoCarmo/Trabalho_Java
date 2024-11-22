package br.com.gabriel_pereira-main.educational.service.exception;

public class ResourceCodeAlreadyExistsException extends RuntimeException{
    public ResourceCodeAlreadyExistsException(String msg) {
        super(msg);
    }
}