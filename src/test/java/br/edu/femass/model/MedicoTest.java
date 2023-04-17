package br.edu.femass.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


public class MedicoTest {

    @Test
    public void testGetNome() {
        Medico medico = new Medico("João", "9999999", new Especialidade("Pediatria"));
        assertEquals("João", medico.getNome());
    }

    @Test
    public void testSetNome() {
        Medico medico = new Medico("João", "9999999", new Especialidade("Pediatria"));
        medico.setNome("Maria");
        assertEquals("Maria", medico.getNome());
    }

    @Test
    public void testGetTelefoneM() {
        Medico medico = new Medico("João", "9999999", new Especialidade("Pediatria"));
        assertEquals("9999999", medico.getTelefoneM());
    }

    @Test
    public void testSetTelefoneM() {
        Medico medico = new Medico("João", "9999999", new Especialidade("Pediatria"));
        medico.setTelefoneM("8888888");
        assertEquals("8888888", medico.getTelefoneM());
    }

    @Test
    public void testGetAtivo() {
        Medico medico = new Medico("João", "9999999", new Especialidade("Pediatria"));
        assertTrue(medico.getAtivo());
    }

    @Test
    public void testSetAtivo() {
        Medico medico = new Medico("João", "9999999", new Especialidade("Pediatria"));
        medico.setAtivo(false);
        assertFalse(medico.getAtivo());
    }

    @Test
    public void testGetEspecialidade() {
        Especialidade esp = new Especialidade("Pediatria");
        Medico medico = new Medico("João", "9999999", esp);
        assertEquals(esp, medico.getEspecialidade());
    }

    @Test
    public void testSetEspecialidade() {
        Especialidade esp1 = new Especialidade("Pediatria");
        Especialidade esp2 = new Especialidade("Cardiologia");
        Medico medico = new Medico("João", "9999999", esp1);
        medico.setEspecialidade(esp2);
        assertEquals(esp2, medico.getEspecialidade());
    }
}
