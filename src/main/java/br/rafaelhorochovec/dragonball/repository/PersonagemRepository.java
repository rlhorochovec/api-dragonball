package br.rafaelhorochovec.dragonball.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.rafaelhorochovec.dragonball.model.Personagem;

public interface PersonagemRepository extends JpaRepository<Personagem, UUID> {

	List<Personagem> findByNome(String nome);
}
