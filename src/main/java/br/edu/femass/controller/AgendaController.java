package br.edu.femass.controller;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import br.edu.femass.dao.AgendaDao;
import br.edu.femass.dao.EspDao;
import br.edu.femass.dao.MedicoDao;
import br.edu.femass.dao.PacienteDao;
import br.edu.femass.dao.PlanoDao;
import br.edu.femass.model.Agenda;
import br.edu.femass.model.Paciente;
import br.edu.femass.model.Esp;
import br.edu.femass.model.Medico;
import br.edu.femass.model.Plano;
import br.edu.femass.utils.UtilsJavaFx;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class AgendaController implements Initializable {
    @FXML
    private TextField TxtId;
    @FXML
    private ComboBox<Paciente> comboPaciente;
    @FXML
    private ComboBox<Plano> comboPlano;
    @FXML
    private ComboBox<Medico> comboMedico;
    @FXML
    private ComboBox<Esp> comboEsp;
    @FXML
    private TextField TxtHora;
    @FXML
    private DatePicker DtData;

    @FXML
    private TableView<Agenda> TableAgenda = new TableView<Agenda>();
    @FXML
    private TableColumn<Agenda, Long> colIdAgenda = new TableColumn<>();
    @FXML
    private TableColumn<Agenda, String> colDataAgenda = new TableColumn<>();
    @FXML
    private TableColumn<Agenda, String> colHoraAgenda = new TableColumn<>();
    @FXML
    private TableColumn<Paciente, String> colPacienteAgenda = new TableColumn<>();
    @FXML
    private TableColumn<Plano, String> colPlanoAgenda = new TableColumn<>();
    @FXML
    private TableColumn<Medico, String> colMedicoAgenda = new TableColumn<>();
    @FXML
    private TableColumn<Esp, String> colEspAgenda = new TableColumn<>();

    private AgendaDao dao_agenda = new AgendaDao();
    private Agenda agenda;
    private MedicoDao dao_medico = new MedicoDao();
    private Medico medico;
    private EspDao dao_esp = new EspDao();
    private Esp esp;
    private PacienteDao dao_paciente = new PacienteDao();
    private Paciente paciente;
    private PlanoDao dao_plano = new PlanoDao();
    private Plano plano;
    

    @FXML
    private void BtnGravar_Click(ActionEvent event) {
        try {
            agenda = new Agenda(
                    TxtHora.getText(),
                    DtData.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    comboPaciente.getValue(),
                    comboPlano.getValue(),
                    comboMedico.getValue(),
                    comboEsp.getValue());
            TxtId.setText(agenda.getId().toString());
            if (dao_agenda.gravar(agenda) == false) {
                UtilsJavaFx.exibirMensagem("Não foi possível gravar o agenda");
                return;
            }
            TxtId.setText("");
            TxtHora.setText("");
            exibirAgenda();

        } catch (Exception e) {
            UtilsJavaFx.exibirMensagem(e.getMessage());
        }
    }

    @FXML
    private void BtnExcluir_Click(ActionEvent event) {
        Agenda agenda = TableAgenda.getSelectionModel().getSelectedItem();
        if (agenda == null)
            return;
        try {
            if (dao_agenda.excluir(agenda) == false) {
                UtilsJavaFx.exibirMensagem("Não foi possível excluir o agenda selecionado");
            }
            exibirAgenda();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @FXML
    private void comboMedico_OnAction(ActionEvent event) {
        Medico medico = comboMedico.getValue();
        try {
            Set<Esp> espsSet = dao_esp.buscarPorMedico(medico);
            List<Esp> esps = new ArrayList<>(espsSet);
            ObservableList<Esp> data = FXCollections.observableArrayList(esps);
            comboEsp.setItems(data);        
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


    // @FXML
    // private void comboMedico_OnAction(ActionEvent event) {
    //     Medico medico = comboMedico.getValue();
    //     if (medico == null)
    //         return;
    //     try {
    //         Set<Esp> espsSet = dao_esp.buscarAtivos();
    //         List<Esp> esps = new ArrayList<>(espsSet);
    //         ObservableList<Esp> data = FXCollections.observableArrayList(esps);
    //         comboEsp.setItems(data);
    //     } catch (Exception ex) {
    //         ex.printStackTrace();    
    //     }
    // }

    private void exibirAgenda() {
        try {
            ObservableList<Agenda> data = FXCollections.observableArrayList(
                    dao_agenda.buscarAtivos());
            TableAgenda.setItems(data);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void carregarCombos() {

        // Pacientes
        try {
            Set<Paciente> pacientesSet = dao_paciente.buscarAtivos();
            List<Paciente> pacientes = new ArrayList<>(pacientesSet);
            ObservableList<Paciente> data = FXCollections.observableArrayList(pacientes);
            comboPaciente.setItems(data);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // Medicos
        try {
            Set<Medico> medicosSet = dao_medico.buscarAtivos();
            List<Medico> medicos = new ArrayList<>(medicosSet);
            ObservableList<Medico> data = FXCollections.observableArrayList(medicos);
            comboMedico.setItems(data);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Esps
        // try {
        //     Set<Esp> espsSet = dao_esp.buscarAtivos();
        //     List<Esp> esps = new ArrayList<>(espsSet);
        //     ObservableList<Esp> data = FXCollections.observableArrayList(esps);
        //     comboEsp.setItems(data);        
        // } catch (Exception ex) {
        //     ex.printStackTrace();
        // }

        // Planos
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
        colIdAgenda.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        colDataAgenda.setCellValueFactory(
                new PropertyValueFactory<>("data"));
        colHoraAgenda.setCellValueFactory(
                new PropertyValueFactory<>("hora"));
        colPacienteAgenda.setCellValueFactory(
                new PropertyValueFactory<>("paciente"));
        colPlanoAgenda.setCellValueFactory(
                new PropertyValueFactory<>("plano"));
        colMedicoAgenda.setCellValueFactory(
                new PropertyValueFactory<>("medico"));
        colEspAgenda.setCellValueFactory(
                new PropertyValueFactory<>("esp"));
        carregarCombos();
        exibirAgenda();
    }

}