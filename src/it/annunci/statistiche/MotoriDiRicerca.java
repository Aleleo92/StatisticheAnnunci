package it.annunci.statistiche;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="motori_di_ricerca")
public class MotoriDiRicerca {
	private int id;
	private String nome;
	private byte attivo;
	
	@Id
	@Column(name="id")
	public int getId() {
		return id;
	}
	@Column(name="nome")
	public String getNome() {
		return nome;
	}
	@Column(name="attivo")
	public byte getAttivo() {
		return attivo;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setAttivo(byte attivo) {
		this.attivo = attivo;
	}
	
//	SELECT nome FROM MotoriDiRicerca
}
