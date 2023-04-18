    package br.edu.femass.controller;

    import java.net.URL;
    import java.time.format.DateTimeFormatter;
    import java.util.ResourceBundle;
    import java.util.function.Predicate;

    import br.edu.femass.dao.AgendaDao;
    import br.edu.femass.model.Agenda;
    import br.edu.femass.model.Medico;
    import br.edu.femass.utils.UtilsJavaFx;
    import javafx.collections.FXCollections;
    import javafx.collections.ObservableList;
    import javafx.collections.transformation.FilteredList;
    import javafx.event.ActionEvent;
    import javafx.fxml.FXML;
    import javafx.fxml.Initializable;
    import javafx.scene.control.DatePicker;
    import javafx.scene.control.TableColumn;
    import javafx.scene.control.TableView;
    import javafx.scene.control.TextField;
    import javafx.scene.control.cell.PropertyValueFactory;

    public class AtendTodosMedicosController implements Initializable{
        @FXML
        private TextField TxtId;
            
        @FXML
        private DatePicker DtData;
        
        @FXML
        private TableView<Agenda> TableAtendTodosMedicos = new TableView<Agenda>();
        @FXML
        private TableColumn<Agenda, Long> colIdAtendTodosMedicos = new TableColumn<>();
        @FXML
        private TableColumn<Agenda, String> colDataAtendTodosMedicos = new TableColumn<>();
        @FXML
        private TableColumn<Agenda, String> colHoraAtendTodosMedicos = new TableColumn<>();
        @FXML
        private TableColumn<Agenda, String> colPacienteAtendTodosMedicos = new TableColumn<>();
        @FXML
        private TableColumn<Agenda, String> colPlanoAtendTodosMedicos = new TableColumn<>();
        @FXML
        private TableColumn<Agenda, String> colMedicoAtendTodosMedicos = new TableColumn<>();
        @FXML
        private TableColumn<Agenda, String> colEspecialidadeAtendTodosMedicos = new TableColumn<>();
        
        private AgendaDao dao_agenda = new AgendaDao();
        private Medico medico;
    
        @FXML
        private void BtnExcluir_Click(ActionEvent event){
            Agenda agenda = TableAtendTodosMedicos.getSelectionModel().getSelectedItem();
            if (agenda==null) return;
            try {
                if (dao_agenda.excluir(agenda)==false) {
                    UtilsJavaFx.exibirMensagem("Não foi possível excluir a agenda selecionada");
                }
                exibirAgenda();
            } catch (Exception e) {
                e.printStackTrace();
            }  
        }
        
        // Ação do botão "Exibir"
        @FXML
        private void BtnExibir_Click(ActionEvent event){
        String data = DtData.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        //Medico medico = comboMedico.getValue();
        try{
            if (data != null) {         
            // obter dados para exibir na TableView
            ObservableList<Agenda> lagenda_filtrada;
            lagenda_filtrada = obterDados(data, medico);
            TableAtendTodosMedicos.getItems().clear();
            TableAtendTodosMedicos.getItems().addAll(lagenda_filtrada);
            }else{
                //msg para o usuario dizendo que falta preencher o medico ou a data
                UtilsJavaFx.exibirMensagem("Por favor, preencha o campo Data da consulta.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private ObservableList<Agenda> obterDados(String data, Medico medico) {
    try{
        ObservableList<Agenda> lagenda = FXCollections.observableArrayList(dao_agenda.buscarAtivos());
        FilteredList<Agenda> lagenda_filtrada = new FilteredList<>(lagenda); 
        //filtro data   
        Predicate<Agenda> predicate = agenda -> agenda.getData().equals(data);
        lagenda_filtrada.setPredicate(predicate);
        return lagenda_filtrada;
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return null;
    }

        private void exibirAgenda() {
            try {
                ObservableList<Agenda> data = FXCollections.observableArrayList(
                    dao_agenda.buscarAtivos()
                );     
                TableAtendTodosMedicos.setItems(data);             
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        @Override
        public void initialize(URL arg0, ResourceBundle arg1) {
            colIdAtendTodosMedicos.setCellValueFactory(
                new PropertyValueFactory<>("id"));
            colDataAtendTodosMedicos.setCellValueFactory(
                new PropertyValueFactory<>("data"));
            colHoraAtendTodosMedicos.setCellValueFactory(
                new PropertyValueFactory<>("hora"));
                colPacienteAtendTodosMedicos.setCellValueFactory(
                new PropertyValueFactory<>("paciente"));
            colPlanoAtendTodosMedicos.setCellValueFactory( 
                new PropertyValueFactory<>("plano"));
            colMedicoAtendTodosMedicos.setCellValueFactory(
                new PropertyValueFactory<>("medico"));
            colEspecialidadeAtendTodosMedicos.setCellValueFactory(
                new PropertyValueFactory<>("especialidade"));
            
            exibirAgenda();   
        }

    }