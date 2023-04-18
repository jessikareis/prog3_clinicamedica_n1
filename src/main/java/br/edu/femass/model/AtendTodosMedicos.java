package br.edu.femass.model;

import java.util.Set;

public class AtendTodosMedicos {
    private Long id;
    private String data;
    private Boolean ativo;

    private static Long ultimoCodigo = 0L;

    public AtendTodosMedicos() {

    }

    public AtendTodosMedicos(String data) {
        this.data = data;
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

    public static void atualizarUltimoId(Set<AtendTodosMedicos> atendimentos) {
        for (AtendTodosMedicos atendimento : atendimentos) {
            if (atendimento.getId().longValue() > ultimoCodigo) {
                ultimoCodigo = atendimento.getId();
            }
        }
    }
}
