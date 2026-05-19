package com.example.ecomerce.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "avaliacoes")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString(exclude = "produto")
@EqualsAndHashCode(of = "id")
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer nota;

    @Size(max = 500)
    @Column(length = 500)
    private String comentario;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String autor;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime CriadoEm;


}

