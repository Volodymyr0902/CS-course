package com.shpp.p2p.cs.vtaboranskyi.assignment11;

/**
 * This class represents an exception which accumulates user input mistakes
 * and should be thrown if it doesn't match specific conditions.
 */
public class ValidationException extends Throwable {

    /**
     * The message describing particular exception.
     */
    private final String message;

    public ValidationException(String message) {
        this.message = message;
    }

    /**
     * Prints the reason why the program can't continue the execution.
     */
    public void printCause() {
        System.out.println("\u001B[31m" + message + "\u001B[0m");
    }
}
