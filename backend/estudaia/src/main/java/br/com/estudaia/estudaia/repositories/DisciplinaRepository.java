package br.com.estudaia.estudaia.repositories;

import br.com.estudaia.estudaia.models.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {}