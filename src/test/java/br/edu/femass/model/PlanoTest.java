package br.edu.femass.model;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class PlanoTest {

    private Set<Plano> planos;

    @Test
    public void setUp() throws Exception {
        this.planos = new HashSet<>();
        this.planos.add(new Plano("Plano A"));
        this.planos.add(new Plano("Plano B"));
    }

    @Test
    public void tearDown() throws Exception {
        this.planos = null;
        Plano.atualizarUltimoId(new HashSet<>()); // Zerando o último código para evitar interferência com outros testes
    }

    @Test
    public void testGetId() {
        Plano planoA = this.planos.stream().filter(p -> p.getNome().equals("Plano A")).findFirst().get();
        assertEquals(Long.valueOf(1), planoA.getId());

        Plano planoB = this.planos.stream().filter(p -> p.getNome().equals("Plano B")).findFirst().get();
        assertEquals(Long.valueOf(2), planoB.getId());
    }

    @Test
    public void testSetNome() {
        Plano planoA = this.planos.stream().filter(p -> p.getNome().equals("Plano A")).findFirst().get();
        planoA.setNome("Plano C");
        assertEquals("Plano C", planoA.getNome());
    }

    @Test
    public void testSetAtivo() {
        Plano planoA = this.planos.stream().filter(p -> p.getNome().equals("Plano A")).findFirst().get();
        planoA.setAtivo(false);
        assertFalse(planoA.getAtivo());
    }

    @Test
    public void testAtualizarUltimoId() {
        Plano.atualizarUltimoId(this.planos);
        assertEquals(Long.valueOf(2), Plano.getIdUltimoCodigo());

        this.planos.add(new Plano("Plano C"));
        Plano.atualizarUltimoId(this.planos);
        assertEquals(Long.valueOf(3), Plano.getIdUltimoCodigo());
    }

}
