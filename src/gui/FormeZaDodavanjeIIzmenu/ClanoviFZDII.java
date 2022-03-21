package gui.FormeZaDodavanjeIIzmenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.management.ClassLoadingMXBean;
import java.util.ArrayList;

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

import gui.FormeZaPrikaz.ClanoviForme;
import net.miginfocom.swing.MigLayout;
import osobe.Clan;
import osobe.Zaposlen;
import videoteka.Videoteka;

public class ClanoviFZDII extends JDialog{
	
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
	private JLabel lblbrojClanskeKarte;
	private JTextField txtBrojClanskeKarte;
	private JLabel lblStatus;
	private JRadioButton rbtnAktivan;
	private JRadioButton rbtnNijeAktivan;
	private ButtonGroup btngStatus;
	private JButton btnOk;
	private JButton btnCancel;
	
	private Videoteka videoteka;
	private Clan clan;
	private ClanoviForme clanoviForme;
	
	public ClanoviFZDII(Videoteka videoteka, Clan clan, ClanoviForme clanoviForme){
		this.videoteka = videoteka;
		this.clan = clan;
		this.clanoviForme = clanoviForme;
		setTitle("Clanovi - Dodavanje");
		if (clan != null){
			setTitle("Clanovi - Izmena");
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
		this.lblbrojClanskeKarte = new JLabel("Broj clanske karte");
		this.txtBrojClanskeKarte = new JTextField();
		txtBrojClanskeKarte.setText(Integer.toString(videoteka.noviBrojClanskeKarte(videoteka.getClanovi())));
		this.lblStatus = new JLabel("Status");
		this.rbtnAktivan = new JRadioButton("Aktivan");
		this.rbtnNijeAktivan = new JRadioButton("Nije aktivan");
		this.rbtnAktivan.setSelected(true);
		this.btngStatus = new ButtonGroup();
		btngStatus.add(rbtnAktivan);
		btngStatus.add(rbtnNijeAktivan);
		this.btnOk = new JButton("Ok");
		this.btnCancel = new JButton("Cancel");
		
		if(clan != null){
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
		add(lblbrojClanskeKarte);
		add(txtBrojClanskeKarte);
		add(lblStatus);
		add(rbtnAktivan, "split 2");
		add(rbtnNijeAktivan);
		add(new JLabel());
		add(btnOk, "split 2");
		add(btnCancel);
		
	}
	
	private void initActions(){
		btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String ime = txtIme.getText().trim();
				String prezime = txtPrezime.getText().trim();
				String JMBG = txtJMBG.getText().trim();
				String adresa = txtAdresa.getText().trim();
				String pol = pol();
				int brojClanskeKarte = Integer.parseInt(txtBrojClanskeKarte.getText());
				boolean aktivan = status();
				if (ime.equals("") || prezime.equals("") || JMBG.equals("") || adresa.equals("")){
					JOptionPane.showMessageDialog(null, "Niste uneli sve podatke");
					
				}else{
					DefaultTableModel model = (DefaultTableModel)ClanoviFZDII.this.clanoviForme.table().getModel();
					if(clan == null){
						boolean status = true;
						int brojClanskeKarteNovi = videoteka.noviBrojClanskeKarte(videoteka.getClanovi());
						clan = new Clan(ime, prezime, JMBG, adresa, pol, brojClanskeKarte, aktivan, status);
						ClanoviFZDII.this.videoteka.getClanovi().add(clan);
						model.addRow(new Object[] {ime, prezime, JMBG, adresa, pol, brojClanskeKarteNovi, aktivan});
						ClanoviFZDII.this.videoteka.snimiClanove();
					}else{
						clan.setIme(ime);
						clan.setPrezime(prezime);
						clan.setJMBG(JMBG);
						clan.setAdresa(adresa);
						clan.setPol(pol);
						clan.setBrojClanskeKarte(brojClanskeKarte);
						clan.setAktivan(aktivan);
						int red = ClanoviFZDII.this.clanoviForme.table().getSelectedRow();
						ClanoviFZDII.this.clanoviForme.table().setValueAt(ime, red, 0);
						ClanoviFZDII.this.clanoviForme.table().setValueAt(prezime, red, 1);
						ClanoviFZDII.this.clanoviForme.table().setValueAt(JMBG, red, 2);
						ClanoviFZDII.this.clanoviForme.table().setValueAt(adresa, red, 3);
						ClanoviFZDII.this.clanoviForme.table().setValueAt(pol, red, 4);
						ClanoviFZDII.this.clanoviForme.table().setValueAt(brojClanskeKarte, red, 5);
						if (aktivan == true){
							ClanoviFZDII.this.clanoviForme.table().setValueAt("Aktivan", red, 6);
						}else{
							ClanoviFZDII.this.clanoviForme.table().setValueAt("Nije aktivan", red, 6);

						}
						ClanoviFZDII.this.videoteka.snimiClanove();
					}
					ClanoviFZDII.this.setVisible(false);
					ClanoviFZDII.this.dispose();
				}
			}
		});
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ClanoviFZDII.this.setVisible(false);
				ClanoviFZDII.this.dispose();
			}
		});
		
	}
	
	private void initValues(){
		
		txtIme.setText(clan.getIme());
		txtPrezime.setText(clan.getPrezime());
		txtJMBG.setText(clan.getJMBG());
		txtAdresa.setText(clan.getAdresa());
		if(clan.getPol().equals("Muski")){
			rbtnMuski.setSelected(true);
		}else{
			rbtnZenski.setSelected(true);
		}
		txtBrojClanskeKarte.setText(Integer.toString(clan.getBrojClanskeKarte()));
		if(clan.isAktivan() == true){
			rbtnAktivan.setSelected(true);
		}else{
			rbtnNijeAktivan.setSelected(true);
		}
		
		
		
	}
	
	private String pol(){
		String pol = "";
		if (rbtnMuski.isSelected()){
			pol = "Muski";
		}else if (rbtnZenski.isSelected()){
			pol = "Zenski";
		}
		return pol;
	}
	private boolean status(){
		boolean aktivan = false;
		if (rbtnAktivan.isSelected()){
			aktivan = true;
		}else if (rbtnNijeAktivan.isSelected()){
			aktivan = false;
		}
		return aktivan;
	}

}
