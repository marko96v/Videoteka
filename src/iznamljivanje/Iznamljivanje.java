package iznamljivanje;

import java.util.ArrayList;

import film.PrimerciFilma;

public class Iznamljivanje {
	
	private String sifra;
	private String zaposleniKorisnickoIme;
	private String clanImeIPrezime;
	private String datumIzdavanja;
	private String datumVracanja;
	private boolean status;
	private ArrayList<PrimerciFilma> filmovi;
	
	public Iznamljivanje(String sifra, String zaposleniKorisnickoIme, String clanImeIPrezime, String datumIzdavanja, String datumVracanja,boolean status,  ArrayList<PrimerciFilma> filmovi){
		
		this.sifra = sifra;
		this.zaposleniKorisnickoIme = zaposleniKorisnickoIme;
		this.clanImeIPrezime = clanImeIPrezime;
		this.datumIzdavanja = datumIzdavanja;
		this.datumVracanja = datumVracanja;
		this.status = status;
		this.filmovi = filmovi;
		
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	public String getZaposleniKorisnickoIme() {
		return zaposleniKorisnickoIme;
	}

	public void setZaposleniKorisnickoIme(String zaposleniKorisnickoIme) {
		this.zaposleniKorisnickoIme = zaposleniKorisnickoIme;
	}

	public String getClanImeIPrezime() {
		return clanImeIPrezime;
	}

	public void setClanImeIPrezime(String clanImeIPrezime) {
		this.clanImeIPrezime = clanImeIPrezime;
	}

	public String getDatumIzdavanja() {
		return datumIzdavanja;
	}

	public void setDatumIzdavanja(String datumIzdavanja) {
		this.datumIzdavanja = datumIzdavanja;
	}

	public String getDatumVracanja() {
		return datumVracanja;
	}

	public void setDatumVracanja(String datumVracanja) {
		this.datumVracanja = datumVracanja;
	}

	public ArrayList<PrimerciFilma> getFilmovi() {
		return filmovi;
	}

	public void setFilmovi(ArrayList<PrimerciFilma> filmovi) {
		this.filmovi = filmovi;
	}
	
	

}
