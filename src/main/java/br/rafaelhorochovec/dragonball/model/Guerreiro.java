package br.rafaelhorochovec.dragonball.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Guerreiro extends Auditoria {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nome;
	private String raca;
	private String planeta;
	
	public Guerreiro() {
		
	}

	public Guerreiro(String nome, String raca, String planeta) {
		this.nome = nome;
		this.raca = raca;
		this.planeta = planeta;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}

	public String getPlaneta() {
		return planeta;
	}

	public void setPlaneta(String planeta) {
		this.planeta = planeta;
	}
	
	@Override
	public String toString() {
		return "Guerreiro [id=" + id + ", nome=" + nome + ", raca=" + raca + ", planeta=" + planeta + "]";
	}
}
