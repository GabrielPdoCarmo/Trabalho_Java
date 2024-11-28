package br.com.gabriel_pereira.educational.controller.exception.model;

import java.io.Serializable;

public class MensagemCampo implements Serializable {

    private String fieldName;
    private String message;

    public MensagemCampo() {

    }

    public MensagemCampo(String fieldName, String message) {
        super();
        this.fieldName = fieldName;
        this.message = message;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
