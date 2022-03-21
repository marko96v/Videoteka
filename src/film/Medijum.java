package film;

public class Medijum {
	
	private String medijum;
	private int cena;
	
	public Medijum(String medijum, int cena){
		this.medijum = medijum;
		this.cena = cena;
	}

	public String getMedijum() {
		return medijum;
	}

	public void setMedijum(String medijum) {
		this.medijum = medijum;
	}

	public int getCena() {
		return cena;
	}

	public void setCena(int cena) {
		this.cena = cena;
	}
	
}
