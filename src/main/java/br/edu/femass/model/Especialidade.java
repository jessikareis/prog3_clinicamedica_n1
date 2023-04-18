package br.edu.femass.model;

import java.util.Set;

public class Especialidade {
    private Long id;
    private String nome;
    private Boolean ativo;

    private static Long ultimoCodigo = 0L;

    public Especialidade() {

    }

    public Especialidade(String nome) {
        this.nome = nome;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    // public EspecialidadeDeSaude getEspecialidadeDeSaude() {
    // return especialidadeDeSaude;
    // }

    // public void se tEspecialidadeDeSaude(EspecialidadeDeSaude
    // especialidadeDeSaude) {
    // this.especialidadeDeSaude = especialidadeDeSaude;
    // }

    public static void atualizarUltimoId(Set<Especialidade> especialidades) {
        for (Especialidade especialidade : especialidades) {
            if (especialidade.getId().longValue() > ultimoCodigo) {
                ultimoCodigo = especialidade.getId();
            }
        }
    }

}
