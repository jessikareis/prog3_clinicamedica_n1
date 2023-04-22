package br.edu.femass.controller;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.function.Predicate;

import br.edu.femass.dao.AgendaDao;
import br.edu.femass.dao.MedicoDao;
import br.edu.femass.model.Agenda;
import br.edu.femass.model.Medico;
import br.edu.femass.utils.UtilsJavaFx;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class AtendimentoEspecificoController implements Initializable {
    @FXML
    private TextField TxtId;

    @FXML
    private DatePicker DtData;

    @FXML
    private ComboBox<Medico> comboMedico;

    @FXML
    private TableView<Agenda> TableAtendimento = new TableView<Agenda>();
    @FXML
    private TableColumn<Agenda, Long> colIdAtendimentoE = new TableColumn<>();
    @FXML
    private TableColumn<Agenda, String> colDataAtendimentoE = new TableColumn<>();
    @FXML
    private TableColumn<Agenda, String> colHoraAtendimentoE = new TableColumn<>();
    @FXML
    private TableColumn<Agenda, String> colPacienteAtendimentoE = new TableColumn<>();
    @FXML
    private TableColumn<Agenda, String> colPlanoAtendimentoE = new TableColumn<>();
    @FXML
    private TableColumn<Agenda, String> colMedicoAtendimentoE = new TableColumn<>();
    @FXML
    private TableColumn<Agenda, String> colEspecialidadeAtendimentoE = new TableColumn<>();

    private AgendaDao dao_agenda = new AgendaDao();
    // private Agenda agenda;
    private MedicoDao dao_medico = new MedicoDao();
    private Medico medico;
    // private EspecialidadeDao dao_especialidade = new EspecialidadeDao();
    // private Especialidade especialidade;
    // private PacienteDao dao_paciente = new PacienteDao();
    // private Paciente paciente;
    // private PlanoDao dao_plano = new PlanoDao();
    // private Plano plano;

    @FXML
    private void BtnExcluir_Click(ActionEvent event) {
        Agenda agenda = TableAtendimento.getSelectionModel().getSelectedItem();
        if (agenda == null)
            return;
        try {
            if (dao_agenda.excluir(agenda) == false) {
                UtilsJavaFx.exibirMensagem("Não foi possível excluir a agenda selecionada");
            }
            exibirAgenda();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // Ação do botão "Exibir"
    @FXML
    private void BtnExibir_Click(ActionEvent event) {
        String data = DtData.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Medico medico = comboMedico.getValue();
        try {
            if (data != null && medico != null) {
                // obter dados para exibir na TableView
                ObservableList<Agenda> lagenda_filtrada;
                lagenda_filtrada = obterDados(data, medico);
                TableAtendimento.getItems().clear();
                TableAtendimento.getItems().addAll(lagenda_filtrada);
            } else {
                // msg para o usuario dizendo que falta preencher o medico ou a data
                UtilsJavaFx.exibirMensagem("Por favor, preencha o campo Médico e a Data da consulta.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private ObservableList<Agenda> obterDados(String data, Medico medico) {
        try {
            ObservableList<Agenda> lagenda = FXCollections.observableArrayList(dao_agenda.buscarAtivos());
            FilteredList<Agenda> lagenda_filtrada = new FilteredList<>(lagenda);
            // filtro data
            Predicate<Agenda> predicate = agenda -> agenda.getData().equals(data);
            // filtro Medico
            Predicate<Agenda> medicoPredicate = agenda -> agenda.getMedico().getId().equals(medico.getId());
            // filtro Medico and data
            predicate = predicate.and(medicoPredicate);
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
                    dao_agenda.buscarAtivos());
            TableAtendimento.setItems(data);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void carregarMedico() {

        // Medicos
        try {
            Set<Medico> medicosSet = dao_medico.buscarAtivos();
            List<Medico> medicos = new ArrayList<>(medicosSet);
            ObservableList<Medico> data = FXCollections.observableArrayList(medicos);
            comboMedico.setItems(data);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        colIdAtendimentoE.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        colDataAtendimentoE.setCellValueFactory(
                new PropertyValueFactory<>("data"));
        colHoraAtendimentoE.setCellValueFactory(
                new PropertyValueFactory<>("hora"));
        colPacienteAtendimentoE.setCellValueFactory(
                new PropertyValueFactory<>("paciente"));
        colPlanoAtendimentoE.setCellValueFactory(
                new PropertyValueFactory<>("plano"));
        colMedicoAtendimentoE.setCellValueFactory(
                new PropertyValueFactory<>("medico"));
        colEspecialidadeAtendimentoE.setCellValueFactory(
                new PropertyValueFactory<>("especialidade"));
        carregarMedico();
        exibirAgenda();
    }

}