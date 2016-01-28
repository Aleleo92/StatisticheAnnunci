package it.annunci.statistiche;

import java.sql.Date;
import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="annunci")
public class Annunci {
	private int id_annunci;
	private Blob testo;
	private Date data;
	
	@Id
	@Column(name="id_annunci")
	public int getId_annunci() {
		return id_annunci;
	}
	@Column(name="testo")
	public Blob getTesto() {
		return testo;
	}
	@Column(name="data")
	public Date getData() {
		return data;
	}
	
	public void setId_annunci(int id_annunci) {
		this.id_annunci = id_annunci;
	}	
	public void setTesto(Blob testo) {
		this.testo = testo;
	}
	public void setData(Date data) {
		this.data = data;
	}
}
