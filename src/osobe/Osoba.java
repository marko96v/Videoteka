package osobe;

public class Osoba {
	
	private String ime;
	private String prezime;
	private String JMBG;
	private String adresa;
	private String pol;
	
	public Osoba(){
		this.ime = "";
		this.prezime = "";
		this.JMBG = "";
		this.adresa = "";
		this.pol = "";
	}
	
	public Osoba(String ime, String prezime, String JMBG, String adresa, String pol){
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.JMBG = JMBG;
		this.adresa = adresa;
		this.pol = pol;
	}
	
	public Osoba(Osoba orginal){
		this.ime = orginal.ime;
		this.prezime = orginal.prezime;
		this.JMBG = orginal.JMBG;
		this.adresa = orginal.adresa;
		this.JMBG = orginal.JMBG;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getJMBG() {
		return JMBG;
	}

	public void setJMBG(String jMBG) {
		JMBG = jMBG;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getPol() {
		return pol;
	}

	public void setPol(String pol) {
		this.pol = pol;
	}
	
	@Override
	public String toString(){
		return "\nIme: " + ime +
				"\nPrezime: " + prezime +
				"\nJMBG: " + JMBG + 
				"\nAdresa: " + adresa +
				"\nPol: " + pol;
	}
	

}
