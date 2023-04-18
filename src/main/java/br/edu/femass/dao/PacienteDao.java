package br.edu.femass.dao;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;

import br.edu.femass.model.Paciente;

public class PacienteDao extends Persistencia implements Dao<Paciente> {

    public PacienteDao() {
        super("pacientes.json");
    }

    public boolean gravar(Paciente objeto) throws StreamWriteException, IOException {
        Set<Paciente> pacientes = buscar();
        boolean gravou = pacientes.add(objeto);
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, pacientes);
        return gravou;
    }

    public boolean excluir(Paciente objeto) throws StreamWriteException, IOException {
        Set<Paciente> pacientes = buscar();
        for (Paciente pacienteSelecionado : pacientes) {
            if (pacienteSelecionado.getId().equals(objeto.getId())) {
                pacienteSelecionado.setAtivo(false);
            }
        }

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, pacientes);
        return true;
    }

    public Set<Paciente> buscar() throws DatabindException {
        try {
            Set<Paciente> pacientes = objectMapper.readValue(arquivo, new TypeReference<Set<Paciente>>() {
            });
            Paciente.atualizarUltimoId(pacientes);
            return pacientes;
        } catch (IOException ex) {
            return new HashSet<Paciente>();
        }
    }

    public Set<Paciente> buscarAtivos() throws DatabindException {
        try {
            Set<Paciente> pacientes = buscar();
            Set<Paciente> pacientesAtivos = new HashSet<>();
            for (Paciente paciente : pacientes) {
                if (paciente.getAtivo()) {
                    pacientesAtivos.add(paciente);
                }
            }
            Paciente.atualizarUltimoId(pacientes);
            return pacientesAtivos;
        } catch (IOException ex) {
            return new HashSet<Paciente>();
        }
    }

    // public List<Cliente> buscarAtivos() throws DatabindException {
    // Set<Cliente> clientes = buscar();

    // List<Cliente> clientesAtivos = clientes
    // .stream()
    // .filter(cliente -> cliente.getAtivo().equals(true))
    // .collect(Collectors.toList());

    // return clientesAtivos;

    // }
}