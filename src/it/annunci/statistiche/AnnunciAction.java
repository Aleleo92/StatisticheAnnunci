package it.annunci.statistiche;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

public class AnnunciAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Annunci annunci;
	private List<Annunci> annunciList;
	
	private Manager manager;
	
	public AnnunciAction() {
		manager = new Manager();
	}
	
	public String showAnnunci() {
        this.annunciList = manager.annunciList();
        System.out.println("execute called");
        return SUCCESS;
    }

	public Annunci getAnnunci() {
		return annunci;
	}

	public List<Annunci> getAnnunciList() {
		return annunciList;
	}

	public Manager getManager() {
		return manager;
	}

	public void setAnnunci(Annunci annunci) {
		this.annunci = annunci;
	}

	public void setAnnunciList(List<Annunci> annunciList) {
		this.annunciList = annunciList;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}
	
	
	
	
}
