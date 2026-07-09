package com.example.remedioapp;

import java.util.List;

public class Remedio {
    private int id;
    private String nome;
    private String dosagem;
    private List<String> horarios;
    @Override
    public String toString() {
    return nome + " - " + dosagem + " - " + horarios;
}

    public Remedio(int id, String nome, String dosagem, List<String> horarios){
        this.id = id;
        this.nome = nome;
        this.dosagem = dosagem;
        this.horarios = horarios;
    }

    public int getId() {return id; }
    public String getNome() {return nome; }
    public String getDosagem () {return dosagem; }
    public List<String> getHorarios() {return horarios; }

    public void setNome(String nome) {this.nome = nome; }
    public void setDosagem (String dosagem) {this.dosagem = dosagem; }
    public void setHorarios(List<String> horarios) { this.horarios = horarios; }
}
