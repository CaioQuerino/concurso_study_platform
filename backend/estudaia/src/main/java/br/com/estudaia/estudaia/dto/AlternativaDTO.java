package br.com.estudaia.estudaia.dto;

import lombok.*;

@Data
@Getter
@Setter
public class AlternativaDTO {
    private Long id;
    private String texto;
    private Boolean correta;
}