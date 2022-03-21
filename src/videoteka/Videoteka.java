package videoteka;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import film.Film;
import film.Medijum;
import film.PrimerciFilma;
import film.Zanr;
import iznamljivanje.Iznamljivanje;
import osobe.Clan;
import osobe.Zaposlen;

public class Videoteka {
	
	private String pib;
	private String naziv;
	private String adresa;
	private ArrayList<Clan> clanovi;
	private ArrayList<Zaposlen> zaposleni;
	private ArrayList<Film> filmovi;
	private ArrayList<Zanr> zanrovi;
	private ArrayList<PrimerciFilma> primerci;
	private ArrayList<Medijum> medijumi;
	private ArrayList<Iznamljivanje> iznamljivanje;
	
	
	public Videoteka(){
		this.pib = "";
		this.naziv = "";
		this.adresa = "";
		this.clanovi = new ArrayList<Clan>();
		this.zaposleni = new ArrayList<Zaposlen>();
		this.filmovi = new ArrayList<Film>();
		this.zanrovi = new ArrayList<Zanr>();
		this.primerci = new ArrayList<PrimerciFilma>();
		this.medijumi = new ArrayList<Medijum>();
		this.iznamljivanje = new ArrayList<Iznamljivanje>();
	}
	
	public Videoteka(String pib, String naziv, String adresa){
		this.pib = pib;
		this.naziv = naziv;
		this.adresa = adresa;
		this.clanovi = new ArrayList<Clan>();
		this.zaposleni = new ArrayList<Zaposlen>();
		this.filmovi = new ArrayList<Film>();
		this.zanrovi = new ArrayList<Zanr>();
		this.primerci = new ArrayList<PrimerciFilma>();
		this.medijumi = new ArrayList<Medijum>();
		this.iznamljivanje = new ArrayList<Iznamljivanje>();

	}
	
	public Videoteka(Videoteka orginal){
		this.pib = orginal.pib;
		this.naziv = orginal.naziv;
		this.adresa = orginal.adresa;
	}

	public String getPib() {
		return pib;
	}

