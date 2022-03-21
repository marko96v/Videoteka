package gui.FormeZaPrikaz;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import org.omg.CORBA.ShortHolder;
import org.w3c.dom.events.EventTarget;
import org.w3c.dom.views.AbstractView;

import film.Film;
import gui.FormeZaDodavanjeIIzmenu.ClanoviFZDII;
import gui.FormeZaDodavanjeIIzmenu.FilmoviFZDII;
import gui.filmOpis.guiFilmOpis;
import osobe.Clan;
import videoteka.Videoteka;

public class FilmForme extends JFrame {
	
	private JToolBar mainToolBar;
	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnDelete;
	private JScrollPane tableScroll;
	private JTable table;
	
	private Videoteka videoteka;
	
	public FilmForme(Videoteka videoteka){
		
		this.videoteka = videoteka;
		setTitle("Film");
		setSize(700, 500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGui();
		initActions();
	}
	
	private void initGui(){
		
		this.mainToolBar = new JToolBar();
		ImageIcon addIcon = new ImageIcon(getClass().getResource("/slike/add.gif"));
		this.btnAdd = new JButton(addIcon);
		ImageIcon editIcon = new ImageIcon(getClass().getResource("/slike/edit.gif"));
		this.btnEdit = new JButton(editIcon);
		ImageIcon deleteIcon = new ImageIcon(getClass().getResource("/slike/remove.gif"));
		this.btnDelete = new JButton(deleteIcon);
		mainToolBar.add(btnAdd);
		mainToolBar.add(btnEdit);
		mainToolBar.add(btnDelete);
		add(mainToolBar, BorderLayout.NORTH);
		
		String[] zaglavlje = new String[] {"Ime na sr." , "Ime", "Godina izdavanja", "Ime i Prezime rez", "Trajanje"};
		Object[][] sadrzaj = new Object[this.videoteka.filmoviZaPrikaz().size()][zaglavlje.length];
		for (int i = 0; i< this.videoteka.filmoviZaPrikaz().size(); i++){
			Film film = this.videoteka.filmoviZaPrikaz().get(i);
			sadrzaj[i][0] = film.getNaslovNaSrpskom();
			sadrzaj[i][1] = film.getOrginalNaslov();
			sadrzaj[i][2] = film.getGodinaIzdavanja();
			sadrzaj[i][3] = film.getImeIPrezimeRez();
			sadrzaj[i][4] = film.getTrajanje();
		}
		
		DefaultTableModel model = new DefaultTableModel(sadrzaj, zaglavlje);
		this.table = new JTable(model);
		this.table.setColumnSelectionAllowed(false);
		this.table.setRowSelectionAllowed(true);
		this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.table.setDefaultEditor(Object.class, null);
		
		this.tableScroll = new JScrollPane(table);
		add(this.table, BorderLayout.CENTER);
	}
	
	public JTable table(){
		return table;
	}
	
	
	private void initActions(){
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent i){
				if (i.getClickCount() == 2){
					int red = table.getSelectedRow();
					String ime = (String)table.getValueAt(red, 0);
					String imeRezisera = (String)table.getValueAt(red, 3);
					Film film = videoteka.nadjiFilm(ime, imeRezisera);
					if (film != null){
						guiFilmOpis fo = new guiFilmOpis(videoteka, film);
						fo.setVisible(true);
					}else{
						JOptionPane.showMessageDialog(null, "Ne moze da se nadje film");
					}
					
				}
				
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				FilmoviFZDII f = new FilmoviFZDII(videoteka, null, FilmForme.this);
				f.setVisible(true);
				
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = table.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, 
							"Morate odabrati film iz tabele");
				}else {
					String naslov = (String)table.getValueAt(red, 0);
					String imeIPrezimeRez = (String)table.getValueAt(red, 3);
					Film film = videoteka.nadjiFilm(naslov, imeIPrezimeRez);
					if(film != null) {
						FilmoviFZDII f = new FilmoviFZDII(videoteka, film, FilmForme.this);
						f.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, 
								"Greska prilikom pronalazenja filma");
					}
				}
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni?", "Potvrda brisanja",
						JOptionPane.YES_NO_OPTION);
				if(izbor == JOptionPane.YES_OPTION){
					int selektovaniRed = table.getSelectedRow();
					if (selektovaniRed == -1){
						JOptionPane.showMessageDialog(null, "Morate odabrati film");
					}else{
						String ime = (String)table.getValueAt(selektovaniRed, 0);
						String imeIPrezimeRez = (String)table.getValueAt(selektovaniRed, 3);
						Film film = videoteka.nadjiFilm(ime, imeIPrezimeRez);
						if (film != null){
							DefaultTableModel model = (DefaultTableModel)table.getModel();
							model.removeRow(selektovaniRed);
							videoteka.izbrisiFilm(ime, imeIPrezimeRez);
							videoteka.snimiFilmove();
							
						}else{
							JOptionPane.showMessageDialog(null, "Greska prilikom pronalazenja filma");
						}
					}
				}
				
			}
		});

	}

}
