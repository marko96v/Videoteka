package gui.FormeZaPrikaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import gui.FormeZaDodavanjeIIzmenu.ZaposleniFZDII;
import osobe.Zaposlen;
import videoteka.Videoteka;

public class ZaposleniForme extends JFrame {
	
	private JToolBar mainToolBar;
	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnDelete;
	private JScrollPane tableScroll;
	private JTable table;
	
	private Videoteka videoteka;
	
	public ZaposleniForme(Videoteka videoteka){
		
		this.videoteka = videoteka;
		setTitle("Zaposleni");
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
		
		String[] zaglavlje = new String[] {"Ime", "Prezime", "JMBG", "Adresa", "Pol", "Plata", "Korisnicko ime"};
		Object[][] sadrzaj = new Object[this.videoteka.zaposlenZaPrikaz().size()][zaglavlje.length];
		for(int i=0; i<this.videoteka.zaposlenZaPrikaz().size(); i++){
			Zaposlen zaposleni = this.videoteka.zaposlenZaPrikaz().get(i);
			sadrzaj[i][0] =  zaposleni.getIme();
			sadrzaj[i][1] = zaposleni.getPrezime();
			sadrzaj[i][2] =  zaposleni.getJMBG();
			sadrzaj[i][3] = zaposleni.getAdresa();
			sadrzaj[i][4] = zaposleni.getPol();
			sadrzaj[i][5] = zaposleni.getPlata();
			sadrzaj[i][6] = zaposleni.getKorisnickoIme();
			}
		
		DefaultTableModel model = new DefaultTableModel(sadrzaj, zaglavlje);
		table = new JTable(model);
		table.setColumnSelectionAllowed(false);
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setDefaultEditor(Object.class, null);
		
		tableScroll = new JScrollPane(table);
		add(table, BorderLayout.CENTER);
		
		
		
	}
	public JTable table(){
		return table;
	}
	
	private void initActions(){
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni?", "Potvrda brisanja",
						JOptionPane.YES_NO_OPTION);
				if(izbor == JOptionPane.YES_OPTION){
					int selektovaniRed = table.getSelectedRow();
					if (selektovaniRed == -1){
						JOptionPane.showMessageDialog(null, "Morate odabrati zaposlenog");
					}else{
						String korisnickoIme = (String)table.getValueAt(selektovaniRed, 6);
						Zaposlen zaposlen = videoteka.nadjiZaposlenog(korisnickoIme);
						if (zaposlen != null){
							DefaultTableModel model = (DefaultTableModel)table.getModel();
							model.removeRow(selektovaniRed);
							videoteka.izbrisiZaposlenog(korisnickoIme);
							videoteka.snimiZaposlene();
							
						}else{
							JOptionPane.showMessageDialog(null, "Greska prilikom pronalazenja zaposlenog");
						}
					}
				}
				
			}
		});
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ZaposleniFZDII zFZDII = new ZaposleniFZDII(videoteka, null, ZaposleniForme.this);
				zFZDII.setVisible(true);
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = table.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, 
							"Morate odabrati prodavca iz tabele");
				}else {
					String korisnickoIme = (String)table.getValueAt(red, 6);
					Zaposlen zaposlen = videoteka.nadjiZaposlenog(korisnickoIme);
					if(zaposlen != null) {
						ZaposleniFZDII z = new ZaposleniFZDII(videoteka, zaposlen, ZaposleniForme.this);
						z.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, 
								"Greska prilikom pronalazenja prodavca");
					}
				}
				
			}
		});
		
	}

}
