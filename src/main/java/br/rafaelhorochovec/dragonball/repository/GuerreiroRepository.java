package br.rafaelhorochovec.dragonball.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.rafaelhorochovec.dragonball.model.Guerreiro;

public interface GuerreiroRepository extends JpaRepository<Guerreiro, Long> {

	List<Guerreiro> findByNome(String nome);
}
