package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import osobe.Zaposlen;
import videoteka.Videoteka;

public class LoginProzor extends JFrame {
	
	private JLabel lblPoruka;
	private JLabel lblKorisnickoIme;
	private JTextField txtKorsnickoIme;
	private JLabel lblSifra;
	private JPasswordField pfSifra;
	private JButton btnOK;
	private JButton btnCancel;
	
	private Videoteka videoteka;
	
	public LoginProzor(Videoteka videoteka){
		this.videoteka = videoteka;
		setTitle("Prijava na sistem");
		setSize(500, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		initGui();
		initActions();
		pack();
		
		
	}
	
	private void initGui(){
		MigLayout layout = new MigLayout("wrap2", "[][]", "[]20[][]30[]");
		setLayout(layout);
		
		this.lblPoruka = new JLabel("Dobro dosli. Molimo prijavite se na sistem");
		this.lblKorisnickoIme  = new JLabel("Korisnicko ime");
		this.txtKorsnickoIme = new JTextField(20);
		this.lblSifra = new JLabel("Sifra");
		this.pfSifra = new JPasswordField(20);
		this.btnOK = new JButton("Ok");
		this.btnCancel = new JButton("Cancel");
		
		this.getRootPane().setDefaultButton(btnOK);
		
		add(lblPoruka, "span 2");
		add(lblKorisnickoIme);
		add(txtKorsnickoIme);
		add(lblSifra);
		add(pfSifra);
		add(new JLabel());
		add(btnOK, "split 2");
		add(btnCancel);
		
	}
	
	private void initActions(){
		
		btnOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String korisnickoIme = txtKorsnickoIme.getText().trim();
				String sifra = new String(pfSifra.getPassword());
				
				if (korisnickoIme.equals("") || sifra.equals("")){
					JOptionPane.showMessageDialog(null, "Niste uneli sve podatke!!!");
				}else{
					Zaposlen zaposlen = videoteka.login(korisnickoIme, sifra);
					if(zaposlen != null && zaposlen.isStatus() == true){
						LoginProzor.this.setVisible(false);
						LoginProzor.this.dispose();
						GlavniProzor glavniProzor = new GlavniProzor(videoteka, zaposlen);
						glavniProzor.setVisible(true);
					}else{
						JOptionPane.showMessageDialog(null, "Greska, niste uneli ispravne podatke!!!");
					}
				}
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				LoginProzor.this.setVisible(false);
				LoginProzor.this.dispose();
				
			}
		});
		
	}

}
