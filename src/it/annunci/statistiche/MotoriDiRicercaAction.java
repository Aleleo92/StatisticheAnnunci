package it.annunci.statistiche;

import java.util.List;

import it.annunci.statistiche.Manager;
import it.annunci.statistiche.MotoriDiRicerca;
 
import com.opensymphony.xwork2.ActionSupport;
 
public class MotoriDiRicercaAction extends ActionSupport{

	private MotoriDiRicerca motoreDiRicerca;
	private List<MotoriDiRicerca> motoriList;
	private int motore; 
	private Manager manager;
	private String yourAnswer;
	
	public MotoriDiRicercaAction() {
		manager = new Manager();
	}
    
//    public String showMotori() {
//    	String answer = getYourAnswer();
//        this.motoriList = manager.motoriList(answer);
//        System.out.println("execute called");
//        return SUCCESS;
//    }
    
    public String ricerca() {
    	int motore = getMotore();
    	manager.query(motore+"");
    	return SUCCESS;
    }

	public MotoriDiRicerca getMotoreDiRicerca() {
		return motoreDiRicerca;
	}
	
	public List<MotoriDiRicerca> getMotoriList() {
		return motoriList;
	}
	
	public Manager getManager() {
		return manager;
	}
	
	public int getMotore() {
		return motore;
	}

	public void setMotoreDiRicerca(MotoriDiRicerca motoreDiRicerca) {
		this.motoreDiRicerca = motoreDiRicerca;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public void setMotoriList(List<MotoriDiRicerca> motoriList) {
		this.motoriList = motoriList;
	}

	public void setMotore(int motore) {
		this.motore = motore;
	}

	public String getYourAnswer() {
		return yourAnswer;
	}

	public void setYourAnswer(String yourAnswer) {
		this.yourAnswer = yourAnswer;
	}


	
}
