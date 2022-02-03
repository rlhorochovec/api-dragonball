package br.rafaelhorochovec.dragonball.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
public class Personagem extends Auditoria {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
	@Column(length = 36, nullable = false, updatable = false)
	@Type(type = "pg-uuid")
	private UUID id;
	private String nome;
	private String raca;
	private String planeta;
	
	public Personagem() {
		
	}

	public Personagem(String nome, String raca, String planeta) {
		this.nome = nome;
		this.raca = raca;
		this.planeta = planeta;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
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
