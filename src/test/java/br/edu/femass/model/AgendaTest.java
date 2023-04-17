package br.edu.femass.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;


public class AgendaTest {

    private Agenda agenda;

    @Test
    public void setUp() {
        Paciente paciente = new Paciente();
        Plano plano = new Plano();
        Medico medico = new Medico("Dr. José", "215425896", new Especialidade("Cardiologista"));
        Especialidade especialidade = new Especialidade("Cardiologista");
        agenda = new Agenda("10:00", "20-04-2023", paciente, plano, medico, especialidade);
    }

    @Test
    public void testGetId() {
        assertNotNull(agenda.getId());
    }

    @Test
    public void testGetHora() {
        assertEquals("10:00", agenda.getHora());
    }

    @Test
    public void testSetHora() {
        agenda.setHora("08:00");
        assertEquals("12:00", agenda.getHora());
    }

    @Test
    public void testGetData() {
        assertEquals("20-04-2023", agenda.getData());
    }

    @Test
    public void testSetData() {
        agenda.setData("20-04-2023");
        assertEquals("20-04-2023", agenda.getData());
    }

    @Test
    public void testGetPaciente() {
        assertNotNull(agenda.getPaciente());
    }

    @Test
    public void testSetPaciente() {
        Paciente novoPaciente = new Paciente();
        agenda.setPaciente(novoPaciente);
        assertEquals(novoPaciente, agenda.getPaciente());
    }

    @Test
    public void testGetPlano() {
        assertNotNull(agenda.getPlano());
    }

    @Test
    public void testSetPlano() {
        Plano novoPlano = new Plano("Amil");
        agenda.setPlano(novoPlano);
        assertEquals(novoPlano, agenda.getPlano());
    }

    @Test
    public void testGetMedico() {
        assertNotNull(agenda.getMedico());
    }

    @Test
    public void testSetMedico() {
        Medico novoMedico = new Medico("Dra. Ana", "254154321", new Especialidade("Oftalmologista"));
        agenda.setMedico(novoMedico);
        assertEquals(novoMedico, agenda.getMedico());
    }

    @Test
    public void testGetEspecialidade() {
        assertNotNull(agenda.getEspecialidade());
    }

    @Test
    public void testSetEspecialidade() {
        Especialidade especialidade = new Especialidade("Pediatria");
        agenda.setEspecialidade(especialidade);
        assertEquals(especialidade, agenda.getEspecialidade());
    }

    @Test
    public void testGetAtivo() {
        assertTrue(agenda.getAtivo());
    }

    @Test
    public void testSetAtivo() {
        agenda.setAtivo(false);
        assertFalse(agenda.getAtivo());
    }

    @Test
    public void testAtualizarUltimoId() {
        Set<Agenda> agendas = new HashSet<>();
        agendas.add(new Agenda("09:00", "2022-04-06", 
        new Paciente("Maria", "09871547321", null, null), 
        new Plano("Plano de Saúde B"), new Medico("Dr. Ana", null, null), 
        new Especialidade("Pediatria")));
    
}
}