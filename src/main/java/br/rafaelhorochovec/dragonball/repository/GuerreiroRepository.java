package br.rafaelhorochovec.dragonball.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.rafaelhorochovec.dragonball.model.Guerreiro;

public interface GuerreiroRepository extends JpaRepository<Guerreiro, UUID> {

	List<Guerreiro> findByNome(String nome);
}
