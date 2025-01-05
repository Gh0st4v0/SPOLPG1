package io.github.Gh0st4v0.ProjetoLPG1Teste.repository;

import io.github.Gh0st4v0.ProjetoLPG1Teste.model.Bilhete;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BilheteRepository extends JpaRepository <Bilhete, String> {
}
