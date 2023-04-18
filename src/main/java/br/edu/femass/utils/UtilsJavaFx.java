package br.edu.femass.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class UtilsJavaFx {
    public static void exibirMensagem(String mensagem) {
        Alert alerta = new Alert(AlertType.ERROR);
        alerta.setTitle(mensagem);
        alerta.setContentText(mensagem);
        alerta.show();
    }
}
