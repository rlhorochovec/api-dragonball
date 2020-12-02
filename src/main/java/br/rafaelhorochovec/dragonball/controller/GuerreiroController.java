package br.rafaelhorochovec.dragonball.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

import br.rafaelhorochovec.dragonball.model.Guerreiro;
import br.rafaelhorochovec.dragonball.repository.GuerreiroRepository;

@RestController
@RequestMapping("/api")
public class GuerreiroController {

	@Autowired
	GuerreiroRepository guerreiroRepository;

	@PostMapping("/guerreiros")
	public ResponseEntity<Guerreiro> criarGuerreiro(@RequestBody Guerreiro guerreiro) {
		try {
			Guerreiro _guerreiro = guerreiroRepository
					.save(new Guerreiro(guerreiro.getNome(), guerreiro.getRaca(), guerreiro.getPlaneta()));
			return new ResponseEntity<>(_guerreiro, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/guerreiros")
	public ResponseEntity<List<Guerreiro>> listarGuerreiros(@RequestParam(required = false) String nome) {
		try {
			List<Guerreiro> guerreiros = new ArrayList<Guerreiro>();
			if (nome == null) {
				guerreiroRepository.findAll().forEach(guerreiros::add);
			} else {
				guerreiroRepository.findByNome(nome).forEach(guerreiros::add);
			}
			if (guerreiros.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(guerreiros, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/guerreiros/{id}")
	public ResponseEntity<Guerreiro> selecionarGuerreiro(@PathVariable("id") long id) {
		Optional<Guerreiro> guerreiro = guerreiroRepository.findById(id);
		if (guerreiro.isPresent()) {
			return new ResponseEntity<>(guerreiro.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/guerreiros/{id}")
	public ResponseEntity<Guerreiro> editarGuerreiro(@PathVariable("id") long id, @RequestBody Guerreiro guerreiro) {
		Optional<Guerreiro> objGuerreiro = guerreiroRepository.findById(id);
		if (objGuerreiro.isPresent()) {
			Guerreiro _guerreiro = objGuerreiro.get();
			_guerreiro.setNome(guerreiro.getNome());
			_guerreiro.setRaca(guerreiro.getRaca());
			_guerreiro.setPlaneta(guerreiro.getPlaneta());
			return new ResponseEntity<>(guerreiroRepository.save(_guerreiro), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/guerreiros/{id}")
	public ResponseEntity<HttpStatus> removerGuerreiro(@PathVariable("id") long id) {
		try {
			guerreiroRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/guerreiros")
	public ResponseEntity<HttpStatus> limparGuerreiros() {
		try {
			guerreiroRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}