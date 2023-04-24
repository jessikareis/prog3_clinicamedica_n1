package br.edu.femass.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EspTest {

    @Test
    public void testEspConstructor() {
        Medico medico = new Medico("Guilherme", "12345");
        Esp esp = new Esp("Cardiologia", medico);
        assertNotNull(esp);
        assertEquals("Cardiologia", esp.getNome());
        assertEquals(medico, esp.getMed());
        assertTrue(esp.getAtivo());
    }

    @Test
    public void testEspSettersAndGetters() {
        Esp esp = new Esp();
        esp.setId(1L);
        esp.setNome("Pediatra");
        esp.setAtivo(false);
        Medico medico = new Medico("Maria", "67890");
        esp.setMed(medico);
        assertEquals(1L, esp.getId().longValue());
        assertEquals("Pediatra", esp.getNome());
        assertFalse(esp.getAtivo());
        assertEquals(medico, esp.getMed());
    }

    @Test
    public void testEspToString() {
        Esp esp = new Esp("Neurologia", new Medico());
        assertEquals("Neurologia", esp.toString());
    }

    @Test
    public void testAtualizarUltimoId() {
        Esp esp1 = new Esp("Dermatologia", new Medico());
        Esp esp2 = new Esp("Ortopedia", new Medico());
        Esp esp3 = new Esp("Ginecologia", new Medico());
        esp1.setId(1L);
        esp2.setId(3L);
        esp3.setId(2L);
        Esp.atualizarUltimoId(Set.of(esp1, esp2, esp3));
        assertEquals(3L, Esp.ultimoCodigo.longValue());
    }
}
