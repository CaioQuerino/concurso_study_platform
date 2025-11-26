package br.com.estudaia.estudaia.repositories;

import br.com.estudaia.estudaia.enums.NivelDificuldade;
import br.com.estudaia.estudaia.models.Questao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface QuestaoRepository extends JpaRepository<Questao, Long> {
    List<Questao> findByDisciplinaId(Long disciplinaId);
    List<Questao> findByConcursoId(Long concursoId);
    List<Questao> findByNivelDificuldade(NivelDificuldade nivelDificuldade);
    
    @Query("SELECT q FROM Questao q WHERE q.disciplina.id = :disciplinaId AND q.nivelDificuldade = :nivel")
    List<Questao> findByDisciplinaAndDificuldade(@Param("disciplinaId") Long disciplinaId, 
                                                @Param("nivel") NivelDificuldade nivel);
}