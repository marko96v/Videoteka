package gui.FormeZaDodavanjeIIzmenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import film.Film;
import gui.FormeZaPrikaz.FilmForme;
import net.miginfocom.swing.MigLayout;
import osobe.Zaposlen;
import videoteka.Videoteka;

public class FilmoviFZDII extends JDialog{
	
	private JLabel lblImeNaSrpskom;
	private JTextField txtImeNaSrpskom;
	private JLabel lblImeOrginal;
	private JTextField txtImeOrginal;
	private JLabel lblImeIPrezimeRez;
	private JTextField txtImeIPrezimeRez;
	private JLabel lblOpis;
	private JTextField txtOpis;
	private JLabel lblGodina;
	private JTextField txtGodina;
	private JLabel lblTrajanje;
	private JTextField txtTrajanje;
	private JButton btnOk;
	private JButton btnCancel;
	
	private Videoteka videoteka;
	private Film film;
	private FilmForme filmForme;
	
	public FilmoviFZDII(Videoteka videoteka, Film film, FilmForme filmForme){
		
		this.videoteka = videoteka;
		this.film = film;
		this.filmForme = filmForme;
		setTitle("Film - Dodavanje");
		if (film != null){
			setTitle("Film - Izmena");
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		initGui();
		initActions();
		pack();
		
		
	}
	private void initGui(){
		MigLayout layout = new MigLayout("wrap 2", "[][]", "[][][][]10[][]20[]" );
		setLayout(layout);
		
		this.lblImeNaSrpskom = new JLabel("Ime na srpskom");
		this.txtImeNaSrpskom = new JTextField(20);
		this.lblImeOrginal = new JLabel("Orginal ime");
		this.txtImeOrginal = new JTextField(20);
		this.lblImeIPrezimeRez = new JLabel("Ime i prezime rezisera");
		this.txtImeIPrezimeRez = new JTextField(20);
		this.lblOpis = new JLabel("Opis");
		this.txtOpis = new JTextField(10);
		this.lblGodina = new JLabel("Godina izdavanja");
		this.txtGodina = new JTextField(10);
		this.lblTrajanje = new JLabel("Trajanje");
		this.txtTrajanje = new JTextField(10);
		this.btnOk = new JButton("Ok");
		this.btnCancel = new JButton("Cancel");
		
		if(film != null){
			initValues();
		}
		
		add(lblImeNaSrpskom);
		add(txtImeNaSrpskom);
		add(lblImeOrginal);
		add(txtImeOrginal);
		add(lblImeIPrezimeRez);
		add(txtImeIPrezimeRez);
		add(lblOpis);
		add(txtOpis);
		add(lblGodina);
		add(txtGodina);
		add(lblTrajanje);
		add(txtTrajanje);
		add(new JLabel());
		add(btnOk, "split 2");
		add(btnCancel);
	}
	
	private void initActions(){
		btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String imeNaSrpskom = txtImeNaSrpskom.getText().trim();
				String imeOrginal = txtImeOrginal.getText().trim();
				String imeIPrezimeRez = txtImeIPrezimeRez.getText().trim();
				String opis = txtOpis.getText().trim();
				int godina = Integer.parseInt(txtGodina.getText());
				double trajanje = Double.parseDouble(txtTrajanje.getText());
				if (imeNaSrpskom.equals("") && imeOrginal.equals("") && Integer.toString(godina).equals("")
						&& Double.toString(trajanje).equals("")) {
					JOptionPane.showMessageDialog(null, "Niste uneli sve podatke");
					
				}else{
					DefaultTableModel model = (DefaultTableModel)FilmoviFZDII.this.filmForme.table().getModel();
					if(film == null){
						film = new Film(imeNaSrpskom, imeOrginal, godina, imeIPrezimeRez, opis, trajanje, true);
						FilmoviFZDII.this.videoteka.getFilmvoi().add(film);
						model.addRow(new Object[] {imeNaSrpskom, imeOrginal, godina, imeIPrezimeRez, trajanje});
						FilmoviFZDII.this.videoteka.snimiFilmove();
					}else{
						film.setNaslovNaSrpskom(imeNaSrpskom);
						film.setOrginalNaslov(imeOrginal);
						film.setGodinaIzdavanja(godina);
						film.setImeIPrezimeRez(imeIPrezimeRez);
						film.setOpis(opis);
						film.setTrajanje(trajanje);
						film.setStatus(true);
						int red = FilmoviFZDII.this.filmForme.table().getSelectedRow();
						FilmoviFZDII.this.filmForme.table().setValueAt(imeNaSrpskom, red, 0);
						FilmoviFZDII.this.filmForme.table().setValueAt(imeOrginal, red, 1);
						FilmoviFZDII.this.filmForme.table().setValueAt(godina, red, 2);
						FilmoviFZDII.this.filmForme.table().setValueAt(imeIPrezimeRez, red, 3);
						FilmoviFZDII.this.filmForme.table().setValueAt(trajanje, red, 0);

						FilmoviFZDII.this.videoteka.snimiFilmove();
					}
					FilmoviFZDII.this.setVisible(false);
					FilmoviFZDII.this.dispose();
				}
				
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				FilmoviFZDII.this.setVisible(false);
				FilmoviFZDII.this.dispose();
				
			}
		});
	}
	
	private void initValues(){
		
		txtImeNaSrpskom.setText(film.getNaslovNaSrpskom());
		txtImeOrginal.setText(film.getOrginalNaslov());
		txtImeIPrezimeRez.setText(film.getImeIPrezimeRez());
		txtOpis.setText(film.getOpis());
		txtGodina.setText(Integer.toString(film.getGodinaIzdavanja()));
		txtTrajanje.setText(Double.toString(film.getTrajanje()));
		
	}
	

}
