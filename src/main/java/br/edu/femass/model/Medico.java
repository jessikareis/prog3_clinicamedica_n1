package br.edu.femass.model;

import java.util.List;
import java.util.Set;

public class Medico {
    private Long id;
    private String nome;
    private String telefoneM;
    private Boolean ativo;
    private Especialidade especialidade;

    private static Long ultimoCodigo = 0L;

    public Medico() {

    }

    public Medico(String nome, String telefoneM, Especialidade especialidade) {
        this.nome = nome;
        this.especialidade = especialidade;
        this.telefoneM = telefoneM;
        this.ativo = true;
        this.id = ultimoCodigo + 1;
        ultimoCodigo++;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefoneM() {
        return telefoneM;
    }

    public void setTelefoneM(String telefoneM) {
        this.telefoneM = telefoneM;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return this.nome;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    public static void atualizarUltimoId(Set<Medico> medicos) {
        for (Medico medico : medicos) {
            if (medico.getId().longValue() > ultimoCodigo) {
                ultimoCodigo = medico.getId();
            }
        }
    }

}
