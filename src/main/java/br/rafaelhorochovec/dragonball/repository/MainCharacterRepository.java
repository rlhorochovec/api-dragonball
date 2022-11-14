package br.rafaelhorochovec.dragonball.repository;

import br.rafaelhorochovec.dragonball.model.MainCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MainCharacterRepository extends JpaRepository<MainCharacter, UUID> {

}
