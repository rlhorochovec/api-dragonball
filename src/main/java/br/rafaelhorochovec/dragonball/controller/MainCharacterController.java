package br.rafaelhorochovec.dragonball.controller;

import br.rafaelhorochovec.dragonball.model.MainCharacter;
import br.rafaelhorochovec.dragonball.repository.MainCharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class MainCharacterController {

	@Autowired
	MainCharacterRepository characterRepository;

	@PostMapping("/characters")
	public ResponseEntity<MainCharacter> create(@RequestBody MainCharacter character) {
		try {
			MainCharacter _character = characterRepository
					.save(new MainCharacter(character.getName(), character.getPlanet()));
			return new ResponseEntity<>(_character, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/characters")
	public ResponseEntity<List<MainCharacter>> read() {
		try {
			return new ResponseEntity<>(characterRepository.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/characters/{id}")
	public ResponseEntity<MainCharacter> getById(@PathVariable("id") UUID id) {
		Optional<MainCharacter> character = characterRepository.findById(id);
		if (character.isPresent()) {
			return new ResponseEntity<>(character.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/characters/{id}")
	public ResponseEntity<MainCharacter> update(@PathVariable("id") UUID id, @RequestBody MainCharacter character) {
		Optional<MainCharacter> objCharacter = characterRepository.findById(id);
		if (objCharacter.isPresent()) {
			MainCharacter _character = objCharacter.get();
			_character.setName(character.getName());
			_character.setPlanet(character.getPlanet());
			return new ResponseEntity<>(characterRepository.save(_character), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/characters/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") UUID id) {
		try {
			characterRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/characters")
	public ResponseEntity<HttpStatus> deleteAll() {
		try {
			characterRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}