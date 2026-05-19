package com.example.ecomerce.demo.controller;


import com.example.ecomerce.demo.entities.Categoria;
import com.example.ecomerce.demo.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apiv1/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaRepository repository;

    // GET /api/v1/categorias — HTTP 200 com lista completa
    @GetMapping
    public ResponseEntity<Iterable<Categoria>> listar() {
        return ResponseEntity.ok(repository.findAll());
    }

    // GET /api/v1/categorias/{id} — HTTP 200 se encontrado, 404 caso contrário
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscar(@PathVariable long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

