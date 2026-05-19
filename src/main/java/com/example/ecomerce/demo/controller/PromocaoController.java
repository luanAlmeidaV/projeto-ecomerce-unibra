package com.example.ecomerce.demo.controller;

import com.example.ecomerce.demo.entities.Promocao;
import com.example.ecomerce.demo.repository.PromocaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/promocoes")
@RequiredArgsConstructor
public class PromocaoController {

    private final PromocaoRepository repository;

    @GetMapping
    public ResponseEntity<Iterable<Promocao>> listar() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Promocao> buscar(@PathVariable long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/vigentes")
    public ResponseEntity<List<Promocao>> vigentes() {
        LocalDate hoje = LocalDate.now();
        return ResponseEntity.ok(
                repository.findByDataInicioLessThanEqualAndDataFimGreaterThanEqual(hoje, hoje)
        );
    }
}