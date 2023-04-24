package br.edu.femass.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

class MedicoTest {

    private Set<Medico> medicos;

    private Medico medico;

    @Test
    void setUp() {
        medico = new Medico("Guilherme", "123456789");
        medico.setId(1L);
        medicos = new HashSet<>();
    }

    @Test
    void testGetId() {
        assertEquals(1L, medico.getId());
    }

    @Test
    void testSetId() {
        medico.setId(2L);
        assertEquals(2L, medico.getId().longValue());
    }

    @Test
    void testGetNome() {
        assertEquals("Guilherme", medico.getNome());
    }

    @Test
    void testSetNome() {
        medico.setNome("Dr. Grey");
        assertEquals("Dr. Grey", medico.getNome());
    }

    @Test
    void testGetTelefoneM() {
        assertEquals("123456789", medico.getTelefoneM());
    }

    @Test
    void testSetTelefoneM() {
        medico.setTelefoneM("987654321");
        assertEquals("987654321", medico.getTelefoneM());
    }

    @Test
    void testGetAtivo() {
        assertTrue(medico.getAtivo());
    }

    @Test
    void testSetAtivo() {
        medico.setAtivo(false);
        assertFalse(medico.getAtivo());
    }

    @Test
    void testToString() {
        assertEquals("Guilherme", medico.toString());
    }

    @Test
    void testAtualizarUltimoId() {
        medicos.add(new Medico("Guilherme", "555555"));
        medicos.add(new Medico("Auxiliadora", "666666"));
        medicos.add(new Medico("Alexandre", "777777"));

        Medico.atualizarUltimoId(medicos);
    }
}