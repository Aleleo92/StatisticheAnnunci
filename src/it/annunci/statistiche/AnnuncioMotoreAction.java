package it.annunci.statistiche;
 
import java.util.List;
 
import it.annunci.statistiche.Manager;
import it.annunci.statistiche.AnnuncioMotore;
 
import com.opensymphony.xwork2.ActionSupport;
 
 
public class AnnuncioMotoreAction extends ActionSupport {
 
    private static final long serialVersionUID = 9149826260758390091L;
    private AnnuncioMotore annunciomotore;
    private List<AnnuncioMotore> list;
    private Long id;
 
    private Manager manager;
 
    public AnnuncioMotoreAction() {
        manager = new Manager();
    }
 
    public String execute() {
        this.list = manager.list();
        System.out.println("execute called");
        return SUCCESS;
    }
 
    public String add() {
        System.out.println(getAnnuncioMotore());
        try {
            manager.add(getAnnuncioMotore());
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.list = manager.list();
        return SUCCESS;
    }
 
    public String delete() {
        manager.delete(getId());
        return SUCCESS;
    }
 
    public AnnuncioMotore getAnnuncioMotore() {
        return annunciomotore;
    }
 
    public List<AnnuncioMotore> getList() {
        return list;
    }
 
    public void setAnnuncioMotore(AnnuncioMotore annunciomotore) {
        this.annunciomotore = annunciomotore;
    }
 
    public void setList(List<AnnuncioMotore> list) {
        this.list = list;
    }
 
    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
}