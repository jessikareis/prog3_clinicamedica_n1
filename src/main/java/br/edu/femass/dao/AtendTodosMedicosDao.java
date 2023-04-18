package br.edu.femass.dao;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;

import br.edu.femass.model.AtendTodosMedicos;

public class AtendTodosMedicosDao extends Persistencia implements Dao<AtendTodosMedicos> {

    public AtendTodosMedicosDao(String nomeArquivo) {
        super(nomeArquivo);
        // TODO Auto-generated constructor stub
    }

    public boolean exibir(AtendTodosMedicos objeto) throws StreamWriteException, IOException {
        Set<AtendTodosMedicos> atendimentos = buscar();
        boolean exibiu = atendimentos.add(objeto);
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, atendimentos);
        return exibiu;
    }

    public boolean excluir(AtendTodosMedicos objeto) throws StreamWriteException, IOException {
        Set<AtendTodosMedicos> atendimentos = buscar();
        for (AtendTodosMedicos atendimentoSelecionado : atendimentos) {
            if (atendimentoSelecionado.getId().equals(objeto.getId())) {
                atendimentoSelecionado.setAtivo(false);
            }
        }

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, atendimentos);
        return true;
    }

    public Set<AtendTodosMedicos> buscar() throws DatabindException {
        try {
            Set<AtendTodosMedicos> atendimentos = objectMapper.readValue(arquivo,
                    new TypeReference<Set<AtendTodosMedicos>>() {
                    });
            AtendTodosMedicos.atualizarUltimoId(atendimentos);
            return atendimentos;
        } catch (IOException ex) {
            return new HashSet<AtendTodosMedicos>();
        }
    }

    public Set<AtendTodosMedicos> buscarAtivos() throws DatabindException {
        try {
            Set<AtendTodosMedicos> atendimentos = buscar();
            Set<AtendTodosMedicos> atendimentosAtivos = new HashSet<>();
            for (AtendTodosMedicos atendimento : atendimentos) {
                if (atendimento.getAtivo()) {
                    atendimentosAtivos.add(atendimento);
                }
            }
            AtendTodosMedicos.atualizarUltimoId(atendimentos);
            return atendimentosAtivos;
        } catch (IOException ex) {
            return new HashSet<AtendTodosMedicos>();
        }
    }

    @Override
    public boolean gravar(AtendTodosMedicos objeto) throws StreamWriteException, IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'gravar'");
    }

}