	public void setPib(String pib) {
		this.pib = pib;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public ArrayList<Zaposlen> getZaposleni(){
		return zaposleni;
	}
	public ArrayList<Zaposlen> zaposlenZaPrikaz(){
		ArrayList<Zaposlen> zaposlenTrue = new ArrayList<>();
		for (Zaposlen zaposlen : zaposleni){
			if(zaposlen.isStatus() == true){
				zaposlenTrue.add(zaposlen);
			}
		}
		return zaposlenTrue;
	}
	
	public ArrayList<Clan> getClanovi(){
		return clanovi;
	}
	public void ucitajClanove(){
		try{
			File clanFajl = new File("src/fajlovi/clan.txt");
			BufferedReader a = new BufferedReader(new FileReader(clanFajl));
			String line = null;
			while((line = a.readLine()) != null){
				String[] split = line.split("\\|");
				String ime = split[0];
				String prezime = split[1];
				String JMBG = split[2];
				String adresa = split[3];
				String pol = split[4];
				int brojClanskeKarte = Integer.parseInt(split[5]);
				boolean aktivan = Boolean.parseBoolean(split[6]);
				boolean status = Boolean.parseBoolean(split[7]);
				Clan clan = new Clan(ime, prezime, JMBG, adresa, pol, brojClanskeKarte, aktivan, status);
				clanovi.add(clan);
			}
			a.close();
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void ucitajZaposlene(){
		try{
			File zaposlenFajl = new File("src/fajlovi/zaposlen.txt");
			BufferedReader a = new BufferedReader(new FileReader(zaposlenFajl));
			String line = null;
			while((line = a.readLine()) != null){
				String[] split = line.split("\\|");
				String ime = split[0];
				String prezime = split[1];
				String JMBG = split[2];
				String adresa = split[3];
				String pol = split[4];
				double plata = Double.parseDouble(split[5]);
				String korisnickoIme = split[6];
				String sifra = split[7]; 
				boolean status = Boolean.parseBoolean(split[8]);
				Zaposlen zaposlen = new Zaposlen(ime, prezime, JMBG, adresa, pol, plata, korisnickoIme, sifra);
				zaposlen.setStatus(status);
				zaposleni.add(zaposlen);
				

				
			}
			a.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void snimiZaposlene(){
		try{
			File zaposleniFajl = new File("src/fajlovi/zaposlen.txt");
			String string = "";
			for (Zaposlen zaposlen : zaposleni){
				string += zaposlen.getIme() + "|" + zaposlen.getPrezime() + "|" + zaposlen.getJMBG() + "|" + zaposlen.getAdresa() + "|" + zaposlen.getPol() + "|" + zaposlen.getPlata() + "|" + zaposlen.getKorisnickoIme() + "|" + zaposlen.getSifra() + "|" + zaposlen.isStatus() + "\n";
			}
			BufferedWriter upisi = new BufferedWriter(new FileWriter(zaposleniFajl));
			upisi.write(string);
			upisi.close();
			
		}catch (Exception e){
			e.printStackTrace();
			
		}
			
	}
	
	public void snimiClanove(){
		try{
			File clanoviFajl = new File("src/fajlovi/clan.txt");
			String string = "";
			for (Clan clan : clanovi){
				string += clan.getIme() + "|" + clan.getPrezime() + "|" + clan.getJMBG() + "|" + clan.getAdresa() + "|" + clan.getPol()+ "|" + clan.getBrojClanskeKarte() + "|" + clan.isAktivan() + "|" + clan.isStatus() +  "\n"; 
			}
			BufferedWriter upisi = new BufferedWriter(new FileWriter(clanoviFajl));
			upisi.write(string);
			upisi.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void izbrisiZaposlenog(String korisnickoIme){
		for(Zaposlen zaposlen : zaposleni){
			if(zaposlen.getKorisnickoIme() == korisnickoIme){
				zaposlen.setStatus(false);
			}
		}
		
	}
	
	public Zaposlen nadjiZaposlenog(String korisnickoIme){
		for (Zaposlen zaposlen : zaposleni){
			if (zaposlen.getKorisnickoIme() == korisnickoIme){
				return zaposlen;
			}
		}
		return null;
	}	
	
	public void ucitajFilmove(){
		try{
			File filmoviFajl = new File("src/fajlovi/film.txt");
			BufferedReader a = new BufferedReader(new FileReader(filmoviFajl));
			String line = null;
			while((line = a.readLine()) != null){
				String[] split = line.split("\\|");
				String imeNaSrpskom = split[0];
				String imeOrginal = split[1];
				int godinaIzdavanja = Integer.parseInt(split[2]);
				String imeIPrezimeRez = split[3];
				String opis = split[4];
				double trajanje = Double.parseDouble(split[5]);
				boolean status = Boolean.parseBoolean(split[6]);
				Film film = new Film(imeNaSrpskom,imeOrginal, godinaIzdavanja, imeIPrezimeRez, opis, trajanje, status);
				filmovi.add(film);
			}
			a.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void ucitajZanrove(){
		try{
			File zanrFajl = new File("src/fajlovi/zanr.txt");
			BufferedReader a = new BufferedReader(new FileReader(zanrFajl));
			String line = null;
			while((line = a.readLine()) != null){
				String[] split = line.split("\\|");
				String oznakaZanra = split[0];
				String nazivZanra = split[1];
				boolean status = Boolean.parseBoolean(split[2]);
				Zanr zanr = new Zanr(oznakaZanra, nazivZanra, status);
				zanrovi.add(zanr);
			}
			a.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public Zaposlen login(String korisnickoIme, String sifra){
		for (Zaposlen zaposlen : zaposleni){
			if (zaposlen.getKorisnickoIme().equals(korisnickoIme) && zaposlen.getSifra().equals(sifra)){
				return zaposlen;
			}
		}
		return null;
	}
	public int noviBrojClanskeKarte(ArrayList<Clan> clanovi){
		int broj = 0;
		for (int i = 0; i< clanovi.size(); i++){
			 broj = clanovi.get(i).getBrojClanskeKarte();
			
		}
		return broj + 1;
		
		
	}
	
	public Clan nadjiClana(String ime, String prezime){
		for (Clan clan : clanovi){
			if(clan.getIme().equals(ime) && clan.getPrezime().equals(prezime)){
				return clan;

			}
		}
		return null;
	}
	
	public ArrayList<Clan> clanoviZaPrikaz(){
		ArrayList<Clan> clanTrue = new ArrayList<>();
		for (Clan clan : clanovi){
			if(clan.isStatus() == true){
				clanTrue.add(clan);
			}
		}
		return clanTrue;
	}
	
	public void izbrisiClana(String ime, String prezime){
		for(Clan clan : clanovi){
			if(clan.getIme().equals(ime) && clan.getPrezime().equals(prezime)){
				clan.setStatus(false);
			}
		}
		
	}
	
	public ArrayList<Film> filmoviZaPrikaz(){
		ArrayList<Film> filmTrue = new ArrayList<>();
		for(Film film : filmovi){
			if(film.isStatus() == true){
				filmTrue.add(film);
			}
		}
		return filmTrue;
	}
	
	public Film nadjiFilm(String ime, String imeRezisera){
		for (Film film : filmovi){
			if(film.getNaslovNaSrpskom().equals(ime) && film.getImeIPrezimeRez().equals(imeRezisera)){
				return film;
			}
		}
		return null;
	}
	public ArrayList<Film> getFilmvoi(){
		return filmovi;
	}
	
	public Film nadjiFilmSamoPoNaslovu(String ime){
		for (Film film : filmoviZaPrikaz()){
			if(film.getNaslovNaSrpskom().equals(ime)){
				return film;
			}
		}
		return null;
	}

	
	
	public void snimiFilmove(){
		try{
			File filmoviFajl = new File("src/fajlovi/film.txt");
			String string = "";
			for (Film film : filmovi){ 
				string += film.getNaslovNaSrpskom() + "|" + film.getOrginalNaslov() + "|" + film.getGodinaIzdavanja() + "|" + film.getImeIPrezimeRez() + "|" + film.getOpis() + "|" + film.getTrajanje() + "|" + film.isStatus() + "\n";
			}
			BufferedWriter upisi = new BufferedWriter(new FileWriter(filmoviFajl));
			upisi.write(string);
			upisi.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void izbrisiFilm(String imeFilma, String imeIPrezimeRez){
		for(Film film : filmovi){
			if(film.getNaslovNaSrpskom().equals(imeFilma) && film.getImeIPrezimeRez().equals(imeIPrezimeRez)){
				film.setStatus(false);
			}
		}
		
	}
	
	public ArrayList<Zanr> zanroviZaPrikaz(){
		ArrayList<Zanr> zanrTrue = new ArrayList<>();
		for(Zanr zanr : zanrovi){
			if(zanr.isStatus() == true){
				zanrTrue.add(zanr);
			}
		}
		return zanrTrue;
	}
	
	public String novaOznakaZanra(){
		String intOznaka = "";
		for (Zanr zanr : zanrovi){
			intOznaka = zanr.getOznakaZanra().substring(0, 1);
		}
		String novaOznak = Integer.toString(Integer.parseInt(intOznaka) + 1) + "z";
		return novaOznak;
	}
	
	public ArrayList<Zanr> getZanrovi(){
		return zanrovi;
	}
	
	public void snimiZanrove(){
		try{
			File zanrFajl = new File("src/fajlovi/zanr.txt");
			String string = "";
			for (Zanr zanr : zanrovi){ 
				string += zanr.getOznakaZanra() + "|" + zanr.getNazivZanra() + "|" + zanr.isStatus() +  "\n";
			}
			BufferedWriter upisi = new BufferedWriter(new FileWriter(zanrFajl));
			upisi.write(string);
			upisi.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public Zanr nadjiZanr(String oznaka, String naziv){
		for (Zanr zanr : zanrovi){
			if(zanr.getOznakaZanra().equals(oznaka) && zanr.getNazivZanra().equals(naziv)){
				return zanr;
			}
		}
		return null;
	}
	
	public void izbrisiZanr(String oznaka, String naziv){
		for(Zanr zanr : zanrovi){
			if(zanr.getOznakaZanra().equals(oznaka) && zanr.getNazivZanra().equals(naziv)){
				zanr.setStatus(false);
			}
		}
		
	}
	
	public void ucitajPrimerkeFilmova(){
		try{
			File primerciFajl = new File("src/fajlovi/primerciFilma.txt");
			BufferedReader a = new BufferedReader(new FileReader(primerciFajl));
			String line = null;
			while((line = a.readLine()) != null){
				String[] split = line.split("\\|");
				String oznaka = split[0];
				String medijum = split[1];
				String ime = split[2];
				int brojPrimeraka = Integer.parseInt(split[3]);
				boolean status = Boolean.parseBoolean(split[4]);
				PrimerciFilma primerak = new PrimerciFilma(oznaka, medijum, ime, brojPrimeraka, status);
				primerci.add(primerak);
			}
			a.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public ArrayList<PrimerciFilma> primerciZaPrikaz(){
		ArrayList<PrimerciFilma> primerakTrue = new ArrayList<>();
		for(PrimerciFilma primerak : primerci){
			if(primerak.isStatus() == true){
				primerakTrue.add(primerak);
			}
		}
		return primerakTrue;
	}
	
	public String novaOznakaPrimerak(){
		String intOznaka = "";
		for (PrimerciFilma primerak : primerci){
			intOznaka = primerak.getOznaka();
		}
		String novaOznak = Integer.toString(Integer.parseInt(intOznaka) + 1);
		return novaOznak;
	}
	
	public ArrayList<PrimerciFilma> getPrimerci(){
		return primerci;
	}
	
	public void snimiPrimerke(){
		try{
			File primerakFajl = new File("src/fajlovi/primerciFilma.txt");
			String string = "";
			for (PrimerciFilma primerak : primerci){ 
				string += primerak.getOznaka() + "|" + primerak.getMedijum() + "|" + primerak.getImeFilma() + "|" + primerak.getBrojPrimeraka() + "|" +  primerak.isStatus() + "\n";
			}
			BufferedWriter upisi = new BufferedWriter(new FileWriter(primerakFajl));
			upisi.write(string);
			upisi.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public PrimerciFilma nadjiPrimerak(String oznaka, String naziv){
		for (PrimerciFilma primerak : primerci){
			if(primerak.getOznaka().equals(oznaka) && primerak.getImeFilma().equals(naziv)){
				return primerak;
			}
		}
		return null;
	}
	
	public void izbrisiPrimerak(String oznaka, String naziv){
		for(PrimerciFilma primerak : primerci){
			if(primerak.getOznaka().equals(oznaka) && primerak.getImeFilma().equals(naziv)){
				primerak.setStatus(false);
			}
		}
		
	}
	
	public String novaSifraIznamljivanja(){
		try{
			File iznamljivanjeFajl = new File("src/fajlovi/iznamljivanje.txt");
			BufferedReader a = new BufferedReader(new FileReader(iznamljivanjeFajl));
			String line = null;
			String sifraStara = "";
			while((line = a.readLine()) != null){
				String[] split = line.split("\\|");
				sifraStara = split[0];
			}
			a.close();
			int sifraNova =Integer.parseInt(sifraStara) + 1;
			return Integer.toString(sifraNova);
			
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public void ucitajMedijume(){
		try{
			File medijumFajl = new File("src/fajlovi/medijum.txt");
			BufferedReader a = new BufferedReader(new FileReader(medijumFajl));
			String line = null;
			while((line = a.readLine()) != null){
				String[] split = line.split("\\|");
				String medijumNaziv = split[0];
				int cena = Integer.parseInt(split[1]);
				Medijum medijum = new Medijum(medijumNaziv, cena);
				medijumi.add(medijum);
			}
			a.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public int nadjiCenuMedijuma(ArrayList<PrimerciFilma> listaPrimeraka){
		int cena = 0;
		for(PrimerciFilma pr : listaPrimeraka){
			for(Medijum m : medijumi){
				if(m.getMedijum().equals(pr.getMedijum())){
					cena += m.getCena();
				}
			}
		}
		return cena;
		
	}
	
	public void snimiIznamljivanje(){
		try{
			File iznamljivanjeFajl = new File("src/fajlovi/iznamljivanje.txt");
			String string = "";
			for(Iznamljivanje i : iznamljivanje){
				string += i.getSifra() + "|" + i.getZaposleniKorisnickoIme() + "|" + i.getClanImeIPrezime() + "|" + i.getDatumIzdavanja() + "|" + i.getDatumVracanja() + "|" + i.isStatus() + "+";
				for (PrimerciFilma primerak : i.getFilmovi()){
					string += primerak.getOznaka() + "|" + primerak.getMedijum() + "|" + primerak.getImeFilma()+ "-";
				}
				string += "\n";
			}
			BufferedWriter upisi = new BufferedWriter(new FileWriter(iznamljivanjeFajl));
			upisi.write(string);
			upisi.close();


		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public ArrayList<Iznamljivanje> getIznamljivanje(){
		return iznamljivanje;
	}
	
	public void ucitajIznamljivanje(){
		try{
			File iznamljivanjeFajl = new File("src/fajlovi/iznamljivanje.txt");
			BufferedReader a = new BufferedReader(new FileReader(iznamljivanjeFajl));
			String line = null;
			ArrayList<PrimerciFilma> listaPrimeraka = new ArrayList<PrimerciFilma>();
			while((line = a.readLine()) != null){
				listaPrimeraka.clear();
				String[] split = line.split("\\+");
				String podaciIznamljivanje = split[0];
				String filmIznamljivanje= split[1];
				String[] s = podaciIznamljivanje.split("\\|");
				String sifra = s[0];
				String zaposleniKorisnicko = s[1];
				String clanImeIPrezime = s[2];
				String datumIznamljivanja = s[3];
				String datumVracanja = s[4];
				boolean status = Boolean.parseBoolean(s[5]);
				String[] k = filmIznamljivanje.split("\\-");
				for (String primerci : k){
					String[] b = primerci.split("\\|");
					String oznaka = b[0];
					String medijum = b[1];
					String naziv = b[2];
					PrimerciFilma primerak = nadjiPrimerak(oznaka, naziv);
					listaPrimeraka.add(primerak);
				}
				
				Iznamljivanje i = new Iznamljivanje(sifra, zaposleniKorisnicko, clanImeIPrezime, datumIznamljivanja, datumVracanja, status, listaPrimeraka);
				iznamljivanje.add(i);
				
			}
			a.close();
		}catch (Exception e){ 
			e.printStackTrace();
		}
	}
		
	public ArrayList<Iznamljivanje> iznamljivanjeZaPrikaz(){
		ArrayList<Iznamljivanje> iznamljivanjeTrue = new ArrayList<>();
		for(Iznamljivanje iznamljivanje : iznamljivanje){
			if(iznamljivanje.isStatus() == true){
				iznamljivanjeTrue.add(iznamljivanje);
			}
		}
		return iznamljivanjeTrue;
	}
	
	public Iznamljivanje nadjiIznamljivanje(String oznaka){
		for (Iznamljivanje iznamljivanje : iznamljivanje){
			if (iznamljivanje.getSifra() == oznaka){
				return iznamljivanje;
			}
		}
		return null;
	}
	
	public void povecajBrojPrimeraka(PrimerciFilma primerak){
		for (PrimerciFilma pr : primerci){
			if (pr == primerak){
				int brojPrimeraka = pr.getBrojPrimeraka() + 1;
				pr.setBrojPrimeraka(brojPrimeraka);
				snimiPrimerke();
			}
		}
		
	}
		
	
		
	
	
	@Override
	public String toString(){
		return "VIDEOTEKA \n--PIB: " + pib +
				"\n--Naziv: " + naziv + 
				"\n--Adresa: " + adresa;
	}
}
