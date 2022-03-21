package gui.FormeZaDodavanjeIIzmenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.omg.PortableServer.POAPackage.AdapterAlreadyExists;

import film.Film;
import film.Medijum;
import film.PrimerciFilma;
import gui.FormeZaPrikaz.PrimerciFilmovaForme;
import net.miginfocom.swing.MigLayout;
import osobe.Clan;
import videoteka.Videoteka;

public class PrimerciFilmaFZDII extends JDialog{
	
	private JLabel lblOznaka;
	private JTextField txtOznaka;
	private JLabel lblMedijum;
	private ButtonGroup bgMedijum;
	private JRadioButton rbtnVHS;
	private JRadioButton rbtnDVD;
	private JRadioButton rbtnBRdvd;
	private JLabel lblIme;
	private JTextField txtIme;
	private JLabel lblBroj;
	private JTextField txtBroj;
	private JButton btnOk;
	private JButton btnCancel;
	
	private Videoteka videoteka;
	private PrimerciFilma primerak;
	private PrimerciFilmovaForme primerciForme;
	private Medijum medijum;
	
	public PrimerciFilmaFZDII(Videoteka videoteka, PrimerciFilma primerak,  PrimerciFilmovaForme primerciForme){
		this.videoteka = videoteka;
		this.primerak = primerak;
		this.primerciForme = primerciForme;
		setTitle("Primerci filma - Dodavanje");
		if (primerak != null){
			setTitle("Primerci filma - Izmena");
		}
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		initGui();
		initActions();
		pack();
	}
	
	private void initGui(){
		MigLayout layout = new MigLayout("wrap 2", "[][]","[][][][]20[]");
		setLayout(layout);
		
		this.lblOznaka = new JLabel("Oznaka");
		this.txtOznaka = new JTextField(8);
		txtOznaka.setEditable(false);
		this.lblMedijum = new JLabel("Medijum");
		this.bgMedijum = new ButtonGroup();
		this.rbtnVHS = new JRadioButton("VHS");
		this.rbtnDVD = new JRadioButton("DVD");
		this.rbtnBRdvd = new JRadioButton("BlueRay");
		this.lblIme = new JLabel("Naslov");
		this.txtIme = new JTextField(20);
		this.lblBroj = new JLabel("Broj primeraka");
		this.txtBroj = new JTextField(8);
		this.btnOk = new JButton("Ok");
		this.btnCancel = new JButton("Cancel");
		
		if(primerak != null){
			initValues();
		}else{
			txtOznaka.setText(videoteka.novaOznakaPrimerak());
			
		}
		
		add(lblOznaka);
		add(txtOznaka);
		add(lblMedijum);
		add(rbtnVHS, "split 3");
		add(rbtnDVD);
		add(rbtnBRdvd);
		add(lblIme);
		add(txtIme);
		add(lblBroj);
		add(txtBroj);
		add(new JLabel());
		add(btnOk, "split 2");
		add(btnCancel);
		
		
		
	}
	
	private void initActions(){
		
		btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String oznaka = txtOznaka.getText().trim();
				String medijum = "";
				if (rbtnVHS.isSelected()){
					medijum = "VHS";
				}else if(rbtnDVD.isSelected()){
					medijum = "DVD";
				}else{
					medijum = "BlueRay";
				}
				String naslov = txtIme.getText().trim();
				int broj = Integer.parseInt(txtBroj.getText().trim());
				if(oznaka.equals("") && naslov.equals("") && Integer.toString(broj).equals("")){
					JOptionPane.showMessageDialog(null, "Niste uneli sve podatke");
				}else{
			
						DefaultTableModel model = (DefaultTableModel)PrimerciFilmaFZDII.this.primerciForme.table().getModel();
						if(primerak == null){
							Film film = videoteka.nadjiFilmSamoPoNaslovu(naslov);
							if(film != null && film.getNaslovNaSrpskom().equals(naslov)){
								boolean status = true;
								primerak = new PrimerciFilma(oznaka, medijum, naslov, broj, status);
								PrimerciFilmaFZDII.this.videoteka.getPrimerci().add(primerak);
								model.addRow(new Object[] {oznaka, medijum, naslov, broj});
									PrimerciFilmaFZDII.this.videoteka.snimiPrimerke();
							}else{
								JOptionPane.showMessageDialog(null, "Nepostojeci film");
							}
							
						
						}else{
							primerak.setOznaka(oznaka);
							primerak.setMedijum(medijum);
							primerak.setImeFilma(naslov);
							primerak.setBrojPrimeraka(broj);
							primerak.setStatus(true);
							int red = PrimerciFilmaFZDII.this.primerciForme.table().getSelectedRow();
							PrimerciFilmaFZDII.this.primerciForme.table().setValueAt(oznaka, red, 0);
							PrimerciFilmaFZDII.this.primerciForme.table().setValueAt(medijum, red, 1);
							PrimerciFilmaFZDII.this.primerciForme.table().setValueAt(naslov, red, 2);
							PrimerciFilmaFZDII.this.primerciForme.table().setValueAt(broj, red, 3);
							PrimerciFilmaFZDII.this.videoteka.snimiPrimerke();
						}
					}
					
					PrimerciFilmaFZDII.this.setVisible(false);
					PrimerciFilmaFZDII.this.dispose();
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PrimerciFilmaFZDII.this.setVisible(false);
				PrimerciFilmaFZDII.this.dispose();
				
			}
		});
		
	}
	
	private void initValues(){
		this.txtOznaka.setText(primerak.getOznaka());
		if (primerak.getMedijum().equals("VHS")){
			rbtnVHS.setSelected(true);
		}else if(primerak.getMedijum().equals("DVD")){
			rbtnDVD.setSelected(true);
			
		}else{
			rbtnBRdvd.setSelected(true);
		}
		this.txtIme.setText(primerak.getImeFilma());
		txtIme.setEditable(false);
		this.txtBroj.setText(Integer.toString(primerak.getBrojPrimeraka()));
		
	}

}
