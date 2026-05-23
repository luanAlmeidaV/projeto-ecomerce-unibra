package com.example.ecomerce.demo.controller;

import com.example.ecomerce.demo.entities.Avaliacao;
import com.example.ecomerce.demo.service.AvaliacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/avaliacoes")
@RequiredArgsConstructor
public class AvaliacaoController {

    private final AvaliacaoService service;

    @GetMapping
    public ResponseEntity<Iterable<Avaliacao>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Avaliacao> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/produto/{produtoId}")
    public ResponseEntity<List<Avaliacao>> porProduto(@PathVariable Long produtoId) {
        return ResponseEntity.ok(service.buscarPorProduto(produtoId));
    }

    @PostMapping
    public ResponseEntity<Avaliacao> criar(@Valid @RequestBody Avaliacao avaliacao) {
        return ResponseEntity.status(201).body(service.salvar(avaliacao));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Avaliacao> atualizar(@PathVariable Long id, @Valid @RequestBody Avaliacao avaliacao) {
        return ResponseEntity.ok(service.atualizar(id, avaliacao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
