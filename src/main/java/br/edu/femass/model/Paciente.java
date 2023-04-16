package br.edu.femass.model;

import java.util.Set;

public class Paciente {
    private Long  id;
    private String nome;
    private String cpf;
    private String telefone;
    private Boolean ativo;
    private Plano plano;
   
   static Long ultimoCodigo = 0L;
  
    public Paciente(){

    }

    public Paciente(String nome, String cpf, String telefone, Plano plano) {
        this.nome = nome;
        this.cpf = cpf;        
        this.telefone = telefone;
        this.plano = plano;
        this.ativo = true;
        this.id = ultimoCodigo+1;
        ultimoCodigo++;
    }

    // getters e setters

    public Long getId() {
        return id;
    }

    public void setId(Long  id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo){
        this.ativo = ativo;
    }
    
           
    @Override
    public String toString(){
        return this.nome;
    }
        
    public Plano getPlano() {
        return plano;
    }
    public void setPlano(Plano plano) {
        this.plano = plano;
    }

    public static void atualizarUltimoId(Set<Paciente> pacientes) {
        for (Paciente paciente: pacientes) {
            if (paciente.getId().longValue()>ultimoCodigo) {    
                ultimoCodigo = paciente.getId();                    
            }
        }
    }

}
