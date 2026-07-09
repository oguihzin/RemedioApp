package com.example.remedioapp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class RegistroService {

    private final RemedioRepository remedioRepository = new RemedioRepository();
    private final RegistroTomadaRepository registroRepository = new RegistroTomadaRepository();

    public List<RegistroTomada> gerarOuBuscarRegistrosDeHoje() {
        LocalDate hoje = LocalDate.now();

        List<RegistroTomada> registrosExistentes = registroRepository.listarPorData(hoje);

        if (!registrosExistentes.isEmpty()) {
            return registrosExistentes;
        }

        List<Remedio> remedios = remedioRepository.listarTodos();

        for (Remedio remedio : remedios) {
            for (String horarioStr : remedio.getHorarios()) {
                LocalTime horario = LocalTime.parse(horarioStr);

                RegistroTomada novoRegistro = new RegistroTomada(
                        0,
                        remedio.getId(),
                        hoje,
                        horario,
                        false,
                        null
                );

                registroRepository.salvar(novoRegistro);
            }
        }

        return registroRepository.listarPorData(hoje);
    }
}