package com.example.remedioapp;

import java.time.LocalDate;
import java.time.LocalTime;

public class RegistroTomada {
    private int id;
    private int remedioId;
    private LocalDate data;
    private LocalTime horarioPrevisto;
    private boolean tomado;
    private LocalTime horarioReal;

    public RegistroTomada(int id, int remedioId, LocalDate data, LocalTime horarioPrevisto, boolean tomado, LocalTime horarioReal) {
        this.id = id;
        this.remedioId = remedioId;
        this.data = data;
        this.horarioPrevisto = horarioPrevisto;
        this.tomado = tomado;
        this.horarioReal = horarioReal;
    }

    public int getId() { return id; }
    public int getRemedioId() { return remedioId; }
    public LocalDate getData() { return data; }
    public LocalTime getHorarioPrevisto() { return horarioPrevisto; }
    public boolean isTomado() { return tomado; }
    public LocalTime getHorarioReal() { return horarioReal; }

    public void setTomado(boolean tomado) { this.tomado = tomado; }
    public void setHorarioReal(LocalTime horarioReal) { this.horarioReal = horarioReal; }
}
