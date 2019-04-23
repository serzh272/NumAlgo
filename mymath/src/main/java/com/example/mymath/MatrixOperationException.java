package com.example.mymath;

public class MatrixOperationException extends Exception {
    public MatrixOperationException() {
    }

    public MatrixOperationException(String message) {
        super(message);
    }

    public MatrixOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public MatrixOperationException(Throwable cause) {
        super(cause);
    }

}
