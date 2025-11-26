package br.com.estudaia.estudaia.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "alternativas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alternativa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String texto;

    @Column(nullable = false)
    private Boolean correta = false;
    
    @Column(length = 1)
    private String letra;

    @ManyToOne
    @JoinColumn(name = "questao_id", nullable = false)
    private Questao questao;
}