package com.example.teamservice.exception;

public class TeamNotFoundException extends RuntimeException{

    public TeamNotFoundException(String s) {
        super(s);
    }
}
