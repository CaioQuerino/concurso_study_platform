package br.com.estudaia.estudaia.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "disciplinas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Disciplina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String nome;
    
    @Column(columnDefinition = "TEXT")
    private String descricao;
    
    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;
    
    @OneToMany(mappedBy = "disciplina", cascade = CascadeType.ALL)
    private List<Questao> questões;
}