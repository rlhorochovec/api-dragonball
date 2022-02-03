package br.rafaelhorochovec.dragonball.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.rafaelhorochovec.dragonball.model.Personagem;
import br.rafaelhorochovec.dragonball.repository.PersonagemRepository;

@RestController
@RequestMapping("/api")
public class PersonagemController {

	@Autowired
	PersonagemRepository personagemRepository;

	@PostMapping("/personagens")
	public ResponseEntity<Personagem> create(@RequestBody Personagem personagem) {
		try {
			Personagem _personagem = personagemRepository
					.save(new Personagem(personagem.getNome(), personagem.getRaca(), personagem.getPlaneta()));
			return new ResponseEntity<>(_personagem, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/personagens")
	public ResponseEntity<List<Personagem>> read(@RequestParam(required = false) String nome) {
		try {
			List<Personagem> personagens = new ArrayList<Personagem>();
			if (nome == null) {
				personagemRepository.findAll().forEach(personagens::add);
			} else {
				personagemRepository.findByNome(nome).forEach(personagens::add);
			}
			if (personagens.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(personagens, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/personagens/{id}")
	public ResponseEntity<Personagem> getById(@PathVariable("id") UUID id) {
		Optional<Personagem> personagem = personagemRepository.findById(id);
		if (personagem.isPresent()) {
			return new ResponseEntity<>(personagem.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/personagens/{id}")
	public ResponseEntity<Personagem> update(@PathVariable("id") UUID id, @RequestBody Personagem personagem) {
		Optional<Personagem> objPersonagen = personagemRepository.findById(id);
		if (objPersonagen.isPresent()) {
			Personagem _personagem = objPersonagen.get();
			_personagem.setNome(personagem.getNome());
			_personagem.setRaca(personagem.getRaca());
			_personagem.setPlaneta(personagem.getPlaneta());
			return new ResponseEntity<>(personagemRepository.save(_personagem), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/personagens/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") UUID id) {
		try {
			personagemRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/personagens")
	public ResponseEntity<HttpStatus> deleteAll() {
		try {
			personagemRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}