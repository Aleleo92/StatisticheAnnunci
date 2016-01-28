package it.annunci.statistiche;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.opensymphony.xwork2.ActionSupport;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

public class Statistiche extends ActionSupport{

	private Manager manager;
	private String motore;
	private String nomeMotore;
	private List<Object> resultQuery1;
	private List<AnnuncioMotore> annuncioMotoreList;
	private float media;
	private String from = null;
	private String to = null;
	private List<MotoriDiRicerca> motoriList;
	private String arrayForBarChart;


	public Statistiche(){
		manager = new Manager();
	}

	public float calcoloMedia(){
		Iterator<AnnuncioMotore> itr = annuncioMotoreList.iterator();
		int i = 0;
		int candidaturePos = 0;
		int candidature = 0;
		float acc[] = new float[annuncioMotoreList.size()];
		while(itr.hasNext()) {
			Object element = (Object) itr.next();
			candidature = ((AnnuncioMotore) element).getCandidature_tot();
			candidaturePos = ((AnnuncioMotore) element).getCandidature_pos();
			acc[i] = ((float) candidaturePos) / candidature;
			System.out.print("\nAnnuncio " + i + " ->\n\tcandidature: " + candidature + "\n\tcandidature positive: " + candidaturePos);
			i++;
		}
		media = mediaArray(acc);
		System.out.println("\n\t" + media);
		return media;
	}
	
	public void prova() throws JSONException{
		Iterator<Object> itr = resultQuery1.iterator();
		JSONArray a = new JSONArray();
		List<AnnuncioMotore> listAcc = new ArrayList<>();
		while(itr.hasNext()){
			JSONObject JSONObj = new JSONObject();
			Object[] obj = (Object[]) itr.next();
			AnnuncioMotore annuncioMotore = (AnnuncioMotore)obj[0];
			listAcc.add(annuncioMotore);
			String nome = String.valueOf(obj[1]);
			JSONObj.put("motori", nome);
			JSONObj.put("positive", annuncioMotore.getCandidature_pos()+"");
			JSONObj.put("negative", annuncioMotore.getCandidature_neg()+"");
			a.put(JSONObj);
		}
		this.annuncioMotoreList = listAcc;
		setArrayForBarChart(a.toString().replace("\"", "'"));
		calcoloMedia();
		System.out.println("lista: " + annuncioMotoreList);
	}

	public JSONArray transformListToJson(List<AnnuncioMotore> ls) throws JSONException{
		JSONArray a = new JSONArray();
		Iterator<AnnuncioMotore> itr = annuncioMotoreList.iterator();
		while(itr.hasNext()) {
			AnnuncioMotore el = (AnnuncioMotore) itr.next();
			JSONObject obj = new JSONObject();
			obj.put("motori", "prova");
			obj.put("positive", el.getCandidature_pos()+"");
			obj.put("negative", el.getCandidature_neg()+"");
			a.put(obj);
		}
		setArrayForBarChart(a.toString().replace("\"", "'"));
		System.out.println("prove" + arrayForBarChart);
		return a;
	}
	public float mediaArray( float a[]){
		float acc = 0;
		for( float x : a ){
			acc += x;
		}
		acc = (float)Math.round((acc / a.length) * 100f) / 100f;
		System.out.println(acc);
		return (acc);
	}

	public String dateSelezionate() throws JSONException{
		String from = getFrom();
		String to = getTo();
		String id_motore = parseParenthesisSquareToCurve(getMotoriList().toString());
		System.out.println("ID_MOTORE" + id_motore);
		if(!(from.isEmpty() || to.isEmpty())){
			this.resultQuery1 = manager.query1(toDate(from), toDate(to), id_motore);
			//System.out.println(transformListToJson(annuncioMotoreList));
		}else{
			this.annuncioMotoreList = manager.query(id_motore);
		}
//		calcoloMedia();
		prova();
		return SUCCESS;
	}

	public Date toDate(String stringDate){
		System.out.println(stringDate);
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {

			date = formatter.parse(stringDate);
			System.out.println(date);
			System.out.println(formatter.format(date));

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public String parseParenthesisSquareToCurve(String str){
		str = str.replaceAll("\\[", "(").replaceAll("\\]",")");
		return str;
	}

	public List<AnnuncioMotore> getAnnuncioMotoreList() {
		return annuncioMotoreList;
	}
	public Manager getManager() {
		return manager;
	}
	public String getMotore() {
		return motore;
	}
	public void setManager(Manager manager) {
		this.manager = manager;
	}
	public void setMotore(int motore) {
		this.motore = "("+ motore + ")";
	}
	public void setAnnuncioMotoreList(List<AnnuncioMotore> annuncioMotoreList) {
		this.annuncioMotoreList = annuncioMotoreList;
	}
	public float getMedia() {
		return media;
	}
	public void setMedia(float media) {
		this.media = media;
	}
	public String getFrom() {
		return from;
	}
	public String getTo() {
		return to;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public void setTo(String to) {
		this.to = to;
	}

	public List<MotoriDiRicerca> getMotoriList() {
		return motoriList;
	}

	public void setMotoriList(List<MotoriDiRicerca> motoriList) {
		this.motoriList = motoriList;
	}

	public String getArrayForBarChart() {
		return arrayForBarChart;
	}

	public void setArrayForBarChart(String string) {
		this.arrayForBarChart = string;
	}

	public String getNomeMotore() {
		return nomeMotore;
	}

	public List<Object> getResultQuery1() {
		return resultQuery1;
	}

	public void setNomeMotore(String nomeMotore) {
		this.nomeMotore = nomeMotore;
	}

	public void setResultQuery1(List<Object> resultQuery1) {
		this.resultQuery1 = resultQuery1;
	}

}
