package com.example.ecomerce.demo.service;

import com.example.ecomerce.demo.entities.Produto;
import com.example.ecomerce.demo.exception.ResourceNotFoundException;
import com.example.ecomerce.demo.repository.ProdutoRepository;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRepository repository;

    @Transactional(readOnly = true)
    public Iterable<Produto> listarTodos() { return repository.findAll(); }

    @Transactional(readOnly = true)
    public Produto buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Produto", id));
    }

    @Transactional(readOnly = true)
    public List<Produto> buscarPorNome(String nome) { return repository.findByNomeContainingIgnoreCase(nome); }

    @Transactional(readOnly = true)
    public List<Produto> buscarPorCategoria(Long categoriaId) { return repository.findByCategoriaId(categoriaId); }

    @Transactional(readOnly = true)
    public List<Produto> buscarPorPreco(BigDecimal min, BigDecimal max) { return repository.findByPrecoBetween(min, max); }

    @Transactional
    public Produto salvar(Produto produto) {
        if (repository.existsByNomeIgnoreCase(produto.getNome())) {
            throw new ValidationException("Já existe um produto com esse nome");
        }
        return repository.save(produto);
    }

    @Transactional
    public Produto atualizar(Long id, Produto dados) {
        Produto atual = buscarPorId(id);
        atual.setNome(dados.getNome());
        atual.setPreco(dados.getPreco());
        atual.setDescricao(dados.getDescricao());
        atual.setCategoria(dados.getCategoria());
        atual.setEstoque(dados.getEstoque());
        return repository.save(atual);
    }

    @Transactional
    public void deletar(Long id) {
        if (!repository.existsById(id)) throw new ResourceNotFoundException("Produto", id);
        repository.deleteById(id);
    }
}
