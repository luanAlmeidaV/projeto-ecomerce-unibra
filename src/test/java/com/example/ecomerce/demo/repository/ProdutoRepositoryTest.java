package com.example.ecomerce.demo.repository;

import com.example.ecomerce.demo.entities.Produto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProdutoRepositoryTest {

    @Autowired
    private ProdutoRepository repository;

    @Test
    void deveSalvarEBuscarProdutoPorNome() {
        Produto produto = new Produto();
        produto.setNome("Notebook Dell");
        produto.setPreco(new BigDecimal("3500.00"));
        produto.setEstoque(5);
        produto.setDescricao("Notebook i5");

        repository.save(produto);

        List<Produto> resultado = repository.findByNomeContainingIgnoreCase("notebook");

        assertFalse(resultado.isEmpty());
        assertEquals("Notebook Dell", resultado.get(0).getNome());
    }

    @Test
    void deveVerificarSeNomeExiste() {
        Produto produto = new Produto();
        produto.setNome("Mouse Gamer");
        produto.setPreco(new BigDecimal("150.00"));
        produto.setEstoque(10);

        repository.save(produto);

        assertTrue(repository.existsByNomeIgnoreCase("mouse gamer"));
    }

    @Test
    void deveBuscarPorFaixaDePreco() {
        Produto produto = new Produto();
        produto.setNome("Teclado Mecânico");
        produto.setPreco(new BigDecimal("250.00"));
        produto.setEstoque(7);

        repository.save(produto);

        List<Produto> resultado = repository.findByPrecoBetween(
                new BigDecimal("100.00"),
                new BigDecimal("300.00")
        );

        assertFalse(resultado.isEmpty());
    }
}
