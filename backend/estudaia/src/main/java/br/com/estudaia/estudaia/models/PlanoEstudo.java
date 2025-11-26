package br.com.estudaia.estudaia.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

import br.com.estudaia.estudaia.enums.StatusPlano;

@Entity
@Table(name = "planos_estudo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanoEstudo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nome;
    
    @Column(columnDefinition = "TEXT")
    private String descricao;
    
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
    
    @ManyToOne
    @JoinColumn(name = "concurso_id", nullable = false)
    private Concurso concurso;
    
    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao = LocalDateTime.now();
    
    @Column(name = "duracao_dias")
    private Integer duracaoDias;
    
    @Enumerated(EnumType.STRING)
    private StatusPlano status = StatusPlano.ATIVO;
    
    @OneToMany(mappedBy = "planoEstudo", cascade = CascadeType.ALL)
    private List<ItemPlanoEstudo> itens;
}