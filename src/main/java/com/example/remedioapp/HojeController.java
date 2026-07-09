package com.example.remedioapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import java.time.LocalTime;

public class HojeController {

    @FXML
    private ListView<RegistroTomada> listaHoje;

    private final RegistroService registroService = new RegistroService();
    private final RegistroTomadaRepository registroRepository = new RegistroTomadaRepository();
    private final RemedioRepository remedioRepository = new RemedioRepository();

    @FXML
    public void initialize() {
        listaHoje.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(RegistroTomada registro, boolean empty) {
                super.updateItem(registro, empty);

                if (empty || registro == null) {
                    setText(null);
                    setGraphic(null);
                    return;
                }

                Remedio remedio = remedioRepository.buscarPorId(registro.getRemedioId());
                String nome = remedio != null ? remedio.getNome() : "Remédio removido";

                CheckBox checkBox = new CheckBox(registro.getHorarioPrevisto() + " - " + nome);
                checkBox.setSelected(registro.isTomado());

                checkBox.setOnAction(e -> {
                    if (checkBox.isSelected()) {
                        registroRepository.marcarComoTomado(registro.getId(), LocalTime.now());
                    }
                });

                setGraphic(checkBox);
            }
        });

        atualizarLista();
    }

    private void atualizarLista() {
        ObservableList<RegistroTomada> itens = FXCollections.observableArrayList(
                registroService.gerarOuBuscarRegistrosDeHoje()
        );
        listaHoje.setItems(itens);
    }
}