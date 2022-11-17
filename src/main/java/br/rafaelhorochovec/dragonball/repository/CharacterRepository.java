package br.rafaelhorochovec.dragonball.repository;

import br.rafaelhorochovec.dragonball.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CharacterRepository extends JpaRepository<Character, UUID> {

}
