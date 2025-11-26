package br.com.estudaia.estudaia.config;

import br.com.estudaia.estudaia.enums.StatusConcurso;
import br.com.estudaia.estudaia.models.*;

import br.com.estudaia.estudaia.repositories.*;

import lombok.RequiredArgsConstructor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final DisciplinaRepository disciplinaRepository;
    private final ConcursoRepository concursoRepository;

    @Override
    public void run(String... args) throws Exception {
        carregarDadosIniciais();
    }

    private void carregarDadosIniciais() {
        // Verifica se já existem dados
        if (disciplinaRepository.count() == 0) {
            carregarDisciplinas();
        }
        if (concursoRepository.count() == 0) {
            carregarConcursos();
        }
    }

    @SuppressWarnings("null")
    private void carregarDisciplinas() {
        Disciplina portugues = new Disciplina(null, "Língua Portuguesa", "Gramática, interpretação de texto e redação", null, null);
        Disciplina matematica = new Disciplina(null, "Matemática", "Matemática básica e raciocínio lógico", null, null);
        Disciplina direito = new Disciplina(null, "Direito Constitucional", "Direito constitucional brasileiro", null, null);
        Disciplina informatica = new Disciplina(null, "Informática", "Conceitos básicos de informática", null, null);

        disciplinaRepository.saveAll(Arrays.asList(portugues, matematica, direito, informatica));
    }

    @SuppressWarnings("null")
    private void carregarConcursos() {
        Concurso trt = new Concurso(null, "TRT - Tribunal Regional do Trabalho", "TRT", "FCC", 
                                   java.time.LocalDate.now().plusMonths(3), 
                                   "http://example.com/edital", StatusConcurso.ABERTO, null);
        
        Concurso policiaFederal = new Concurso(null, "Polícia Federal", "PF", "CESPE", 
                                             java.time.LocalDate.now().plusMonths(4), 
                                             "http://example.com/edital", StatusConcurso.ABERTO, null);

        concursoRepository.saveAll(Arrays.asList(trt, policiaFederal));
    }
}