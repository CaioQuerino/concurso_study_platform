package br.com.estudaia.estudaia.repositories;

import br.com.estudaia.estudaia.models.Alternativa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlternativaRepository extends JpaRepository<Alternativa, Long> {}