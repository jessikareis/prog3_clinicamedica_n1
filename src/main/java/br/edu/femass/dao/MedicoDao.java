package br.edu.femass.dao;
    
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;

import br.edu.femass.model.Medico;

public class MedicoDao extends Persistencia implements Dao<Medico> {

    public MedicoDao() {
        super("medicos.json");
    }

    public boolean gravar(Medico objeto) throws StreamWriteException, IOException {
        Set<Medico> medicos = buscar();
        boolean gravou = medicos.add(objeto);
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, medicos);
        return gravou;
    }

    public boolean excluir(Medico objeto) throws StreamWriteException, IOException {
        Set<Medico> medicos = buscar();
        for (Medico medicoSelecionado : medicos) {
            if (medicoSelecionado.getId().equals(objeto.getId())) {
                medicoSelecionado.setAtivo(false);              
            }  
        }

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, medicos);
        return true;
    }

    public Set<Medico> buscar() throws DatabindException {
        try {
            Set<Medico> medicos = objectMapper.readValue(arquivo, new TypeReference<Set<Medico>>() {});
            Medico.atualizarUltimoId(medicos);
            return medicos;
        } catch (IOException ex) {
            return new HashSet<Medico>();
        }
    }

    public Set<Medico> buscarAtivos() throws DatabindException {
        try {
            Set<Medico> medicos = buscar();
            Set<Medico> medicosAtivos = new HashSet<>();
            for (Medico medico : medicos) {
                if (medico.getAtivo()) {
                    medicosAtivos.add(medico);
                }
            }
            Medico.atualizarUltimoId(medicos);
            return medicosAtivos;
        } catch (IOException ex) {
            return new HashSet<Medico>();
        }
    }

    // public List<Cliente> buscarAtivos() throws DatabindException {
    //     Set<Cliente> clientes = buscar();

    //     List<Cliente> clientesAtivos = clientes
    //             .stream()
    //             .filter(cliente -> cliente.getAtivo().equals(true))
    //             .collect(Collectors.toList());

    //     return clientesAtivos;

    // }
}