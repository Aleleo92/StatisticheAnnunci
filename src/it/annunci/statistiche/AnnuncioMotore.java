package it.annunci.statistiche;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
 
@Entity
@Table(name="annuncio_motore")
public class AnnuncioMotore implements Serializable{
     
    private static final long serialVersionUID = -8767337896773261247L;
    
    
	private Long id;
	private int id_annunci;
    private int id_motore;
    private int candidature_tot;
    private int candidature_pos;
    private int candidature_neg;
 
    @Id
    @GeneratedValue
    @Column(name="id")
    public Long getId() {
        return id;
    }
    @Column(name="id_annunci")
    public int getId_annunci() {
		return id_annunci;
	}
    @Column(name="id_motore")
    public int getId_motore() {
		return id_motore;
	}
    @Column(name="candidature_tot")
    public int getCandidature_tot() {
		return candidature_tot;
	}
    @Column(name="candidature_pos")
    public int getCandidature_pos() {
		return candidature_pos;
	}
    @Column(name="candidature_neg")
    public int getCandidature_neg() {
		return candidature_neg;
	}
    
    public void setId(Long id) {
		this.id = id;
	}
	public void setId_annunci(int id_annunci) {
		this.id_annunci = id_annunci;
	}
	public void setId_motore(int id_motore) {
		this.id_motore = id_motore;
	}	
	public void setCandidature_tot(int candidature_tot) {
		this.candidature_tot = candidature_tot;
	}	
	public void setCandidature_pos(int candidature_pos) {
		this.candidature_pos = candidature_pos;
	}	
	public void setCandidature_neg(int candidature_neg) {
		this.candidature_neg = candidature_neg;
	}


}