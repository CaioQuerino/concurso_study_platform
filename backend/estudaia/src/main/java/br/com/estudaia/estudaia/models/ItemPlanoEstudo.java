package br.com.estudaia.estudaia.models;

import br.com.estudaia.estudaia.enums.StatusItem;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "itens_plano_estudo")
@Data
@NoArgsConstructor
@AllArgsConstructor
class ItemPlanoEstudo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "plano_estudo_id", nullable = false)
    private PlanoEstudo planoEstudo;
    
    @ManyToOne
    @JoinColumn(name = "disciplina_id", nullable = false)
    private Disciplina disciplina;
    
    private Integer horasSemanais;
    
    private Integer ordem;
    
    @Enumerated(EnumType.STRING)
    private StatusItem status = StatusItem.PENDENTE;
}