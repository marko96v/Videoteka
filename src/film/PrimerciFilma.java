package film;


public class PrimerciFilma {
	
	private String oznaka;
	private String medijum;
	private String imeFilma;
	private int brojPrimeraka;
	private boolean status;

	
	


	public PrimerciFilma(){
		this.oznaka = "";
		this.medijum = "";
		this.imeFilma = "";
		this.brojPrimeraka = 0;
		this.status = false;
	}
	
	public PrimerciFilma(String oznaka, String medijum,String imeFilma,  int brojPrimeraka, boolean status){
		this.oznaka = oznaka;
		this.medijum = medijum;
		this.imeFilma = imeFilma;
		this.brojPrimeraka = brojPrimeraka;
		this.status = status;
	}
	
	public PrimerciFilma(PrimerciFilma orginal){
		this.oznaka = orginal.oznaka;
		this.medijum = orginal.medijum;
		this.imeFilma = orginal.imeFilma;
		this.brojPrimeraka = orginal.brojPrimeraka;
		this.status = orginal.status;
	} 
 
	public String getOznaka() {
		return oznaka;
	}

	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}

	public String getMedijum() {
		return medijum;
	}

	public void setMedijum(String medijum) {
		this.medijum = medijum;
	}

	public int getBrojPrimeraka() {
		return brojPrimeraka;
	}

	public void setBrojPrimeraka(int brojPrimeraka) {
		this.brojPrimeraka = brojPrimeraka;
	}
	
	
	
	public String getImeFilma() {
		return imeFilma;
	}

	public void setImeFilma(String imeFilma) {
		this.imeFilma = imeFilma;
	}
	

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString(){
		return "PRIMERCI FILMA \n--Oznaka: " + oznaka +
				"\n--Medijum: " + medijum + 
				"\n--Broj primeraka: " + brojPrimeraka;
	}
	

}
