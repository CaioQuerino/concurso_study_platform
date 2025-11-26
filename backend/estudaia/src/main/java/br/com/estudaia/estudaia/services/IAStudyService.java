package br.com.estudaia.estudaia.services;

import br.com.estudaia.estudaia.dto.*;

import br.com.estudaia.estudaia.models.*;

import br.com.estudaia.estudaia.repositories.*;

import com.theokanning.openai.completion.chat.*;
import com.theokanning.openai.service.OpenAiService;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class IAStudyService {

    private final QuestaoRepository questaoRepository;
    private final QuestaoService questaoService;
    private final OpenAiService openAiService;

    public ApiResponse<String> gerarExplicacaoQuestao(Long questaoId) {
        try {
            @SuppressWarnings("null")
            Optional<Questao> questaoOpt = questaoRepository.findById(questaoId);
            if (questaoOpt.isEmpty()) {
                return ApiResponse.error("Questão não encontrada");
            }

            Questao questao = questaoOpt.get();
            String explicacao = gerarExplicacaoComIA(questao);
            return ApiResponse.success(explicacao);

        } catch (Exception e) {
            return ApiResponse.error("Erro ao gerar explicação: " + e.getMessage());
        }
    }

    public ApiResponse<List<QuestaoDTO>> gerarQuestoesPersonalizadas(Long usuarioId) {
        try {
            // Por enquanto, retorna questões aleatórias
            // Futuramente: implementar lógica baseada no desempenho do usuário
            List<QuestaoDTO> todasQuestoes = questaoService.findAll();

            // Seleciona no máximo 10 questões
            if (todasQuestoes.size() > 10) {
                todasQuestoes = todasQuestoes.subList(0, Math.min(10, todasQuestoes.size()));
            }

            return ApiResponse.success(todasQuestoes);
        } catch (Exception e) {
            return ApiResponse.error("Erro ao gerar questões personalizadas: " + e.getMessage());
        }
    }

    public ApiResponse<PlanoEstudo> gerarPlanoEstudo(Long usuarioId, Long concursoId) {
        try {
            PlanoEstudo plano = simularPlanoEstudoIA(usuarioId, concursoId);
            return ApiResponse.success(plano);
        } catch (Exception e) {
            return ApiResponse.error("Erro ao gerar plano de estudo: " + e.getMessage());
        }
    }

    private String gerarExplicacaoComIA(Questao questao) {
        try {
            String prompt = String.format(
                "Explique a seguinte questão de concurso de forma didática:\n\n" +
                "Disciplina: %s\n" +
                "Ano: %s\n" +
                "Nível: %s\n" +
                "Enunciado: %s\n\n" +
                "Forneça uma explicação clara, destacando os conceitos importantes e por que a alternativa correta é a mais adequada.",
                questao.getDisciplina() != null ? questao.getDisciplina().getNome() : "Não especificada",
                questao.getAno(),
                questao.getNivelDificuldade(),
                questao.getEnunciado()
            );

            ChatMessage message = new ChatMessage(ChatMessageRole.USER.value(), prompt);
            
            ChatCompletionRequest request = ChatCompletionRequest.builder()
                .model("gpt-3.5-turbo")
                .messages(Arrays.asList(message))
                .maxTokens(500)
                .temperature(0.7)
                .build();

            return openAiService.createChatCompletion(request)
                .getChoices().get(0).getMessage().getContent();

        } catch (Exception e) {
            // Fallback para caso a API falhe
            return String.format(
                "Questão de %s (%s) - Nível: %s\n\nExplicação: Esta questão aborda conceitos importantes da disciplina. " +
                "Recomendo revisar os tópicos relacionados para melhor compreensão.",
                questao.getDisciplina() != null ? questao.getDisciplina().getNome() : "Não especificada",
                questao.getAno(),
                questao.getNivelDificuldade()
            );
        }
    }

    private PlanoEstudo simularPlanoEstudoIA(Long usuarioId, Long concursoId) {
        PlanoEstudo plano = new PlanoEstudo();
        plano.setId(new Random().nextLong());
        plano.setNome("Plano de Estudos Personalizado - Concurso " + concursoId);
        plano.setDescricao("Plano gerado automaticamente com base no seu perfil e no edital do concurso.");
        plano.setDuracaoDias(90);

        return plano;
    }
}