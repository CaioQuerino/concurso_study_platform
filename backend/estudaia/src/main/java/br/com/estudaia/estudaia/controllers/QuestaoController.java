package br.com.estudaia.estudaia.controllers;

import br.com.estudaia.estudaia.dto.*;
import br.com.estudaia.estudaia.services.QuestaoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questoes")
@AllArgsConstructor
public class QuestaoController {
    
    private final QuestaoService questaoService;
    
    @GetMapping
    public ApiResponse<List<QuestaoDTO>> getAllQuestoes() {
        return ApiResponse.success(questaoService.findAll());
    }
    
    @GetMapping("/{id}")
    public ApiResponse<QuestaoDTO> getQuestaoById(@PathVariable Long id) {
        return ApiResponse.success(questaoService.findById(id));
    }
    
    @GetMapping("/disciplina/{disciplinaId}")
    public ApiResponse<List<QuestaoDTO>> getQuestoesByDisciplina(@PathVariable Long disciplinaId) {
        return ApiResponse.success(questaoService.findByDisciplina(disciplinaId));
    }
    
    @PostMapping
    public ApiResponse<QuestaoDTO> createQuestao(@RequestBody QuestaoDTO questaoDTO) {
        try {
            // Aqui você precisará implementar a lógica de criação no service
            // Por enquanto, retornaremos um mock
            QuestaoDTO createdQuestao = questaoService.create(questaoDTO);
            return ApiResponse.success(createdQuestao);
        } catch (Exception e) {
            return ApiResponse.error("Erro ao criar questão: " + e.getMessage());
        }
    }
    
    @PutMapping("/{id}")
    public ApiResponse<QuestaoDTO> updateQuestao(@PathVariable Long id, @RequestBody QuestaoDTO questaoDTO) {
        try {
            QuestaoDTO updatedQuestao = questaoService.update(id, questaoDTO);
            return ApiResponse.success(updatedQuestao);
        } catch (Exception e) {
            return ApiResponse.error("Erro ao atualizar questão: " + e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteQuestao(@PathVariable Long id) {
        try {
            questaoService.delete(id);
            return ApiResponse.success(null);
        } catch (Exception e) {
            return ApiResponse.error("Erro ao excluir questão: " + e.getMessage());
        }
    }
}