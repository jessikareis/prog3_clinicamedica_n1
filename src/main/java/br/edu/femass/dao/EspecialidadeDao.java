package br.edu.femass.dao;
    
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;

import br.edu.femass.model.Especialidade;

public class EspecialidadeDao extends Persistencia implements Dao<Especialidade> {

    public EspecialidadeDao() {
        super("especialidades.json");
    }

    public boolean gravar(Especialidade objeto) throws StreamWriteException, IOException {
        Set<Especialidade> especialidades = buscar();
        boolean gravou = especialidades.add(objeto);
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, especialidades);
        return gravou;
    }

    public boolean excluir(Especialidade objeto) throws StreamWriteException, IOException {
        Set<Especialidade> especialidades = buscar();
        for (Especialidade especialidadeSelecionado : especialidades) {
            if (especialidadeSelecionado.getId().equals(objeto.getId())) {
                especialidadeSelecionado.setAtivo(false);              
            }  
        }

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, especialidades);
        return true;
    }

    public Set<Especialidade> buscar() throws DatabindException {
        try {
            Set<Especialidade> especialidades = objectMapper.readValue(arquivo, new TypeReference<Set<Especialidade>>() {});
            Especialidade.atualizarUltimoId(especialidades);
            return especialidades;
        } catch (IOException ex) {
            return new HashSet<Especialidade>();
        }
    }

    public Set<Especialidade> buscarAtivos() throws DatabindException {
        try {
            Set<Especialidade> especialidades = buscar();
            Set<Especialidade> especialidadesAtivos = new HashSet<>();
            for (Especialidade especialidade : especialidades) {
                if (especialidade.getAtivo()) {
                    especialidadesAtivos.add(especialidade);
                }
            }
            Especialidade.atualizarUltimoId(especialidades);
            return especialidadesAtivos;
        } catch (IOException ex) {
            return new HashSet<Especialidade>();
        }
    }
    
    


}
    

