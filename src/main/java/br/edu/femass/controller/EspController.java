package br.edu.femass.controller;

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
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class EspController implements Initializable {
    @FXML
    private TextField TxtNomeEsp;
    @FXML
    private TextField TxtId;
    @FXML
    private ComboBox<Medico> comboMedico;
    @FXML
    private TableView<Esp> TableEsp = new TableView<Esp>();
    @FXML
    private TableColumn<Esp, Long> colIdEsp = new TableColumn<>();
    @FXML
    private TableColumn<Esp, Medico> colMedicoEsp = new TableColumn<>();
    @FXML
    private TableColumn<Esp, String> colNomeEsp = new TableColumn<>();
    


    private MedicoDao dao_medico = new MedicoDao();
    private Medico medico;

    private Esp esp;
    private EspDao dao_esp = new EspDao();


    @FXML
    private void BtnGravar_Click(ActionEvent event) {
        try {
            Medico med = new Medico();
            med = comboMedico.getValue();
            esp = new Esp(TxtNomeEsp.getText(),med);
            TxtId.setText(esp.getId().toString());
            if (dao_esp.gravar(esp) == false) {
                UtilsJavaFx.exibirMensagem("Não foi possível gravar o medico");
                return;
            }
            TxtId.setText("");
            TxtNomeEsp.setText("");
            exibirEsp();
        } catch (Exception e) {
            UtilsJavaFx.exibirMensagem(e.getMessage());
        }
    }
    @FXML
    private void BtnExcluir_Click(ActionEvent event) {
        Esp esp = TableEsp.getSelectionModel().getSelectedItem();
        if (esp == null)
            return;
        try {
            if (dao_esp.excluir(esp) == false) {
                UtilsJavaFx.exibirMensagem("Não foi possível excluir o medico selecionado");
            }
            exibirEsp();
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

    

    private void carregarMedicos() {
        try {
            Set<Medico> medicosSet = dao_medico.buscarAtivos();
            List<Medico> medicos = new ArrayList<>(medicosSet);
            ObservableList<Medico> data = FXCollections.observableArrayList(medicos);   
            comboMedico.setItems(data); 
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private void exibirEsp() {
        try {
            ObservableList<Esp> data = FXCollections.observableArrayList(
                    dao_esp.buscarAtivos());
            TableEsp.setItems(data);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
            colIdEsp.setCellValueFactory(
                    new PropertyValueFactory<>("id"));
            colMedicoEsp.setCellValueFactory(
                    new PropertyValueFactory<>("med"));
            colNomeEsp.setCellValueFactory(
                    new PropertyValueFactory<>("nome"));
    exibirEsp();
    carregarMedicos();        
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
// TxtEspecialidadeP.setText(medico.getEspecialidadeDeSaude());
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