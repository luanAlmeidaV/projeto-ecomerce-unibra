package com.example.ecomerce.demo.controller;

import com.example.ecomerce.demo.entities.Promocao;
import com.example.ecomerce.demo.service.PromocaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/promocoes")
@RequiredArgsConstructor
public class PromocaoController {

    private final PromocaoService service;

    @GetMapping
    public ResponseEntity<Iterable<Promocao>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Promocao> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/vigentes")
    public ResponseEntity<List<Promocao>> vigentes() {
        return ResponseEntity.ok(service.listarVigentes());
    }

    @PostMapping
    public ResponseEntity<Promocao> criar(@Valid @RequestBody Promocao promocao) {
        return ResponseEntity.status(201).body(service.salvar(promocao));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Promocao> atualizar(@PathVariable Long id, @Valid @RequestBody Promocao promocao) {
        return ResponseEntity.ok(service.atualizar(id, promocao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
