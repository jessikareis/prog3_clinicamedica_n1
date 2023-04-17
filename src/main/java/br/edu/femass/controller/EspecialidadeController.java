package br.edu.femass.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import com.fasterxml.jackson.core.exc.StreamWriteException;

import br.edu.femass.dao.EspecialidadeDao;
import br.edu.femass.model.Especialidade;   
import br.edu.femass.utils.UtilsJavaFx;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class EspecialidadeController implements Initializable{
    @FXML
    private TextField TxtIdEspecialidade;
    @FXML
    private TextField TxtNomeEspecialidade;     
    @FXML
    private TableView<Especialidade> TableEspecialidade = new TableView<Especialidade>();
    @FXML
    private TableColumn<Especialidade, Long> colIdEspecialidade = new TableColumn<>();
    @FXML
    private TableColumn<Especialidade, String> colNomeEspecialidade = new TableColumn<>();
   
    private EspecialidadeDao dao_especialidade = new EspecialidadeDao();
    private Especialidade especialidade;

    @FXML
    private void BtnGravar_Click(ActionEvent event) {
        try {
            especialidade = new Especialidade(
                    TxtNomeEspecialidade.getText());

            TxtIdEspecialidade.setText(especialidade.getId().toString());
            if (dao_especialidade.gravar(especialidade)==false) {
                UtilsJavaFx.exibirMensagem("Não foi possível gravar o especialidade");
                return;
            }            
            TxtIdEspecialidade.setText("");                    
            TxtNomeEspecialidade.setText("");

            exibirEspecialidade();   
              
        } catch (Exception e) {
            UtilsJavaFx.exibirMensagem(e.getMessage());
        }
    }

    @FXML
    private void BtnExcluir_Click(ActionEvent event){
        Especialidade especialidade = TableEspecialidade.getSelectionModel().getSelectedItem();
        if (especialidade==null) return;
        try {
            if (dao_especialidade.excluir(especialidade)==false) {
                UtilsJavaFx.exibirMensagem("Não foi possível excluir o cliente selecionado");
            }
            exibirEspecialidade();
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

    private void exibirEspecialidade() {
        try {
             ObservableList<Especialidade> data = FXCollections.observableArrayList(
                dao_especialidade.buscarAtivos()
             );
             TableEspecialidade.setItems(data);             
             } catch (Exception ex) {
                 ex.printStackTrace();
             }
        }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        colIdEspecialidade.setCellValueFactory(
            new PropertyValueFactory<>("id"));
        colNomeEspecialidade.setCellValueFactory(
            new PropertyValueFactory<>("nome"));         
        exibirEspecialidade();
    }


}

//     @FXML 
//     private void listaEspecialidade_keyPressed(KeyEvent event) {
//         exibirDados();
//     }

//     @FXML 
//     private void listaP aciente_mouseClicked(MouseEvent event) {
//         exibirDados();
//     }

//     private void exibirDados() {
//         Especialidade especialidade = listaEspecialidade.getSelectionModel().getSelectedItem();
//         if (especialidade==null) return;

//         TxtCPFP.setText(especialidade.getCPF());
//         TxtEspecialidadeP.setText(especialidade.getEspecialidadeDeSaude());
//         TxtEnderecoP.setText(especialidade.getEndereco());
//         TxtId.setText(especialidade.getId().toString());
//         TxtNomeP.setText(especialidade.getNome());
//         TxtTelefoneP.setText(especialidade.getTelefones().get(0));
//     }

//     @FXML
//     private void BtnExcluir_Click(ActionEvent event) {
    //         Especialidade especialidade = listaEspecialidade.getSelectionModel().getSelectedItem();
    //         if (especialidade==null) return;

    //         try {
    //             if (especialidadeDao.excluir(especialidade)==false) {
    //                 UtilsJavaFx.exibirMensagem("Não foi possível excluir o especialidade selecionado");
    //             }
    //         exibirEspecialidade();
    //         } catch (Exception e) {
    //             e.printStackTrace();
    //         }

//     }

//      

//     public void exibirEspecialidade() {
    //         try {
    //         ObservableList<Especialidade> data = FXCollections.observableArrayList(
    //             especialidadeDao.buscarAtivos()
    //         );
    //         listaEspecialidade.setItems(data);
    //         } catch (Exception ex) {
    //             ex.printStackTrace();
    //         }
        
//     }
// }
