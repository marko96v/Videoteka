package gui.Iznamljivanje;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;

import film.PrimerciFilma;
import net.miginfocom.swing.MigLayout;
import osobe.Clan;
import osobe.Zaposlen;
import videoteka.Videoteka;

public class Iznamljivanje extends JDialog {
	
	private JLabel lbloznaka;
	private JTextField txtOznaka;
	private JLabel lblKorisnickoImeZap;
	private JTextField txtKorisnickoImeZap;
	private JLabel lblImeIPrezimeClna;
	private JTextField txtImeIPrezimeClana;
	private JLabel lblDatumIzdavanja;
	private JTextField txtDatumIzdavanja;
	private JLabel lblDatumVracanja;
	private JTextField txtDatumVracanja;
	private JTable tabelaPrimeraka;
	private JScrollPane tabelaScroll;
	private JLabel lblCena;
	private JTextField txtCena;
	private JButton btnOk;
	private JButton btnCancel;
	
	private Videoteka videoteka;
	private Clan clan;
	private Zaposlen zaposlen;
	private ArrayList<PrimerciFilma> primerciFilma;
	private int brojDana;
	private int cena;
	
	
	public Iznamljivanje(Videoteka videoteka,Clan clan, Zaposlen zaposlen, ArrayList<PrimerciFilma> primerciFilma, int brojDana, int cena){
		
		this.videoteka = videoteka;
		this.zaposlen = zaposlen;
		this.primerciFilma = primerciFilma;
		this.clan = clan;
		this.brojDana = brojDana;
		this.cena = cena;
		setTitle("Iznamljivanje filmova");
		setSize(700, 500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGui();
		initActions();
		
	}
	
	private void initGui(){
		MigLayout layout = new MigLayout("wrap 2", "[][]" , "[][][][][][][][]20[]");
		setLayout(layout);
		
		 this.lbloznaka = new JLabel("Sifra");
		 this.txtOznaka = new JTextField(8);
		 txtOznaka.setText(videoteka.novaSifraIznamljivanja());
		 txtOznaka.setEditable(false);
		 this.lblKorisnickoImeZap = new JLabel("Zaposleni");
		 this.txtKorisnickoImeZap = new JTextField(20);
		 txtKorisnickoImeZap.setText(zaposlen.getKorisnickoIme());
		 txtKorisnickoImeZap.setEditable(false);
		 this.lblImeIPrezimeClna = new JLabel("Ime i prezime clana");
		 this.txtImeIPrezimeClana = new JTextField(20);
		 txtImeIPrezimeClana.setText(clan.getIme() + " " + clan.getPrezime());
		 txtImeIPrezimeClana.setEditable(false);
		 this.lblDatumIzdavanja = new JLabel("Datum izdavanja");
		 this.txtDatumIzdavanja = new JTextField(10);
		 SimpleDateFormat sd = new SimpleDateFormat("dd.mm.yyyy");
		 Calendar cal = Calendar.getInstance();
		 int day = cal.get(Calendar.DAY_OF_MONTH);
		 int mounth = cal.get(Calendar.MONTH);
		 int year = cal.get(Calendar.YEAR);
		 String datumIzdavanja = Integer.toString(day) + "." + Integer.toString(mounth) + "." + Integer.toString(year);
		 txtDatumIzdavanja.setText(datumIzdavanja);
		 txtDatumIzdavanja.setEditable(false);
		 this.lblDatumVracanja = new JLabel("Datum vracanja");
		 this.txtDatumVracanja = new JTextField(10);
		 String datumVracanja = Integer.toString(day + brojDana) + "." + Integer.toString(mounth) + "." + Integer.toString(year);
		 txtDatumVracanja.setText(datumVracanja);
		 txtDatumVracanja.setEditable(false);
		 this.lblCena = new JLabel("Cena");
		 this.txtCena = new JTextField(10);
		 txtCena.setText(Integer.toString(cena));
		 txtCena.setEditable(false);
		 

 
		
		String[] zaglavlje = new String[] {"Oznaka", "Medijum", "Naslov"};
		Object[][] sadrzaj = new Object[this.primerciFilma.size()][zaglavlje.length];
		for (int i = 0; i< this.primerciFilma.size(); i++){
			PrimerciFilma primerak = this.primerciFilma.get(i);
			sadrzaj[i][0] = primerak.getOznaka();
			sadrzaj[i][1] = primerak.getMedijum();
			sadrzaj[i][2] = primerak.getImeFilma();
		}
		
		DefaultTableModel model = new DefaultTableModel(sadrzaj, zaglavlje);
		this.tabelaPrimeraka = new JTable(model);
		this.tabelaPrimeraka.setColumnSelectionAllowed(false);
		this.tabelaPrimeraka.setRowSelectionAllowed(true);
		this.tabelaPrimeraka.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.tabelaPrimeraka.setDefaultEditor(Object.class, null);
		this.tabelaScroll = new JScrollPane(tabelaPrimeraka);
		this.tabelaPrimeraka.setSize(getPreferredSize());
		
		this.btnOk = new JButton("Ok");
		this.btnCancel = new JButton("Cancel");
		
		add(lbloznaka);
		add(txtOznaka);
		add(lblKorisnickoImeZap);
		add(txtKorisnickoImeZap);
		add(lblImeIPrezimeClna);
		add(txtImeIPrezimeClana);
		add(lblDatumIzdavanja);
		add(txtDatumIzdavanja);
		add(lblDatumVracanja);
		add(txtDatumVracanja);

		add(new JLabel());
		add(tabelaPrimeraka);
		add(lblCena);
		add(txtCena);
		add(new JLabel());
		add(btnOk, "split 2");
		add(btnCancel);
	}
	
	private void initActions(){
		btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String oznaka = txtOznaka.getText().trim();
				String korisnickoImeZap = txtKorisnickoImeZap.getText().trim();
				String ImeIPrezimeClana = txtImeIPrezimeClana.getText().trim();
				String datumIzdavanja = txtDatumIzdavanja.getText().trim();
				String datumVracanja = txtDatumVracanja.getText().trim();
				ArrayList<PrimerciFilma> filmovi = primerciFilma;
				
				iznamljivanje.Iznamljivanje i = new iznamljivanje.Iznamljivanje(oznaka, korisnickoImeZap, ImeIPrezimeClana, datumIzdavanja, datumVracanja, true, filmovi);
				Iznamljivanje.this.videoteka.getIznamljivanje().add(i);
				Iznamljivanje.this.videoteka.snimiIznamljivanje();
				
				
				Iznamljivanje.this.setVisible(false);
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Iznamljivanje.this.setVisible(false);
				
			}
		});
	
		
		
		
	}

}
