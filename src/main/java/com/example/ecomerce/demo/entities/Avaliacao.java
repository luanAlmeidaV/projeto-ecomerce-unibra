package com.example.ecomerce.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import javax.validation.constraints.*;
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
    private Long id;

    @NotNull(message = "A nota é obrigatória")
    @Min(value = 1, message = "A nota mínima é 1")
    @Max(value = 5, message = "A nota máxima é 5")
    @Column(nullable = false)
    private Integer nota;

    @Size(max = 500, message = "O comentário deve ter no máximo 500 caracteres")
    @Column(length = 500)
    private String comentario;

    @NotBlank(message = "O autor é obrigatório")
    @Size(max = 100, message = "O autor deve ter no máximo 100 caracteres")
    @Column(nullable = false, length = 100)
    private String autor;

    @NotNull(message = "O produto é obrigatório")
    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    @JsonIgnoreProperties({"avaliacoes", "promocoes", "categoria"})
    private Produto produto;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime criadoEm;
}
