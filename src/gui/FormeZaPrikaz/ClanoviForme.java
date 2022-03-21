package gui.FormeZaPrikaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import gui.FormeZaDodavanjeIIzmenu.ClanoviFZDII;
import gui.FormeZaDodavanjeIIzmenu.ZaposleniFZDII;
import osobe.Clan;
import osobe.Zaposlen;
import videoteka.Videoteka;

public class ClanoviForme extends JFrame{
	
	private JToolBar mainToolBar;
	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnDelete;
	private JScrollPane tableScroll;
	private JTable table;
	
	private Videoteka videoteka;
	
	public ClanoviForme(Videoteka videoteka){
		
		this.videoteka = videoteka;
		setTitle("Clanovi");
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
		
		String[] zaglavlje = new String[] {"Ime", "Prezime", "JMBG", "Adresa", "Pol", "Br. cl. karte", "Status"};
		Object[][] sadrzaj = new Object[this.videoteka.clanoviZaPrikaz().size()][zaglavlje.length];
		for (int i = 0; i< this.videoteka.clanoviZaPrikaz().size(); i++){
			Clan clanovi = this.videoteka.clanoviZaPrikaz().get(i);
			sadrzaj[i][0] = clanovi.getIme();
			sadrzaj[i][1] = clanovi.getPrezime();
			sadrzaj[i][2] = clanovi.getJMBG();
			sadrzaj[i][3] = clanovi.getAdresa();
			sadrzaj[i][4] = clanovi.getPol();
			sadrzaj[i][5] = clanovi.getBrojClanskeKarte();
			if (clanovi.isAktivan() == true){
				sadrzaj[i][6] = "Aktivan";
			}else{
				sadrzaj[i][6] = "Nije aktivan";
			}
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
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ClanoviFZDII cFZDII = new ClanoviFZDII(videoteka, null, ClanoviForme.this);
				cFZDII.setVisible(true);
				
				
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = table.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, 
							"Morate odabrati clana iz tabele");
				}else {
					String ime = (String)table.getValueAt(red, 0);
					String prezime = (String)table.getValueAt(red, 1);
					Clan clan = videoteka.nadjiClana(ime, prezime);
					if(clan != null) {
						ClanoviFZDII c = new ClanoviFZDII(videoteka, clan, ClanoviForme.this);
						c.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, 
								"Greska prilikom pronalazenja clana");
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
						JOptionPane.showMessageDialog(null, "Morate odabrati clana");
					}else{
						String ime = (String)table.getValueAt(selektovaniRed, 0);
						String prezime = (String)table.getValueAt(selektovaniRed, 1);
						Clan clan = videoteka.nadjiClana(ime, prezime);
						if (clan != null){
							DefaultTableModel model = (DefaultTableModel)table.getModel();
							model.removeRow(selektovaniRed);
							videoteka.izbrisiClana(ime, prezime);
							videoteka.snimiClanove();
							
						}else{
							JOptionPane.showMessageDialog(null, "Greska prilikom pronalazenja clana");
						}
					}
				}
				
			}
		});
		
		
	}

}
