package com.example.ecomerce.demo.service;

import com.example.ecomerce.demo.entities.Avaliacao;
import com.example.ecomerce.demo.exception.ResourceNotFoundException;
import com.example.ecomerce.demo.repository.AvaliacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvaliacaoService {
    private final AvaliacaoRepository repository;

    @Transactional(readOnly = true)
    public Iterable<Avaliacao> listarTodos() { return repository.findAll(); }

    @Transactional(readOnly = true)
    public Avaliacao buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Avaliação", id));
    }

    @Transactional(readOnly = true)
    public List<Avaliacao> buscarPorProduto(Long produtoId) { return repository.findByProdutoId(produtoId); }

    @Transactional
    public Avaliacao salvar(Avaliacao avaliacao) { return repository.save(avaliacao); }

    @Transactional
    public Avaliacao atualizar(Long id, Avaliacao dados) {
        Avaliacao atual = buscarPorId(id);
        atual.setNota(dados.getNota());
        atual.setComentario(dados.getComentario());
        atual.setAutor(dados.getAutor());
        atual.setProduto(dados.getProduto());
        return repository.save(atual);
    }

    @Transactional
    public void deletar(Long id) {
        if (!repository.existsById(id)) throw new ResourceNotFoundException("Avaliação", id);
        repository.deleteById(id);
    }
}
