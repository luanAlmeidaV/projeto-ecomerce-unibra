package com.example.ecomerce.demo.service;

import com.example.ecomerce.demo.entities.Categoria;
import com.example.ecomerce.demo.exception.ResourceNotFoundException;
import com.example.ecomerce.demo.repository.CategoriaRepository;
import javax.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoriaService {
    private final CategoriaRepository repository;

    @Transactional(readOnly = true)
    public Iterable<Categoria> listarTodos() { return repository.findAll(); }

    @Transactional(readOnly = true)
    public Categoria buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Categoria", id));
    }

    @Transactional
    public Categoria salvar(Categoria categoria) {
        if (repository.existsByNomeIgnoreCase(categoria.getNome())) {
            throw new ValidationException("Já existe uma categoria com esse nome");
        }
        return repository.save(categoria);
    }

    @Transactional
    public Categoria atualizar(Long id, Categoria dados) {
        Categoria atual = buscarPorId(id);
        atual.setNome(dados.getNome());
        atual.setDescricao(dados.getDescricao());
        return repository.save(atual);
    }

    @Transactional
    public void deletar(Long id) {
        if (!repository.existsById(id)) throw new ResourceNotFoundException("Categoria", id);
        repository.deleteById(id);
    }
}
