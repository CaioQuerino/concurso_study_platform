package br.com.estudaia.estudaia.models;

import br.com.estudaia.estudaia.enums.StatusConcurso;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "concursos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Concurso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String nome;

    @Column(length = 100)
    private String orgao;

    @Column(name = "banca_organizadora", length = 100)
    private String bancaOrganizadora;

    @Column(name = "data_prova")
    private LocalDate dataProva;

    @Column(name = "edital_url", columnDefinition = "TEXT")
    private String editalUrl;

    @Enumerated(EnumType.STRING)
    private StatusConcurso status = StatusConcurso.ABERTO;

    @OneToMany(mappedBy = "concurso", cascade = CascadeType.ALL)
    private List<Questao> questões;
}