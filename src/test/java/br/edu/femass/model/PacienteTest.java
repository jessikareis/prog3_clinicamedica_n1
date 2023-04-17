package br.edu.femass.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class PacienteTest {
    private Paciente paciente;
    
    @Test
    public void testSetNome() {
        paciente.setNome("Maria da Silva");
        assertEquals("Maria da Silva", paciente.getNome());
    }

    @Test
    public void testSetCpf() {
        paciente.setCpf("987.654.321-00");
        assertEquals("987.654.321-00", paciente.getCpf());
    }

    @Test
    public void testSetTelefone() {
        paciente.setTelefone("(11) 8888-8888");
        assertEquals("(11) 8888-8888", paciente.getTelefone());
    }

    @Test
    public void testSetPlano() {
        paciente.setPlano(new Plano("Amil"));
        assertEquals("Amil", paciente.getPlano().getNome());
    }

    @Test
    public void testToString() {
        assertEquals("João da Silva", paciente.toString());
    }

    @Test
    public void testSetAtivo() {
        paciente.setAtivo(false);
        assertEquals(false, paciente.getAtivo());
    }

    @Test
    public void testAtualizarUltimoId() {
        Set<Paciente> pacientes = new HashSet<>();
        pacientes.add(new Paciente("João da Silva", "123.456.789-00", "(11) 9999-9999", new Plano("Unimed")));
        pacientes.add(new Paciente("Maria da Silva", "987.654.321-00", "(11) 8888-8888", new Plano("Amil")));
        pacientes.add(new Paciente("Pedro da Silva", "456.789.123-00", "(11) 7777-7777", new Plano("Sulamérica")));
        Paciente.atualizarUltimoId(pacientes);
        assertEquals(3L, Paciente.ultimoCodigo.longValue());
    }

    @Test
    public void testSetId() {
        paciente.setId(1L);
        assertEquals(1L, paciente.getId().longValue());
    }
}