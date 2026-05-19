package com.example.ecomerce.demo.repository;

import com.example.ecomerce.demo.entities.Promocao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PromocaoRepository extends CrudRepository<Promocao, Long> {

    List<Promocao> findByNomeContainingIgnoreCase(String nome);

    // Retorna promoções vigentes na data informada: dataInicio <= hoje E dataFim >= hoje
    // O parâmetro é passado duas vezes pois o Spring Data não reutiliza o mesmo valor
    // em condições AND com campos diferentes.
    List<Promocao> findByDataInicioLessThanEqualAndDataFimGreaterThanEqual(
            LocalDate hoje, LocalDate hojeRepetido
    );
}