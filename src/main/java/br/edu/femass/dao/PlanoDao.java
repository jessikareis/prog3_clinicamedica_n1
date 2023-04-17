package br.edu.femass.dao;
    
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;

import br.edu.femass.model.Plano;

public class PlanoDao extends Persistencia implements Dao<Plano> {

    public PlanoDao() {
        super("planos.json");
    }

    public boolean gravar(Plano objeto) throws StreamWriteException, IOException {
        Set<Plano> planos = buscar();
        boolean gravou = planos.add(objeto);
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, planos);
        return gravou;
    }

    public boolean excluir(Plano objeto) throws StreamWriteException, IOException {
        Set<Plano> planos = buscar();
        for (Plano planoSelecionado : planos) {
            if (planoSelecionado.getId().equals(objeto.getId())) {
                planoSelecionado.setAtivo(false);              
            }  
        }

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, planos);
        return true;
    }

    public Set<Plano> buscar() throws DatabindException {
        try {
            Set<Plano> planos = objectMapper.readValue(arquivo, new TypeReference<Set<Plano>>() {});
            Plano.atualizarUltimoId(planos);
            return planos;
        } catch (IOException ex) {
            return new HashSet<Plano>();
        }
    }

    public Set<Plano> buscarAtivos() throws DatabindException {
        try {
            Set<Plano> planos = buscar();
            Set<Plano> planosAtivos = new HashSet<>();
            for (Plano plano : planos) {
                if (plano.getAtivo()) {
                    planosAtivos.add(plano);
                }
            }
            Plano.atualizarUltimoId(planos);
            return planosAtivos;
        } catch (IOException ex) {
            return new HashSet<Plano>();
        }
    }
    
    


}
    

