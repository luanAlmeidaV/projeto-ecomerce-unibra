package com.example.ecomerce.demo.controller;


import com.example.ecomerce.demo.entities.Produto;
import com.example.ecomerce.demo.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/produtos")
@RequiredArgsConstructor
public class ProdutoController{

    private final ProdutoRepository repository;

    // GET /api/v1/produtos
    @GetMapping
    public ResponseEntity<Iterable<Produto>> listar() {
        return ResponseEntity.ok(repository.findAll());
    }

    // GET /api/v1/produtos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscar(@PathVariable long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/v1/produtos/busca?nome=tenis — busca case-insensitive por trecho do nome
    @GetMapping("/busca")
    public ResponseEntity<List<Produto>> buscarPorNome(@RequestParam String nome) {
        return ResponseEntity.ok(repository.findByNomeContainingIgnoreCase(nome));
    }

    // GET /api/v1/produtos/categoria/3 — todos os produtos de uma categoria
    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<Produto>> porCategoria(@PathVariable Long categoriaId) {
        return ResponseEntity.ok(repository.findByCategoriaId(categoriaId));
    }
}