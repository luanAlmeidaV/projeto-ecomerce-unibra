package com.example.ecomerce.demo.service;

import com.example.ecomerce.demo.entities.Usuario;
import com.example.ecomerce.demo.exception.ResourceNotFoundException;
import com.example.ecomerce.demo.repository.UsuarioRepository;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository repository;

    @Transactional(readOnly = true)
    public Iterable<Usuario> listarTodos() { return repository.findAll(); }

    @Transactional(readOnly = true)
    public Usuario buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário", id));
    }

    @Transactional(readOnly = true)
    public List<Usuario> buscarPorNome(String nome) { return repository.findByNomeContainingIgnoreCase(nome); }

    @Transactional
    public Usuario salvar(Usuario usuario) {
        if (repository.existsByEmailIgnoreCase(usuario.getEmail())) {
            throw new ValidationException("Já existe um usuário com esse e-mail");
        }
        return repository.save(usuario);
    }

    @Transactional
    public Usuario atualizar(Long id, Usuario dados) {
        Usuario atual = buscarPorId(id);
        atual.setNome(dados.getNome());
        atual.setEmail(dados.getEmail());
        atual.setSenha(dados.getSenha());
        return repository.save(atual);
    }

    @Transactional
    public void deletar(Long id) {
        if (!repository.existsById(id)) throw new ResourceNotFoundException("Usuário", id);
        repository.deleteById(id);
    }
}
