    package br.edu.femass.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import br.edu.femass.dao.EspDao;
import br.edu.femass.dao.MedicoDao;
import br.edu.femass.model.Esp;
import br.edu.femass.model.Medico;
import br.edu.femass.utils.UtilsJavaFx;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MedicoController implements Initializable {
    
    @FXML
    private TextField TxtIdM;
    @FXML
    private TextField TxtNomeM;
    @FXML
    private TextField TxtTelefoneM;
    @FXML
    private TableView<Medico> TableMedico = new TableView<Medico>();
    @FXML
    private TableColumn<Medico, Long> colIdMedico = new TableColumn<>();
    @FXML
    private TableColumn<Medico, String> colNomeMedico = new TableColumn<>();
    @FXML
    private TableColumn<Medico, String> colTelefoneMedico = new TableColumn<>();
    

    private MedicoDao dao_medico = new MedicoDao();
    private Medico medico;
    private EspDao dao_esp = new EspDao();
    private Esp esp;

    @FXML
    private void BtnEsp_Click(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Esp.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        scene.getRoot().setStyle("-fx-font-family: 'serif'");

        Stage stage = new Stage();
        stage.setTitle("Adicionar uma especialiadade a um medico");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void BtnGravar_Click(ActionEvent event) {
        try {
            Esp e = new Esp();
            medico = new Medico(
                    TxtNomeM.getText(),
                    TxtTelefoneM.getText());

            TxtIdM.setText(medico.getId().toString());
            if (dao_medico.gravar(medico) == false) {
                UtilsJavaFx.exibirMensagem("Não foi possível gravar o medico");
                return;
            }
            TxtIdM.setText("");
            TxtNomeM.setText("");
            TxtTelefoneM.setText("");

            exibirMedico();

        } catch (Exception e) {
            UtilsJavaFx.exibirMensagem(e.getMessage());
        }
    }

    @FXML
    private void BtnExcluir_Click(ActionEvent event) {
        Medico medico = TableMedico.getSelectionModel().getSelectedItem();
        if (medico == null)
            return;
        try {
            if (dao_medico.excluir(medico) == false) {
                UtilsJavaFx.exibirMensagem("Não foi possível excluir o medico selecionado");
            }
            exibirMedico();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /*
     * botaoRemover.addActionListener(new ActionListener() {
     * 
     * @Override
     * public void actionPerformed(ActionEvent e) {
     * try {
     * Autor autor = (Autor) listaAutores.getSelectedValue();
     * new AutorDao().remover(autor);
     * atualizarLista();
     * 
     * } catch (Exception ex) {
     * JOptionPane.showMessageDialog(null, ex.getMessage());
     * }
     * }
     * });
     * 
     * 
     * 
     */

    private void exibirMedico() {
        try {
            ObservableList<Medico> data = FXCollections.observableArrayList(
                    dao_medico.buscarAtivos());
            TableMedico.setItems(data);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

   

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        colIdMedico.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        colNomeMedico.setCellValueFactory(
                new PropertyValueFactory<>("nome"));
        colTelefoneMedico.setCellValueFactory(
                new PropertyValueFactory<>("telefoneM"));
        exibirMedico();
    }
}
// @FXML
// private void listaMedico_keyPressed(KeyEvent event) {
// exibirDados();
// }

// @FXML
// private void listaP aciente_mouseClicked(MouseEvent event) {
// exibirDados();
// }

// private void exibirDados() {
// Medico medico = listaMedico.getSelectionModel().getSelectedItem();
// if (medico==null) return;

// TxtCPFP.setText(medico.getCPF());
// TxtEspP.setText(medico.getEspDeSaude());
// TxtEnderecoP.setText(medico.getEndereco());
// TxtId.setText(medico.getId().toString());
// TxtNomeP.setText(medico.getNome());
// TxtTelefoneP.setText(medico.getTelefones().get(0));
// }

// @FXML
// private void BtnExcluir_Click(ActionEvent event) {
// Medico medico = listaMedico.getSelectionModel().getSelectedItem();
// if (medico==null) return;

// try {
// if (medicoDao.excluir(medico)==false) {
// UtilsJavaFx.exibirMensagem("Não foi possível excluir o medico selecionado");
// }
// exibirMedico();
// } catch (Exception e) {
// e.printStackTrace();
// }

// }

//

// public void exibirMedico() {
// try {
// ObservableList<Medico> data = FXCollections.observableArrayList(
// medicoDao.buscarAtivos()
// );
// listaMedico.setItems(data);
// } catch (Exception ex) {
// ex.printStackTrace();
// }

// }
// }