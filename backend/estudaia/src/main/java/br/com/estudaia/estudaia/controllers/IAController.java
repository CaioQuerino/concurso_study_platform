package br.com.estudaia.estudaia.controllers;

import br.com.estudaia.estudaia.dto.*;

import br.com.estudaia.estudaia.models.PlanoEstudo;

import br.com.estudaia.estudaia.services.IAStudyService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/ia")
@AllArgsConstructor
public class IAController {
    
    private final IAStudyService iaStudyService;
    
    @GetMapping("/explicacao/{questaoId}")
    public ApiResponse<String> gerarExplicacaoQuestao(@PathVariable Long questaoId) {
        return iaStudyService.gerarExplicacaoQuestao(questaoId);
    }
    
    @GetMapping("/questoes-personalizadas/{usuarioId}")
    public ApiResponse<List<QuestaoDTO>> gerarQuestoesPersonalizadas(@PathVariable Long usuarioId) {
        return iaStudyService.gerarQuestoesPersonalizadas(usuarioId);
    }
    
    @PostMapping("/plano-estudo/{usuarioId}/{concursoId}")
    public ApiResponse<PlanoEstudo> gerarPlanoEstudo(@PathVariable Long usuarioId, 
                                                   @PathVariable Long concursoId) {
        return iaStudyService.gerarPlanoEstudo(usuarioId, concursoId);
    }
}