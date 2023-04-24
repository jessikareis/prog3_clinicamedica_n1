package br.edu.femass.dao;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;

import br.edu.femass.model.Esp;
import br.edu.femass.model.Medico;

public class EspDao extends Persistencia implements Dao<Esp> {

    public EspDao() {
        super("esps.json");
    }

    public boolean gravar(Esp objeto) throws StreamWriteException, IOException {
        Set<Esp> esps = buscar();
        boolean gravou = esps.add(objeto);
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, esps);
        return gravou;
    }

    public boolean excluir(Esp objeto) throws StreamWriteException, IOException {
        Set<Esp> esps = buscar();
        for (Esp espSelecionado : esps) {
            if (espSelecionado.getId().equals(objeto.getId())) {
                espSelecionado.setAtivo(false);
            }
        }

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, esps);
        return true;
    }

    public Set<Esp> buscar() throws DatabindException {
        try {
            Set<Esp> esps = objectMapper.readValue(arquivo, new TypeReference<Set<Esp>>() {
            });
            Esp.atualizarUltimoId(esps);
            return esps;
        } catch (IOException ex) {
            return new HashSet<Esp>();
        }
    }

    public Set<Esp> buscarAtivos() throws DatabindException {
        try {
            Set<Esp> esps = buscar();
            Set<Esp> espsAtivos = new HashSet<>();
            for (Esp esp : esps) {
                if (esp.getAtivo()) {
                    espsAtivos.add(esp);
                }
            }
            Esp.atualizarUltimoId(esps);
            return espsAtivos;
        } catch (IOException ex) {
            return new HashSet<Esp>();
        }
    }
    public Set<Esp> buscarPorMedico(Medico med) throws DatabindException {
        try {
            Set<Esp> EspsAtivos = buscarAtivos();
            Set<Esp> EspsMed = new HashSet<>();
            for (Esp Esp  : EspsAtivos) {
                if (Esp.getMed().getId()==med.getId()) {
                    EspsMed.add(Esp);
                }
            }
            Esp.atualizarUltimoId(EspsAtivos);
            return EspsMed;
        } catch (IOException ex) {
            return new HashSet<Esp>();
        }
    }
}