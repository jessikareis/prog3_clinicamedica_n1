package br.edu.femass.dao;
    
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;

import br.edu.femass.model.Agenda;

public class AgendaDao extends Persistencia implements Dao<Agenda> {

    public AgendaDao() {
        super("agendas.json");
    }

    public boolean gravar(Agenda objeto) throws StreamWriteException, IOException {
        Set<Agenda> agendas = buscar();
        boolean gravou = agendas.add(objeto);
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, agendas);
        return gravou;
    }

    public boolean excluir(Agenda objeto) throws StreamWriteException, IOException {
        Set<Agenda> agendas = buscar();
        for (Agenda agendaSelecionado : agendas) {
            if (agendaSelecionado.getId().equals(objeto.getId())) {
                agendaSelecionado.setAtivo(false);              
            }  
        }

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, agendas);
        return true;
    }

    public Set<Agenda> buscar() throws DatabindException {
        try {
            Set<Agenda> agendas = objectMapper.readValue(arquivo, new TypeReference<Set<Agenda>>() {});
            Agenda.atualizarUltimoId(agendas);
            return agendas;
        } catch (IOException ex) {
            return new HashSet<Agenda>();
        }
    }

    public Set<Agenda> buscarAtivos() throws DatabindException {
        try {
            Set<Agenda> agendas = buscar();
            Set<Agenda> agendasAtivos = new HashSet<>();
            for (Agenda agenda : agendas) {
                if (agenda.getAtivo()) {
                    agendasAtivos.add(agenda);
                }
            }
            Agenda.atualizarUltimoId(agendas);
            return agendasAtivos;
        } catch (IOException ex) {
            return new HashSet<Agenda>();
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
    

