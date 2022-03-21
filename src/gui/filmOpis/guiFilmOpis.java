package gui.filmOpis;

import java.awt.TextField;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import film.Film;
import videoteka.Videoteka;

public class guiFilmOpis extends JFrame{
	
	private JTextArea opis;
	
	private Videoteka videoteka;
	private Film film;
	
	public guiFilmOpis(Videoteka videoteka, Film film){
		this.videoteka = videoteka;
		this.film = film;
		setTitle("Opis " + film.getNaslovNaSrpskom() );
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		initGui();
		
	}
	
	private void initGui(){
		
		this.opis = new JTextArea();
		opis.setText(film.getOpis());
		opis.setEditable(false);
		
		add(opis);
		
		
	}

}
