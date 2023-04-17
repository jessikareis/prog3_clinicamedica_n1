package br.edu.femass.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import com.fasterxml.jackson.core.exc.StreamWriteException;

import br.edu.femass.dao.PlanoDao;
import br.edu.femass.model.Plano;   
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

public class PlanoController implements Initializable{
    @FXML
    private TextField TxtIdPlano;
    @FXML
    private TextField TxtNomePlano;     
    @FXML
    private TableView<Plano> TablePlano = new TableView<Plano>();
    @FXML
    private TableColumn<Plano, Long> colIdPlano = new TableColumn<>();
    @FXML
    private TableColumn<Plano, String> colNomePlano = new TableColumn<>();
   
    private PlanoDao dao_plano = new PlanoDao();
    private Plano plano;

    @FXML
    private void BtnGravar_Click(ActionEvent event) {
        try {
            plano = new Plano(
                    TxtNomePlano.getText());

            TxtIdPlano.setText(plano.getId().toString());
            if (dao_plano.gravar(plano)==false) {
                UtilsJavaFx.exibirMensagem("Não foi possível gravar o plano");
                return;
            }            
            TxtIdPlano.setText("");                    
            TxtNomePlano.setText("");

            exibirPlano();   
              
        } catch (Exception e) {
            UtilsJavaFx.exibirMensagem(e.getMessage());
        }
    }

    @FXML
    private void BtnExcluir_Click(ActionEvent event){
        Plano plano = TablePlano.getSelectionModel().getSelectedItem();
        if (plano==null) return;
        try {
            if (dao_plano.excluir(plano)==false) {
                UtilsJavaFx.exibirMensagem("Não foi possível excluir o cliente selecionado");
            }
            exibirPlano();
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

    private void exibirPlano() {
        try {
             ObservableList<Plano> data = FXCollections.observableArrayList(
                dao_plano.buscarAtivos()
             );
             TablePlano.setItems(data);             
             } catch (Exception ex) {
                 ex.printStackTrace();
             }
        }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        colIdPlano.setCellValueFactory(
            new PropertyValueFactory<>("id"));
        colNomePlano.setCellValueFactory(
            new PropertyValueFactory<>("nome"));         
        exibirPlano();
    }
}

//     @FXML 
//     private void listaPlano_keyPressed(KeyEvent event) {
//         exibirDados();
//     }

//     @FXML 
//     private void listaP aciente_mouseClicked(MouseEvent event) {
//         exibirDados();
//     }

//     private void exibirDados() {
//         Plano plano = listaPlano.getSelectionModel().getSelectedItem();
//         if (plano==null) return;

//         TxtCPFP.setText(plano.getCPF());
//         TxtPlanoP.setText(plano.getPlanoDeSaude());
//         TxtEnderecoP.setText(plano.getEndereco());
//         TxtId.setText(plano.getId().toString());
//         TxtNomeP.setText(plano.getNome());
//         TxtTelefoneP.setText(plano.getTelefones().get(0));
//     }

//     @FXML
//     private void BtnExcluir_Click(ActionEvent event) {
    //         Plano plano = listaPlano.getSelectionModel().getSelectedItem();
    //         if (plano==null) return;

    //         try {
    //             if (planoDao.excluir(plano)==false) {
    //                 UtilsJavaFx.exibirMensagem("Não foi possível excluir o plano selecionado");
    //             }
    //         exibirPlano();
    //         } catch (Exception e) {
    //             e.printStackTrace();
    //         }

//     }

//      

//     public void exibirPlano() {
    //         try {
    //         ObservableList<Plano> data = FXCollections.observableArrayList(
    //             planoDao.buscarAtivos()
    //         );
    //         listaPlano.setItems(data);
    //         } catch (Exception ex) {
    //             ex.printStackTrace();
    //         }
        
//     }   
// }