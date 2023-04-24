package br.edu.femass.model;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

public class Esp {
    private Long id;
    private String nome;
    private Boolean ativo;
    public Medico med;

    static Long ultimoCodigo = 0L;

    public Esp() {

    }

    public Esp(String nome, Medico med) {
        this.nome = nome;
        this.med = med;
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

    public Medico getMed() {
        return med;
    }

    public void setMed(Medico med) {
        this.med = med;
    }

    // public EspDeSaude getEspDeSaude() {
    // return EspDeSaude;
    // }

    // public void se tEspDeSaude(EspDeSaude
    // EspDeSaude) {
    // this.EspDeSaude = EspDeSaude;
    // }

    public static void atualizarUltimoId(Set<Esp> Esps) {
        for (Esp Esp : Esps) {
            if (Esp.getId().longValue() > ultimoCodigo) {
                ultimoCodigo = Esp.getId();
            }
        }
    }

}
