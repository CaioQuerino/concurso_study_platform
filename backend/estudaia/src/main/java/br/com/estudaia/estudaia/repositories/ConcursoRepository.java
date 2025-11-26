package br.com.estudaia.estudaia.repositories;

import br.com.estudaia.estudaia.models.Concurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConcursoRepository extends JpaRepository<Concurso, Long> {}