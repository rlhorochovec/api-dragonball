package br.rafaelhorochovec.dragonball.service;

import br.rafaelhorochovec.dragonball.repository.CharacterRepository;
import br.rafaelhorochovec.dragonball.model.Character;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CharacterService {

    @Autowired
    CharacterRepository characterRepository;

    // CREATE 
    public Character createCharacter(Character character) {
        return characterRepository.save(character);
    }

    // READ
    public List<Character> getCharacters() {
        return characterRepository.findAll();
    }

    // UPDATE
    public Character updateCharacter(UUID id, Character characterDetails) {
        Character character = characterRepository.findById(id).get();
        character.setName(characterDetails.getName());
        character.setPlanet(characterDetails.getPlanet());
        return characterRepository.save(character);
    }

    // GET BY ID
    public Character getById(UUID id) {
        Character character = characterRepository.findById(id).get();
        return characterRepository.getOne(id);
    }

    // DELETE
    public void deleteCharacter(UUID id) {
        characterRepository.deleteById(id);
    }
}
