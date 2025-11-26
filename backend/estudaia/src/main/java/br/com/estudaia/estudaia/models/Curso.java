package br.com.estudaia.estudaia.models;

import jakarta.persistence.*;

import java.util.List;

import lombok.*;

@Entity
@Table(name = "cursos")
@Data
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nome;
    
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
    private List<Disciplina> disciplinas;
}