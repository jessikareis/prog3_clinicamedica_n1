package br.edu.femass.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class EspecialidadeTest {

    @Test
    public void testEspecialidade() {
        Especialidade e = new Especialidade("Cardiologia");
        assertEquals("Cardiologia", e.getNome());
        assertTrue(e.getAtivo());
    }

    @Test
    public void testSetAtivo() {
        Especialidade e = new Especialidade("Oftalmologia");
        assertTrue(e.getAtivo());
        e.setAtivo(false);
        assertFalse(e.getAtivo());
    }

    @Test
    public void testToString() {
        Especialidade e = new Especialidade("Ortopedia");
        assertEquals("Ortopedia", e.toString());
    }
}
