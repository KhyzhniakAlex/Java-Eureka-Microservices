package com.labs.maven.springBoot.SpringBootMSC.Model;

public class ExceptionMessage {

    private String message;
    private static String gap;
    private String status;

    public ExceptionMessage(String message, String status) {
        this.message = message;
        this.status = status;
    }

    public ExceptionMessage() {

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGap() {
        return gap;
    }

    public void setGap(String gap) {
        ExceptionMessage.gap = gap;
    }
}
