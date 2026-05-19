package com.example.ecomerce.demo.controller;

import com.example.ecomerce.demo.entities.Avaliacao;
import com.example.ecomerce.demo.repository.AvaliacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/avaliacoes")
@RequiredArgsConstructor
public class AvaliacaoController {

    private final AvaliacaoRepository repository;

    // GET /api/v1/avaliacoes
    @GetMapping
    public ResponseEntity<Iterable<Avaliacao>> listar() {
        return ResponseEntity.ok(repository.findAll());
    }

    // GET /api/v1/avaliacoes/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Avaliacao> buscar(@PathVariable long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/v1/avaliacoes/produto/5 — todas as avaliações do produto com id=5
    @GetMapping("/produto/{produtoId}")
    public ResponseEntity<List<Avaliacao>> porProduto(@PathVariable Long produtoId) {
        return ResponseEntity.ok(repository.findByProdutoId(produtoId));
    }
}