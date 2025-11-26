package br.com.estudaia.estudaia.dto;

import java.util.List;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestaoDTO {
    private Long id;
    private String enunciado;
    private String disciplinaNome;
    private String concursoNome;
    private Integer ano;
    private String nivelDificuldade;
    private List<AlternativaDTO> alternativas;
}