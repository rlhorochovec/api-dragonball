package br.rafaelhorochovec.dragonball.controller;

import br.rafaelhorochovec.dragonball.model.Character;
import br.rafaelhorochovec.dragonball.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class CharacterController {

	@Autowired
	CharacterService characterService;

	// POST
	@PostMapping("/characters")
	public Character createCharacter(@RequestBody Character character) {
		return characterService.createCharacter(character);
	}
	
	// GET
	@GetMapping("/characters")
	public List<Character> listCharacters() {
		return characterService.getCharacters();
	}

	// PUT
	@PutMapping("/characters/{id}")
	public Character updateCharacter(@PathVariable(value = "id") UUID id, @RequestBody Character characterDetails) {
		return characterService.updateCharacter(id, characterDetails);
	}

	// GET BY ID
	@GetMapping("/characters/{id}")
	public Character getCharacter(@PathVariable(value = "id") UUID id) {
		return characterService.getById(id);
	}

	// DELETE
	@DeleteMapping("/characters/{id}")
	public void deleteCharacter(@PathVariable(value = "id") UUID id) {
		characterService.deleteCharacter(id);
	}
}