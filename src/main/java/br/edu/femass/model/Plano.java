package br.edu.femass.model;

import java.util.Set;

public class Plano {
    private Long  id;
    private String nome;
    private Boolean ativo;
  
    private static Long ultimoCodigo = 0L;

    public Plano(){

    }

    public Plano(String nome) {
        this.nome = nome;
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

    // public PlanoDeSaude getPlanoDeSaude() {
    //     return planoDeSaude;
    // }
        
    // public void se   tPlanoDeSaude(PlanoDeSaude planoDeSaude) {
    //     this.planoDeSaude = planoDeSaude;
    // }
        
    public static void atualizarUltimoId(Set<Plano> planos) {
        for (Plano plano: planos) {
            if (plano.getId().longValue()>ultimoCodigo) {    
                ultimoCodigo = plano.getId();                
            }
        }
    }

    public static Long getIdUltimoCodigo() {
        return null;
    }

}