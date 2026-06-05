package com.example.ecomerce.demo.service;

import com.example.ecomerce.demo.entities.Produto;
import com.example.ecomerce.demo.exception.ResourceNotFoundException;
import com.example.ecomerce.demo.repository.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ValidationException;
import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {

    @Mock
    private ProdutoRepository repository;

    @InjectMocks
    private ProdutoService service;

    @Test
    void deveSalvarProdutoQuandoNomeNaoExiste() {
        Produto produto = new Produto();
        produto.setNome("Notebook");
        produto.setPreco(new BigDecimal("3500.00"));
        produto.setEstoque(10);

        when(repository.existsByNomeIgnoreCase("Notebook")).thenReturn(false);
        when(repository.save(produto)).thenReturn(produto);

        Produto resultado = service.salvar(produto);

        assertEquals("Notebook", resultado.getNome());
        verify(repository).save(produto);
    }

    @Test
    void naoDeveSalvarProdutoComNomeDuplicado() {
        Produto produto = new Produto();
        produto.setNome("Notebook");

        when(repository.existsByNomeIgnoreCase("Notebook")).thenReturn(true);

        assertThrows(ValidationException.class, () -> service.salvar(produto));
        verify(repository, never()).save(produto);
    }

    @Test
    void deveBuscarProdutoPorId() {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Mouse");

        when(repository.findById(1L)).thenReturn(Optional.of(produto));

        Produto resultado = service.buscarPorId(1L);

        assertEquals(1L, resultado.getId());
        assertEquals("Mouse", resultado.getNome());
    }

    @Test
    void deveLancarErroQuandoProdutoNaoExiste() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.buscarPorId(99L));
    }
}
