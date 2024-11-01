package com.shpp.p2p.cs.vtaboranskyi.assignment11;

public class ValidationException extends Throwable {

    private final String message;

    public ValidationException(String message) {
        this.message = message;
    }

    public void printCause() {
        System.out.println("\u001B[31m" + message + "\u001B[0m");
    }
}
