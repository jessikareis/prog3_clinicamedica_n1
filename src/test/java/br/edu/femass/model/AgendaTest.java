package br.edu.femass.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class AgendaTest {

    private Agenda agenda;

    @Test
    public void setUp() {
        Paciente paciente = new Paciente();
        Plano plano = new Plano();
        Medico medico = new Medico("Alexandre", "215425896");
        Esp especialidade = new Esp("Cardiologista", medico);
        agenda = new Agenda("10:00", "20-06-2023", paciente, plano, medico, especialidade);
    }

    @Test
    public void testGetId() {
        assertEquals(Long.valueOf(1), agenda.getId());
    }

    @Test
    public void testGetHora() {
        assertEquals("10:00", agenda.getHora());
    }

    @Test
    public void testGetData() {
        assertEquals("2023-05-01", agenda.getData());
    }

    @Test
    public void testGetPaciente() {
        Paciente paciente = agenda.getPaciente();
        assertEquals("João", paciente.getNome());
        assertEquals("11111111111", paciente.getCpf());
    }

    @Test
    public void testGetPlano() {
        Plano plano = agenda.getPlano();
        assertEquals("Plano de saúde A", plano.getNome());
    }

    @Test
    public void testGetMedico() {
        Medico medico = agenda.getMedico();
        assertEquals("Alexandre", medico.getNome());
        assertEquals("2222222222", medico.getTelefoneM());
    }

    @Test
    public void testGetEsp() {
        Esp esp = agenda.getEsp();
        assertEquals("Cardiologia", esp.getNome());
    }

    @Test
    public void testGetAtivo() {
        assertEquals(Boolean.TRUE, agenda.getAtivo());
    }

    @Test
    public void testAtualizarUltimoId() {
        Set<Agenda> agendas = new HashSet<>();
        agendas.add(new Agenda());
        agendas.add(new Agenda());
        agendas.add(new Agenda());
        Agenda.atualizarUltimoId(agendas);
    }

}