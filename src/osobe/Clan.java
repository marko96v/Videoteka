package osobe;

public class Clan extends Osoba {
	
	private int brojClanskeKarte;
	private boolean aktivan;
	private boolean status;
	
	public Clan(){
		this.brojClanskeKarte = 0;
		this.aktivan = false;
		this.status = false;
	}
	
	public Clan(String ime, String prezime, String JMBG, String adresa, String pol, int brojClanskeKarte,
			boolean aktivan, boolean status){
		super(ime, prezime, JMBG, adresa, pol);
		this.brojClanskeKarte = brojClanskeKarte;
		this.aktivan = aktivan;
		this.status = status;
	}
	
	public Clan(Clan orginal){
		super(orginal);
		this.brojClanskeKarte = orginal.brojClanskeKarte;
		this.aktivan = orginal.aktivan;
		this.status = orginal.status;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getBrojClanskeKarte() {
		return brojClanskeKarte;
	}

	public void setBrojClanskeKarte(int brojClanskeKarte) {
		this.brojClanskeKarte = brojClanskeKarte;
	}

	public boolean isAktivan() {
		return aktivan;
	}

	public void setAktivan(boolean aktivan) {
		this.aktivan = aktivan;
	}
	@Override
	public String toString(){
		return "CLAN " + super.toString() + 
				"\nBroj clanske karte: " + brojClanskeKarte;
	}
}
