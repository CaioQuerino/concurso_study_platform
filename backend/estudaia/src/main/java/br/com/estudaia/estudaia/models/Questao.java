package br.com.estudaia.estudaia.models;

import jakarta.persistence.*;

import java.util.List;

import br.com.estudaia.estudaia.enums.NivelDificuldade;
import lombok.Data;

@Entity
@Table(name = "questoes")
@Data
public class Questao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(columnDefinition = "TEXT")
    private String enunciado;
    
    @ManyToOne
    @JoinColumn(name = "disciplina_id")
    private Disciplina disciplina;
    
    @ManyToOne
    @JoinColumn(name = "concurso_id")
    private Concurso concurso;
    
    private Integer ano;
    
    @Enumerated(EnumType.STRING)
    private NivelDificuldade nivelDificuldade;
    
    @OneToMany(mappedBy = "questao", cascade = CascadeType.ALL)
    private List<Alternativa> alternativas;
}