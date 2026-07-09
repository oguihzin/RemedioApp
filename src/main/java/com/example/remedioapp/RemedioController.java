package com.example.remedioapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import java.util.List;
import java.time.LocalDate;

public class RemedioController {

    @FXML
    private ListView<Remedio> listaRemedios;

    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoDosagem;

    @FXML
    private TextField campoHorario;

    @FXML
    private Label labelFeedback;

    private final RemedioRepository repository = new RemedioRepository();

    @FXML
    public void initialize() {
        atualizarLista();
    }

    @FXML
    public void adicionarRemedio() {
        String nome = campoNome.getText();
        String dosagem = campoDosagem.getText();
        String horario = campoHorario.getText();

        if (nome.isBlank() || horario.isBlank()) {
            labelFeedback.setText("Nome e horário são obrigatórios!");
            return;
        }

        try {
            java.time.LocalTime.parse(horario);
        } catch (java.time.format.DateTimeParseException e) {
            labelFeedback.setText("Horário inválido! Use o formato HH:mm (ex: 08:00)");
            return;
        }

        Remedio novo = new Remedio(0, nome, dosagem, List.of(horario));
        repository.salvar(novo);

        campoNome.clear();
        campoDosagem.clear();
        campoHorario.clear();
        labelFeedback.setText("");

        atualizarLista();
    }

    private void atualizarLista() {
        ObservableList<Remedio> itens = FXCollections.observableArrayList(repository.listarTodos());
        listaRemedios.setItems(itens);
    }

    @FXML
public void removerRemedio() {
    Remedio selecionado = listaRemedios.getSelectionModel().getSelectedItem();

    if (selecionado == null) {
        labelFeedback.setText("Selecione um remédio para remover!");
        return;
    }

    boolean temRegistroHoje = registroRepository.listarPorData(LocalDate.now()).stream()
            .anyMatch(r -> r.getRemedioId() == selecionado.getId());

    if (temRegistroHoje) {
        labelFeedback.setText("Não é possível remover: esse remédio já tem registro de hoje.");
        return;
    }

    repository.deletar(selecionado.getId());
    labelFeedback.setText("");
    atualizarLista();
}

    private final RegistroTomadaRepository registroRepository = new RegistroTomadaRepository();
}