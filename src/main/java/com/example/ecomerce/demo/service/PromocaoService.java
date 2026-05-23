package com.example.ecomerce.demo.service;

import com.example.ecomerce.demo.entities.Promocao;
import com.example.ecomerce.demo.exception.ResourceNotFoundException;
import com.example.ecomerce.demo.repository.PromocaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PromocaoService {
    private final PromocaoRepository repository;

    @Transactional(readOnly = true)
    public Iterable<Promocao> listarTodos() { return repository.findAll(); }

    @Transactional(readOnly = true)
    public Promocao buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Promoção", id));
    }

    @Transactional(readOnly = true)
    public List<Promocao> listarVigentes() {
        LocalDate hoje = LocalDate.now();
        return repository.findByDataInicioLessThanEqualAndDataFimGreaterThanEqual(hoje, hoje);
    }

    @Transactional
    public Promocao salvar(Promocao promocao) { return repository.save(promocao); }

    @Transactional
    public Promocao atualizar(Long id, Promocao dados) {
        Promocao atual = buscarPorId(id);
        atual.setNome(dados.getNome());
        atual.setDescontoPercent(dados.getDescontoPercent());
        atual.setDataInicio(dados.getDataInicio());
        atual.setDataFim(dados.getDataFim());
        atual.setProdutos(dados.getProdutos());
        return repository.save(atual);
    }

    @Transactional
    public void deletar(Long id) {
        if (!repository.existsById(id)) throw new ResourceNotFoundException("Promoção", id);
        repository.deleteById(id);
    }
}
