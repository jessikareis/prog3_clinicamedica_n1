package br.edu.femass.dao;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Persistencia {
    protected File arquivo;
    protected ObjectMapper objectMapper = new ObjectMapper();

    public Persistencia(String nomeArquivo) {
        arquivo = new File(nomeArquivo);
    }

}