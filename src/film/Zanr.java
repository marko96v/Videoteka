package film;

public class Zanr {
	
	private String oznakaZanra;
	private String nazivZanra;
	private boolean status;
	
	public Zanr(){
		this.oznakaZanra = "";
		this.nazivZanra = "";
		this.status = false;
	}
	
	public Zanr(String oznakaZanra, String nazivZanra, boolean status){
		this.oznakaZanra = oznakaZanra;
		this.nazivZanra = nazivZanra;
		this.status = status;
	}
	
	public Zanr(Zanr orginal){
		this.oznakaZanra = orginal.oznakaZanra;
		this.nazivZanra = orginal.nazivZanra;
		this.status = orginal.status;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getOznakaZanra() {
		return oznakaZanra;
	}

	public void setOznakaZanra(String oznakaZanra) {
		this.oznakaZanra = oznakaZanra;
	}

	public String getNazivZanra() {
		return nazivZanra;
	}

	public void setNazivZanra(String nazivZanra) {
		this.nazivZanra = nazivZanra;
	}
	@Override
	public String toString(){
		return "ZANR\n--" + nazivZanra;
	}

}
