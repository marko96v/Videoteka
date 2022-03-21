package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import film.PrimerciFilma;
import gui.FormeZaPrikaz.ClanoviForme;
import gui.FormeZaPrikaz.FilmForme;
import gui.FormeZaPrikaz.IznamljivanjeForme;
import gui.FormeZaPrikaz.PrimerciFilmovaForme;
import gui.FormeZaPrikaz.ZanroviForme;
import gui.FormeZaPrikaz.ZaposleniForme;
import gui.vracanje.Vracanje;
import osobe.Zaposlen;
import videoteka.Videoteka;

public class GlavniProzor extends JFrame {
	
	private JMenuBar mainMenu;
	private JMenu osobe;
	private JMenuItem zaposleni;
	private JMenuItem clanovi;
	private JMenu film;
	private JMenuItem filmovi;
	private JMenuItem zanroviFilmova;
	private JMenuItem primerciFilmova;
	private JMenuItem iznamljivanje;
	private JMenuItem vracanje;
	
	private Videoteka videoteka;
	private Zaposlen zaposlen;
	
	
	public GlavniProzor(Videoteka videoteka, Zaposlen zaposlen){
		
		this.videoteka = videoteka;
		this.zaposlen = zaposlen;
		setTitle("Videoteka -" + zaposlen.getKorisnickoIme());
		setSize(500, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		initGui();
		initActions();
		
	}
	
	private void initGui(){
		
		this.mainMenu = new JMenuBar();
		this.osobe = new JMenu("Osobe");
		this.zaposleni = new JMenuItem("Zaposleni");
		this.clanovi = new JMenuItem("Clanovi");
		this.film = new JMenu("Film");
		this.filmovi = new JMenuItem("Filmovi");
		this.zanroviFilmova = new JMenuItem("Zanrovi filmova");
		this.primerciFilmova = new JMenuItem("Primerci filmova");
		this.iznamljivanje = new JMenuItem("Iznamljivanje");
		this.vracanje = new JMenuItem("Vracanje filmova");
		
		this.osobe.add(zaposleni);
		this.osobe.add(clanovi);
		this.film.add(filmovi);
		this.film.add(zanroviFilmova);
		this.film.add(primerciFilmova);
		
		this.mainMenu.add(osobe);
		this.mainMenu.add(film);
		this.mainMenu.add(iznamljivanje);
		this.mainMenu.add(vracanje);
		
		setJMenuBar(this.mainMenu);
		
	}
	
	private void initActions(){
		zaposleni.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ZaposleniForme zf = new ZaposleniForme(GlavniProzor.this.videoteka);
				zf.setVisible(true);
				
			}
		});
		
		clanovi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ClanoviForme cf = new ClanoviForme(GlavniProzor.this.videoteka);
				cf.setVisible(true);
				
			}
		});
		
		filmovi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				FilmForme ff = new FilmForme(GlavniProzor.this.videoteka);
				ff.setVisible(true);
				
			}
		});
		
		zanroviFilmova.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ZanroviForme zf = new ZanroviForme(GlavniProzor.this.videoteka);
				zf.setVisible(true);
				
			}
		});
		
		primerciFilmova.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PrimerciFilmovaForme pff = new PrimerciFilmovaForme(GlavniProzor.this.videoteka);
				pff.setVisible(true);
				
				
			}
		});
		
		iznamljivanje.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				IznamljivanjeForme iff = new IznamljivanjeForme(GlavniProzor.this.videoteka, GlavniProzor.this.zaposlen);
				iff.setVisible(true);
				
			}
		});
		
		vracanje.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 Vracanje v = new Vracanje(GlavniProzor.this.videoteka);
				 v.setVisible(true);
				
			}
		});
		
	}

}
