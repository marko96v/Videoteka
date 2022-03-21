package glavni;




import gui.LoginProzor;
import videoteka.Videoteka;

public class videotekaMain {
	
	public static void main(String[] args) {
	
	Videoteka videoteka = new Videoteka("123456789", "VideotekaNS", "Novi Sad 10");
	videoteka.ucitajClanove();
	videoteka.ucitajFilmove();
	videoteka.ucitajZaposlene();
	videoteka.ucitajZanrove();
	videoteka.ucitajPrimerkeFilmova();
	videoteka.ucitajMedijume();
	videoteka.ucitajIznamljivanje();
	
	LoginProzor prozorLogin = new LoginProzor(videoteka);
	prozorLogin.setVisible(true);
	}
}
