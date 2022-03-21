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

import film.Film;
import film.Zanr;
import gui.FormeZaDodavanjeIIzmenu.FilmoviFZDII;
import gui.FormeZaDodavanjeIIzmenu.ZanroviFZDII;
import videoteka.Videoteka;

public class ZanroviForme extends JFrame {
	
	private JToolBar mainToolBar;
	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnDelete;
	private JScrollPane tableScroll;
	private JTable table;
	
	private Videoteka videoteka;
	
	public ZanroviForme(Videoteka videoteka){
		
		this.videoteka = videoteka;
		setTitle("Zanr");
		setSize(500, 500);
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
		
		String[] zaglavlje = new String[] {"Oznaka zanra", "Naziv zanra"};
		Object[][] sadrzaj = new Object[this.videoteka.zanroviZaPrikaz().size()][zaglavlje.length];
		for (int i = 0; i< this.videoteka.zanroviZaPrikaz().size(); i++){
			Zanr zanr = this.videoteka.zanroviZaPrikaz().get(i);
			sadrzaj[i][0] = zanr.getOznakaZanra();
			sadrzaj[i][1] = zanr.getNazivZanra();
			
		}
		DefaultTableModel model = new DefaultTableModel(sadrzaj, zaglavlje);
		this.table = new JTable(model);
		this.table.setColumnSelectionAllowed(false);
		this.table.setRowSelectionAllowed(true);
		this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.table.setDefaultEditor(Object.class, null);
		this.tableScroll = new JScrollPane(table);
		this.table.setSize(getPreferredSize());
		add(this.table, BorderLayout.CENTER);
	}
	
	public JTable table(){
		return table;
	}
	
	
	private void initActions(){
		
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ZanroviFZDII z = new ZanroviFZDII(videoteka, null, ZanroviForme.this);
				z.setVisible(true);
				
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int red = table.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, 
							"Morate odabrati zanr iz tabele");
				}else {
					String oznaka = (String)table.getValueAt(red, 0);
					String naziv = (String)table.getValueAt(red, 1);
					Zanr zanr = videoteka.nadjiZanr(oznaka, naziv);
					if(zanr != null) {
						ZanroviFZDII z = new ZanroviFZDII(videoteka,zanr, ZanroviForme.this);
						z.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, 
								"Greska prilikom pronalazenja zanra");
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
						JOptionPane.showMessageDialog(null, "Morate odabrati zanr");
					}else{
						String oznaka = (String)table.getValueAt(selektovaniRed, 0);
						String naziv= (String)table.getValueAt(selektovaniRed, 1);
						Zanr zanr = videoteka.nadjiZanr(oznaka, naziv);
						if (zanr != null){
							DefaultTableModel model = (DefaultTableModel)table.getModel();
							model.removeRow(selektovaniRed);
							videoteka.izbrisiZanr(oznaka, naziv);
							videoteka.snimiZanrove();
							
						}else{
							JOptionPane.showMessageDialog(null, "Greska prilikom pronalazenja zanra");
						}
					}
				}
				
			}
		});
		
	}

}
