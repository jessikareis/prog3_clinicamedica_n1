package br.edu.femass.model;

import java.util.Set;

public class Agenda {
    private Long id;
    private String hora;
    private String data;
    private Paciente paciente;
    private Plano plano;
    private Medico medico;
    private Especialidade especialidade;
    private Boolean ativo;

    private static Long ultimoCodigo = 0L;

    public Agenda() {

    }

    public Agenda(String hora, String data, Paciente paciente, Plano plano, Medico medico,
            Especialidade especialidade) {
        this.hora = hora;
        this.data = data;
        this.paciente = paciente;
        this.plano = plano;
        this.medico = medico;
        this.especialidade = especialidade;
        this.ativo = true;
        this.id = ultimoCodigo + 1;
        ultimoCodigo++;
    }

    // getters e setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Plano getPlano() {
        return plano;
    }

    public void setPlano(Plano plano) {
        this.plano = plano;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
    // fim

    @Override
    public String toString() {
        return this.data;
    }

    public static void atualizarUltimoId(Set<Agenda> agendas) {
        for (Agenda agenda : agendas) {
            if (agenda.getId().longValue() > ultimoCodigo) {
                ultimoCodigo = agenda.getId();
            }
        }
    }

}
