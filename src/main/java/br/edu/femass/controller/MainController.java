package br.edu.femass.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController implements Initializable {
    
    @FXML
    private void BtnPaciente_Click(ActionEvent event) {preencherLista("Paciente");}
    @FXML
    private void BtnEspecialidade_Click(ActionEvent event) {preencherLista("Especialidade");}
    @FXML
    private void BtnPlano_Click(ActionEvent event) {preencherLista("Plano");}
    @FXML
    private void BtnMedico_Click(ActionEvent event) {preencherLista("Medico");}
    @FXML
    private void BtnAtendimento1_Click(ActionEvent event) {preencherLista("AtendimentoEspecifico");}
    @FXML
    private void BtnAtendimento2_Click(ActionEvent event) {preencherLista("AtendTodosMedicos");}
    @FXML
    private void BtnAgenda_Click(ActionEvent event) {preencherLista("Agenda");} 
    
    private void preencherLista(String path) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/"+path+".fxml"));
            
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");
            scene.getRoot().setStyle("-fx-font-family: 'serif'");

            Stage stage = new Stage();
            stage.setTitle("Cadastro de "+path);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
    }
}