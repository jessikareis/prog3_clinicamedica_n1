package br.edu.femass.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javax.swing.JOptionPane;
import com.fasterxml.jackson.core.exc.StreamWriteException;

import br.edu.femass.dao.PacienteDao;
import br.edu.femass.dao.PlanoDao;
import br.edu.femass.model.Paciente;
import br.edu.femass.model.Plano;
import br.edu.femass.utils.UtilsJavaFx;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class PacienteController implements Initializable{
    @FXML
    private TextField TxtId;
    @FXML
    private ComboBox<Plano> comboPlano;
    @FXML
    private TextField TxtNomeP;
    @FXML
    private TextField TxtCPFP;
    @FXML
    private TextField TxtPlanoP;
    @FXML
    private TextField TxtTelefoneP;    
    @FXML
    private TableView<Paciente> TablePaciente = new TableView<Paciente>();
    @FXML
    private TableColumn<Paciente, Long> colIdPaciente = new TableColumn<>();
    @FXML
    private TableColumn<Paciente, String> colNomePaciente = new TableColumn<>();
    @FXML
    private TableColumn<Paciente, String > colCPFPaciente = new TableColumn<>();
    @FXML
    private TableColumn<Paciente, String> colTelefonePaciente = new TableColumn<>();
    @FXML
    private TableColumn<Plano, String> colPlanoPaciente = new TableColumn<>();

    private PacienteDao dao_paciente = new PacienteDao();
    private Paciente paciente;
    private PlanoDao dao_plano = new PlanoDao();
    private Plano plano;

    @FXML
    private void BtnGravar_Click(ActionEvent event) {
        try {
            Plano p = new Plano();            
            p = comboPlano.getValue();
            paciente = new Paciente(
                TxtNomeP.getText(),
                TxtCPFP.getText(),
                TxtTelefoneP.getText(),p);    
            TxtId.setText(paciente.getId().toString());
            if (dao_paciente.gravar(paciente)==false) {
                UtilsJavaFx.exibirMensagem("Não foi possível gravar o paciente");
                return;
            }            
            TxtId.setText("");
            TxtCPFP.setText("");            
            TxtNomeP.setText("");
            TxtTelefoneP.setText("");

            exibirPaciente();   
              
        } catch (Exception e) {
            UtilsJavaFx.exibirMensagem(e.getMessage());
        }
    }

    @FXML
    private void BtnExcluir_Click(ActionEvent event){
        Paciente paciente = TablePaciente.getSelectionModel().getSelectedItem();
        if (paciente==null) return;
        try {
            if (dao_paciente.excluir(paciente)==false) {
                UtilsJavaFx.exibirMensagem("Não foi possível excluir o paciente selecionado");
            }
            exibirPaciente();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*
     * botaoRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Autor autor = (Autor) listaAutores.getSelectedValue();
                    new AutorDao().remover(autor);
                    atualizarLista();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
     * 
     * 
     * 
     */

    private void exibirPaciente() {
        try {
             ObservableList<Paciente> data = FXCollections.observableArrayList(
                dao_paciente.buscarAtivos()
             );     
             TablePaciente.setItems(data);             
             } catch (Exception ex) {
                 ex.printStackTrace();
             }
        }

    private void carregarPlanos() {
        try {
            Set<Plano> planosSet = dao_plano.buscarAtivos();
            List<Plano> planos = new ArrayList<>(planosSet);
            ObservableList<Plano> data = FXCollections.observableArrayList(planos);
            comboPlano.setItems(data);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        colIdPaciente.setCellValueFactory(
            new PropertyValueFactory<>("id"));
        colNomePaciente.setCellValueFactory(
            new PropertyValueFactory<>("nome"));
        colCPFPaciente.setCellValueFactory(
            new PropertyValueFactory<>("cpf"));
        colTelefonePaciente.setCellValueFactory(
            new PropertyValueFactory<>("telefone"));
        colPlanoPaciente.setCellValueFactory(
            new PropertyValueFactory<>("plano"));
        carregarPlanos();
        exibirPaciente();   
    }
}