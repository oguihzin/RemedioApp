package com.example.remedioapp;

import javafx.application.Application;

public class Launcher {
    public static void main(String[] args) {
        DatabaseHelper.inicializarBanco();
        Application.launch(HelloApplication.class, args);
    }
}