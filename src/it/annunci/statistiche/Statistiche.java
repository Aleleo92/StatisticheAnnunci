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
	private List motoriList;
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
		List<String> nomi = new ArrayList<>();
		int pos = 0;
		int neg = 0;
		while(itr.hasNext()){
			JSONObject JSONObj = new JSONObject();
			Object[] obj = (Object[]) itr.next();
			AnnuncioMotore annuncioMotore = (AnnuncioMotore)obj[0];
			listAcc.add(annuncioMotore);
			String nome = String.valueOf(obj[1]);
			JSONObj.put("motori", nome);
			JSONObj.put("positive", annuncioMotore.getCandidature_pos());
			JSONObj.put("negative", annuncioMotore.getCandidature_neg());
			//			}
			a.put(JSONObj);
		}
		a = cumulate("motori", a);
		this.annuncioMotoreList = listAcc;
		setArrayForBarChart(a.toString().replace("\"", "'"));
		calcoloMedia();

	}

	public JSONArray cumulate(String key, JSONArray jsa) throws JSONException{
		int l = jsa.length();
		int pos = 0;
		int neg = 0;
		List<String> nomi = new ArrayList<>();
		JSONArray JSONArray = new JSONArray();
		String nome = "";
		for(int i = 0; i < l; i++){
			nome = jsa.getJSONObject(i).getString(key);
			if(!nomi.contains(nome)){
				nomi.add(nome);
			}
		}
		Iterator itr = nomi.iterator();
		while(itr.hasNext()){
			String str = (String) itr.next();
			for(int i = 0; i<l; i++){
				nome = jsa.getJSONObject(i).getString(key);
				if(str.equals(nome)){
					pos += jsa.getJSONObject(i).getInt("positive");
					neg += jsa.getJSONObject(i).getInt("negative");		
				}
			}
			JSONObject JSONObj = new JSONObject();
			JSONObj.put("motori", str);
			JSONObj.put("positive", pos);
			JSONObj.put("negative", neg );
			pos = 0;
			neg = 0;
			nome = "";
			JSONArray.put(JSONObj);
		}
		return JSONArray;
	}


	public float mediaArray( float a[]){
		float acc = 0;
		for( float x : a ){
			acc += x;
		}
		acc = (float)Math.round((acc / a.length) * 100f) / 100f;
		return (acc);
	}

	public String dateSelezionate(){
		String from = getFrom();
		String to = getTo();
		String id_motore = parseParenthesisSquareToCurve(getMotoriList().toString());
		if(!(from.isEmpty() || to.isEmpty())){
			this.resultQuery1 = manager.query1(toDate(from), toDate(to), id_motore);
		}else{
			this.annuncioMotoreList = manager.query(id_motore);
		}
		try {
			prova();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public Date toDate(String stringDate){
		System.out.println(stringDate);
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {

			date = formatter.parse(stringDate);

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

	public List getMotoriList() {
		return motoriList;
	}

	public void setMotoriList(List motoriList) {
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
