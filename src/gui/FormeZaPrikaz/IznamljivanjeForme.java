package gui.FormeZaPrikaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import film.PrimerciFilma;
import gui.Iznamljivanje.Iznamljivanje;
import net.miginfocom.swing.MigLayout;
import osobe.Clan;
import osobe.Zaposlen;
import videoteka.Videoteka;

public class IznamljivanjeForme extends JFrame {
	
	private JTable tabelaPrimeraka;
	private JScrollPane tabelaPrimerakaScroll;
	private JButton dodaj;
	private JButton gotovo;
	private ArrayList<PrimerciFilma> listaPrimeraka;
	private JLabel lblIme;
	private JTextField txtIme;
	private JLabel lblPrezime;
	private JTextField txtPrezime;
	private JLabel lblKolikoDana;
	private ButtonGroup KolikoDana;
	private JRadioButton dan;
	private JRadioButton dvaDana;
	private JRadioButton triDana;
	
	private Videoteka videoteka;
	private Zaposlen zaposlen;
	
	public IznamljivanjeForme(Videoteka videoteka, Zaposlen zaposlen){
		
		this.videoteka = videoteka;
		this.zaposlen = zaposlen;
		this.listaPrimeraka = new ArrayList<>();
		
		setTitle("Iznamljivanje filmova");
		setSize(700, 500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGui();
		initActions();
	}
	
	private void initGui(){
		MigLayout layout = new MigLayout("wrap 2", "[][]" , "[]20[][][]20[]");
		setLayout(layout);
		String[] zaglavlje = new String[] {"Oznaka", "Medijum", "Naslov", "Broj"};
		Object[][] sadrzaj = new Object[this.videoteka.primerciZaPrikaz().size()][zaglavlje.length];
		for (int i = 0; i< this.videoteka.primerciZaPrikaz().size(); i++){
			PrimerciFilma primerak = this.videoteka.primerciZaPrikaz().get(i);
			sadrzaj[i][0] = primerak.getOznaka();
			sadrzaj[i][1] = primerak.getMedijum();
			sadrzaj[i][2] = primerak.getImeFilma();
			sadrzaj[i][3] = primerak.getBrojPrimeraka();
		}
		
		DefaultTableModel model = new DefaultTableModel(sadrzaj, zaglavlje);
		this.tabelaPrimeraka = new JTable(model);
		this.tabelaPrimeraka.setColumnSelectionAllowed(false);
		this.tabelaPrimeraka.setRowSelectionAllowed(true);
		this.tabelaPrimeraka.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.tabelaPrimeraka.setDefaultEditor(Object.class, null);
		this.tabelaPrimerakaScroll = new JScrollPane(tabelaPrimeraka);
		this.tabelaPrimeraka.setMaximumSize(getSize());
		add(this.tabelaPrimeraka, BorderLayout.CENTER);
		
		this.lblIme = new JLabel("Ime clana");
		this.txtIme = new JTextField(20);
		this.lblPrezime = new JLabel("Prezime clana");
		this.txtPrezime = new JTextField(20);
		this.lblKolikoDana = new JLabel("Koliko dana iznamljujete ?");
		this.KolikoDana = new ButtonGroup();
		this.dan = new JRadioButton("Dan");
		this.dvaDana = new JRadioButton("Dva dana");
		this.triDana = new JRadioButton("Tri dana");
		KolikoDana.add(dan);
		KolikoDana.add(dvaDana);
		KolikoDana.add(triDana);
		 
		
		
		this.dodaj = new JButton("Dodaj");
		this.gotovo = new JButton("Gotovo");
		
		add( new JLabel());
		add(lblIme);
		add(txtIme);
		add(lblPrezime);
		add(txtPrezime);
		add(lblKolikoDana);
		add(dan, "split 3");
		add(dvaDana);
		add(triDana);
		add(new JLabel());
		add(dodaj, "split 2");
		add(gotovo);
		
	}
	
	private void initActions(){
		
		this.dodaj.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = tabelaPrimeraka.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, 
							"Morate odabrati primerak filma iz tabele");
				}else {
					String oznaka = (String)tabelaPrimeraka.getValueAt(red, 0);
					String naziv = (String)tabelaPrimeraka.getValueAt(red, 2);
					PrimerciFilma primerak = videoteka.nadjiPrimerak(oznaka, naziv);
					int brojPrimerak = primerak.getBrojPrimeraka() - 1;
					if(brojPrimerak < 0){
						JOptionPane.showMessageDialog(null, "Nemamo vise datih primeraka");
					}else{
						primerak.setBrojPrimeraka(brojPrimerak);
						videoteka.snimiPrimerke();
						listaPrimeraka.add(primerak);
						JOptionPane.showMessageDialog(null, "Dodali ste primerak filma u korpu");
					}
				
					
				}
			}
		});
		
		this.gotovo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String ime = txtIme.getText().trim();
				String prezime = txtPrezime.getText().trim();
				Clan clan = videoteka.nadjiClana(ime, prezime);
				if (clan != null){
					int brojDana = brojDana();
					if(listaPrimeraka != null){
						int cena = videoteka.nadjiCenuMedijuma(listaPrimeraka);
						int cenaUkupna = cena * brojDana;
						Iznamljivanje i = new Iznamljivanje(videoteka,clan, zaposlen, listaPrimeraka, brojDana, cenaUkupna);
						i.setVisible(true);
						IznamljivanjeForme.this.setVisible(false);
						IznamljivanjeForme.this.dispose();
					}else{
						JOptionPane.showMessageDialog(null, "Morate izabrati primerak filma iz tabele");
					}
					
				}else{
					JOptionPane.showMessageDialog(null, "Clan nije prijavljen");
				}

				
				
			}
		});

		
	}
	
	private int brojDana(){
		int brojDana = 0;
		if(dan.isSelected()){
			brojDana = 1;
		}else if(dvaDana.isSelected()){
			brojDana = 2;
		}else{
			brojDana = 3;
		}
		return brojDana;
	}

}
