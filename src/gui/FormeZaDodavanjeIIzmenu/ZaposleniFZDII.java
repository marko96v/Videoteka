package gui.FormeZaDodavanjeIIzmenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.Policy;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import gui.GlavniProzor;
import gui.FormeZaPrikaz.ZaposleniForme;
import net.miginfocom.swing.MigLayout;
import osobe.Zaposlen;
import videoteka.Videoteka;

public class ZaposleniFZDII extends JDialog{
	
	private JLabel lblIme;
	private JTextField txtIme;
	private JLabel lblPrezime;
	private JTextField txtPrezime;
	private JLabel lblJMBG;
	private JTextField txtJMBG;
	private JLabel lblAdresa;
	private JTextField txtAdresa;
	private JLabel lblPol;
	private JRadioButton rbtnMuski;
	private JRadioButton rbtnZenski;
	private ButtonGroup btngPol;
	private JLabel lblPlata;
	private JTextField txtPlata;
	private JLabel lblKorisnickoIme;
	private JTextField txtKorisnickoIme;
	private JLabel lblSifra;
	private JPasswordField pfSifra;
	private JButton btnOk;
	private JButton btnCancel;
	
	
	private Videoteka videoteka;
	private Zaposlen zaposlen;
	private ZaposleniForme zaposleniForma;
	public ZaposleniFZDII(Videoteka videoteka, Zaposlen zaposlen, ZaposleniForme zaposleniForme){
		
		this.videoteka = videoteka;
		this.zaposlen = zaposlen;
		this.zaposleniForma = zaposleniForme;
		setTitle("Zaposleni - Dodavanje");
		if (zaposlen != null){
			setTitle("Zaposleni - Izmena");
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		initGui();
		initActions();
		pack();
		
		
	}
	
	private void initGui(){
		MigLayout layout = new MigLayout("wrap2", "[][]","[][][][][][][][]20[]");
		setLayout(layout);
		
		this.lblIme = new JLabel("Ime");
		this.txtIme = new JTextField(20);
		this.lblPrezime = new JLabel("Prezime");
		this.txtPrezime = new JTextField(20);
		this.lblJMBG = new JLabel("JMBG");
		this.txtJMBG = new JTextField(20);
		this.lblAdresa = new JLabel("Adresa");
		this.txtAdresa = new JTextField(20);
		this.lblPol = new JLabel("Pol");
		this.rbtnMuski = new JRadioButton("Muski");
		this.rbtnZenski = new JRadioButton("Zenski");
		this.rbtnZenski.setSelected(true);
		this.btngPol = new ButtonGroup();
		btngPol.add(rbtnMuski);
		btngPol.add(rbtnZenski);
		this.lblPlata = new JLabel("Plata");
		this.txtPlata = new JTextField(20);
		this.lblKorisnickoIme = new JLabel("Korisnicko ime");
		this.txtKorisnickoIme = new JTextField(20);
		this.lblSifra = new JLabel("Sifra");
		this.pfSifra = new JPasswordField(20);
		this.btnOk = new JButton("OK");
		this.btnCancel = new JButton("Cancel");
		
		if (zaposlen != null){
			initValues();
		}
		
		add(lblIme);
		add(txtIme);
		add(lblPrezime);
		add(txtPrezime);
		add(lblJMBG);
		add(txtJMBG);
		add(lblAdresa);
		add(txtAdresa);
		add(lblPol);
		add(rbtnMuski, "split 2");
		add(rbtnZenski);
		add(lblPlata);
		add(txtPlata);
		add(lblKorisnickoIme);
		add(txtKorisnickoIme);
		add(lblSifra);
		add(pfSifra);
		add(new JLabel());
		add(btnOk, "split 2");
		add(btnCancel);
		
		
		
	}
	
	private void initActions(){
		btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String ime = txtIme.getText().trim();
				String prezime = txtPrezime.getText().trim();
				String JMBG = txtJMBG.getText().trim();
				String adresa = txtAdresa.getText().trim();
				String pol = btngPol.getSelection().getActionCommand();
				double plata = Double.parseDouble(txtPlata.getText().trim());
				String korisnickoIme = txtKorisnickoIme.getText().trim();
				String sifra = new String(pfSifra.getPassword());
				if (ime.equals("") || prezime.equals("") || JMBG.equals("") || adresa.equals("")
						|| korisnickoIme.equals("") || sifra.equals("")){
					JOptionPane.showMessageDialog(null, "Niste uneli sve podatke");
				}else{
					DefaultTableModel model = (DefaultTableModel)ZaposleniFZDII.this.zaposleniForma.table().getModel();
					if(zaposlen == null){
						zaposlen = new Zaposlen(ime, prezime, JMBG, adresa, pol, plata, korisnickoIme, sifra);
						ZaposleniFZDII.this.videoteka.getZaposleni().add(zaposlen);
						model.addRow(new Object[] {ime, prezime, JMBG, adresa, pol, plata, korisnickoIme,sifra});
						ZaposleniFZDII.this.videoteka.snimiZaposlene();
						
						
					}else{
						zaposlen.setIme(ime);
						zaposlen.setPrezime(prezime);
						zaposlen.setJMBG(JMBG);
						zaposlen.setAdresa(adresa);
						zaposlen.setPol(pol);
						zaposlen.setPlata(plata);
						zaposlen.setKorisnickoIme(korisnickoIme);
						zaposlen.setSifra(sifra);
						int red = ZaposleniFZDII.this.zaposleniForma.table().getSelectedRow();
						ZaposleniFZDII.this.zaposleniForma.table().setValueAt(ime, red, 0);
						ZaposleniFZDII.this.zaposleniForma.table().setValueAt(prezime, red, 1);
						ZaposleniFZDII.this.zaposleniForma.table().setValueAt(JMBG, red, 2);
						ZaposleniFZDII.this.zaposleniForma.table().setValueAt(adresa, red, 3);
						ZaposleniFZDII.this.zaposleniForma.table().setValueAt(pol, red, 4);
						ZaposleniFZDII.this.zaposleniForma.table().setValueAt(plata, red, 5);
						ZaposleniFZDII.this.zaposleniForma.table().setValueAt(korisnickoIme, red, 6);
						ZaposleniFZDII.this.videoteka.snimiZaposlene();




						
					}
					ZaposleniFZDII.this.setVisible(false);
					ZaposleniFZDII.this.dispose();
				}
				
			}
		});
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ZaposleniFZDII.this.setVisible(false);
				ZaposleniFZDII.this.dispose();
			}
		});
		
	}
	private void initValues(){
		
		txtIme.setText(zaposlen.getIme());
		txtPrezime.setText(zaposlen.getPrezime());
		txtJMBG.setText(zaposlen.getJMBG());
		txtAdresa.setText(zaposlen.getAdresa());
		if(zaposlen.getPol().equals("Muski")){
			rbtnMuski.setSelected(true);
		}else{
			rbtnZenski.setSelected(true);
		}
		txtPlata.setText(Double.toString(zaposlen.getPlata()));
		txtKorisnickoIme.setText(zaposlen.getKorisnickoIme());
		pfSifra.setText(zaposlen.getSifra());
	}

}
