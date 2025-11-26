package br.com.estudaia.estudaia.services;

import br.com.estudaia.estudaia.dto.*;
import br.com.estudaia.estudaia.enums.NivelDificuldade;
import br.com.estudaia.estudaia.models.*;
import br.com.estudaia.estudaia.repositories.QuestaoRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class QuestaoService {
    
    private final QuestaoRepository questaoRepository;
    
    public List<QuestaoDTO> findAll() {
        return questaoRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
    
    public QuestaoDTO findById(Long id) {
        @SuppressWarnings("null")
        Questao questao = questaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Questão não encontrada"));
        return toDTO(questao);
    }
    
    public List<QuestaoDTO> findByDisciplina(Long disciplinaId) {
        return questaoRepository.findByDisciplinaId(disciplinaId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
    
    @SuppressWarnings("null")
    public QuestaoDTO create(QuestaoDTO questaoDTO) {
        Questao questao = toEntity(questaoDTO);
        Questao savedQuestao = questaoRepository.save(questao);
        return toDTO(savedQuestao);
    }

    @SuppressWarnings("null")    
    public QuestaoDTO update(Long id, QuestaoDTO questaoDTO) {
        Questao existingQuestao = questaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Questão não encontrada"));
        
        // Atualiza os campos
        existingQuestao.setEnunciado(questaoDTO.getEnunciado());
        existingQuestao.setAno(questaoDTO.getAno());
        
        // Atualiza disciplina se fornecida
        if (questaoDTO.getDisciplinaNome() != null) {
            // Aqui você precisaria buscar a disciplina pelo nome
            // Por simplicidade, vamos manter a disciplina existente
        }
        
        // Atualiza alternativas
        if (questaoDTO.getAlternativas() != null) {
            existingQuestao.getAlternativas().clear();
            questaoDTO.getAlternativas().forEach(altDTO -> {
                Alternativa alternativa = new Alternativa();
                alternativa.setTexto(altDTO.getTexto());
                alternativa.setCorreta(altDTO.getCorreta());
                alternativa.setQuestao(existingQuestao);
                existingQuestao.getAlternativas().add(alternativa);
            });
        }
        
        Questao updatedQuestao = questaoRepository.save(existingQuestao);
        return toDTO(updatedQuestao);
    }

    @SuppressWarnings("null")
    public void delete(Long id) {
        if (!questaoRepository.existsById(id)) {
            throw new RuntimeException("Questão não encontrada");
        }
        questaoRepository.deleteById(id);
    }
    
    private QuestaoDTO toDTO(Questao questao) {
        QuestaoDTO dto = new QuestaoDTO();
        dto.setId(questao.getId());
        dto.setEnunciado(questao.getEnunciado());
        dto.setAno(questao.getAno());
        
        if (questao.getNivelDificuldade() != null) {
            dto.setNivelDificuldade(questao.getNivelDificuldade().toString());
        }
        
        if (questao.getDisciplina() != null) {
            dto.setDisciplinaNome(questao.getDisciplina().getNome());
        }
        
        if (questao.getConcurso() != null) {
            dto.setConcursoNome(questao.getConcurso().getNome());
        }
        
        if (questao.getAlternativas() != null) {
            List<AlternativaDTO> alternativasDTO = questao.getAlternativas().stream()
                    .map(this::toAlternativaDTO)
                    .collect(Collectors.toList());
            dto.setAlternativas(alternativasDTO);
        }
        
        return dto;
    }
    
    private AlternativaDTO toAlternativaDTO(Alternativa alternativa) {
        AlternativaDTO dto = new AlternativaDTO();
        dto.setId(alternativa.getId());
        dto.setTexto(alternativa.getTexto());
        dto.setCorreta(alternativa.getCorreta());
        return dto;
    }
    
    private Questao toEntity(QuestaoDTO dto) {
        Questao questao = new Questao();
        questao.setEnunciado(dto.getEnunciado());
        questao.setAno(dto.getAno());
        
        // Define o nível de dificuldade
        if (dto.getNivelDificuldade() != null) {
            try {
                questao.setNivelDificuldade(NivelDificuldade.valueOf(dto.getNivelDificuldade()));
            } catch (IllegalArgumentException e) {
                questao.setNivelDificuldade(NivelDificuldade.MEDIO);
            }
        }
        
        // Busca disciplina pelo nome (simplificado)
        if (dto.getDisciplinaNome() != null) {
            // Aqui você implementaria a busca da disciplina
            // Por enquanto, vamos deixar null
        }
        
        // Adiciona alternativas
        if (dto.getAlternativas() != null) {
            List<Alternativa> alternativas = dto.getAlternativas().stream()
                    .map(altDTO -> {
                        Alternativa alternativa = new Alternativa();
                        alternativa.setTexto(altDTO.getTexto());
                        alternativa.setCorreta(altDTO.getCorreta());
                        alternativa.setQuestao(questao);
                        return alternativa;
                    })
                    .collect(Collectors.toList());
            questao.setAlternativas(alternativas);
        }
        
        return questao;
    }
}