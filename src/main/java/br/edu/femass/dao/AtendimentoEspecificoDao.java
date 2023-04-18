package br.edu.femass.dao;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;

import br.edu.femass.model.AtendimentoEspecifico;

public class AtendimentoEspecificoDao extends Persistencia implements Dao<AtendimentoEspecifico> {

    public AtendimentoEspecificoDao(String nomeArquivo) {
        super(nomeArquivo);
        // TODO Auto-generated constructor stub
    }

    public void AtendimentoEspecifico() {

    }

    public boolean exibir(AtendimentoEspecifico objeto) throws StreamWriteException, IOException {
        Set<AtendimentoEspecifico> atendimentos = buscar();
        boolean exibiu = atendimentos.add(objeto);
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, atendimentos);
        return exibiu;
    }

    public boolean excluir(AtendimentoEspecifico objeto) throws StreamWriteException, IOException {
        Set<AtendimentoEspecifico> atendimentos = buscar();
        for (AtendimentoEspecifico atendimentoSelecionado : atendimentos) {
            if (atendimentoSelecionado.getId().equals(objeto.getId())) {
                atendimentoSelecionado.setAtivo(false);
            }
        }

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, atendimentos);
        return true;
    }

    public Set<AtendimentoEspecifico> buscar() throws DatabindException {
        try {
            Set<AtendimentoEspecifico> atendimentos = objectMapper.readValue(arquivo,
                    new TypeReference<Set<AtendimentoEspecifico>>() {
                    });
            AtendimentoEspecifico.atualizarUltimoId(atendimentos);
            return atendimentos;
        } catch (IOException ex) {
            return new HashSet<AtendimentoEspecifico>();
        }
    }

    public Set<AtendimentoEspecifico> buscarAtivos() throws DatabindException {
        try {
            Set<AtendimentoEspecifico> atendimentos = buscar();
            Set<AtendimentoEspecifico> atendimentosAtivos = new HashSet<>();
            for (AtendimentoEspecifico atendimento : atendimentos) {
                if (atendimento.getAtivo()) {
                    atendimentosAtivos.add(atendimento);
                }
            }
            AtendimentoEspecifico.atualizarUltimoId(atendimentos);
            return atendimentosAtivos;
        } catch (IOException ex) {
            return new HashSet<AtendimentoEspecifico>();
        }
    }

    @Override
    public boolean gravar(br.edu.femass.model.AtendimentoEspecifico objeto) throws StreamWriteException, IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'gravar'");
    }

}
