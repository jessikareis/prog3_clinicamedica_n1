package br.edu.femass.model;

import java.util.Set;

public class AtendimentoEspecifico {
    private Long id;
    private String data;
    private Medico medico;
    private Boolean ativo;

    private static Long ultimoCodigo = 0L;

    public AtendimentoEspecifico() {

    }

    public AtendimentoEspecifico(String data, Medico medico) {
        this.data = data;
        this.medico = medico;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
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

    public static void atualizarUltimoId(Set<AtendimentoEspecifico> atendimentos) {
        for (AtendimentoEspecifico atendimento : atendimentos) {
            if (atendimento.getId().longValue() > ultimoCodigo) {
                ultimoCodigo = atendimento.getId();
            }
        }
    }

}
