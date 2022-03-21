package osobe;

public class Zaposlen extends Osoba {
	
	private double plata;
	private String korisnickoIme;
	private String sifra;
	private boolean status;
	
	public Zaposlen(){
		super();
		this.plata = 0;
		this.korisnickoIme = "";
		this.sifra = "";
		this.status = true;
	}
	
	public Zaposlen(String ime, String prezime, String JMBG, String adresa, String pol, double plata,
			String korisnickoIme, String sifra) {
		super(ime, prezime, JMBG, adresa, pol);
		this.plata = plata;
		this.korisnickoIme = korisnickoIme;
		this.sifra = sifra;
		this.status = true;
	}
	
	public Zaposlen(Zaposlen orginal){
		super(orginal);
		this.plata = orginal.plata;
		this.korisnickoIme = orginal.korisnickoIme;
		this.sifra = orginal.sifra;
		this.status = true;
	}

	public double getPlata() {
		return plata;
	}

	public void setPlata(double plata) {
		this.plata = plata;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}
	
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String toString(){
		return "ZAPOSLEN " + super.toString() +
				"\nPlata: " + plata +
				"\nKorisnicko ime: " + korisnickoIme + 
				"\nSifra: " + sifra;
	}

}
