package com.example.ecomerce.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "promocoes")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString(exclude = "produtos")
@EqualsAndHashCode(of = "id")
public class Promocao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 150)
    @Column(nullable = false, length = 150)
    private String nome;

    @DecimalMin("0.01") @DecimalMax("99.99")
    @Column(precision = 5, scale = 2, nullable = false)
    private BigDecimal descontoPercent;

    @Column(nullable = false)
    private LocalDate dataInicio;        // primeiro dia em que a promoção é válida

    @Column(nullable = false)
    private LocalDate dataFim;           // último dia em que a promoção é válida

    @JsonIgnore
    @ManyToMany(mappedBy = "promocoes")
    private Set<Produto> produtos = new HashSet<>();

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime criadoEm;
}