package br.com.estudaia.estudaia.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

import br.com.estudaia.estudaia.enums.PerfilUsuario;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String nome;
    
    @Column(unique = true, nullable = false, length = 150)
    private String email;
    
    @Column(nullable = false)
    private String senha;
    
    @Enumerated(EnumType.STRING)
    private PerfilUsuario perfil = PerfilUsuario.USER;
    
    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao = LocalDateTime.now();
}