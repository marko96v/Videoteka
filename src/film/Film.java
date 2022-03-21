package film;

public class Film {
	private String naslovNaSrpskom;
	private String orginalNaslov;
	private int godinaIzdavanja;
	private String imeIPrezimeRez;
	private String opis;
	private double trajanje;
	private boolean status;
	
	public Film(){
		this.naslovNaSrpskom = "";
		this.orginalNaslov = "";
		this.godinaIzdavanja = 0;
		this.imeIPrezimeRez = "";
		this.opis = "";
		this.trajanje = 0;
		this.status = false;
	}
	
	public Film(String naslovNaSrpskom, String orginalNaslov, int godinaIzdavanja, String imeIPrezimeRez, String opis,
			double trajanje, boolean status) {
		this.naslovNaSrpskom = naslovNaSrpskom;
		this.orginalNaslov = orginalNaslov;
		this.godinaIzdavanja = godinaIzdavanja;
		this.imeIPrezimeRez = imeIPrezimeRez;
		this.opis = opis;
		this.trajanje = trajanje;
		this.status = status;
	}
	
	public Film(Film orginal){
		this.naslovNaSrpskom = orginal.naslovNaSrpskom;
		this.orginalNaslov = orginal.orginalNaslov;
		this.godinaIzdavanja = orginal.godinaIzdavanja;
		this.imeIPrezimeRez = orginal.imeIPrezimeRez;
		this.opis = orginal.opis;
		this.trajanje = orginal.trajanje;
		this.status = orginal.status;
		
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getNaslovNaSrpskom() {
		return naslovNaSrpskom;
	}

	public void setNaslovNaSrpskom(String naslovNaSrpskom) {
		this.naslovNaSrpskom = naslovNaSrpskom;
	}

	public String getOrginalNaslov() {
		return orginalNaslov;
	}

	public void setOrginalNaslov(String orginalNaslov) {
		this.orginalNaslov = orginalNaslov;
	}

	public int getGodinaIzdavanja() {
		return godinaIzdavanja;
	}

	public void setGodinaIzdavanja(int godinaIzdavanja) {
		this.godinaIzdavanja = godinaIzdavanja;
	}

	public String getImeIPrezimeRez() {
		return imeIPrezimeRez;
	}

	public void setImeIPrezimeRez(String imeIPrezimeRez) {
		this.imeIPrezimeRez = imeIPrezimeRez;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public double getTrajanje() {
		return trajanje;
	}

	public void setTrajanje(double trajanje) {
		this.trajanje = trajanje;
	}
	@Override
	public String toString(){
		return "FILM \n--Ime na srpskom: " + naslovNaSrpskom + 
				"\n--Ime orginal: " + orginalNaslov + 
				"\n--Godina izdavanja: " + godinaIzdavanja + 
				"\n--Reziser: " + imeIPrezimeRez + 
				"\n--Opis: " + opis + 
				"\n--Trajanje: " + trajanje;
	} 
}
