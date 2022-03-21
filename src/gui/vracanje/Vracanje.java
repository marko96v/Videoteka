package gui.vracanje;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
import film.PrimerciFilma;
import gui.filmOpis.guiFilmOpis;
import iznamljivanje.Iznamljivanje;
import videoteka.Videoteka;

public class Vracanje extends JFrame{
	
	private JScrollPane tableScroll;
	private JTable table;
	
	private Videoteka videoteka;
	
	public Vracanje(Videoteka videoteka){
		
		this.videoteka = videoteka;
		setTitle("Vracanje filmova");
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGui();
		initActions();
	}
	
	private void initGui(){
		
		String[] zaglavlje = new String[] {"Sifra", "Ime i prezime", "Datum izdavanja", "Datum vracanja"};
		Object[][] sadrzaj = new Object[this.videoteka.iznamljivanjeZaPrikaz().size()][zaglavlje.length];
		for (int i = 0; i< this.videoteka.iznamljivanjeZaPrikaz().size(); i++){
			Iznamljivanje a = this.videoteka.iznamljivanjeZaPrikaz().get(i);
			sadrzaj[i][0] = a.getSifra();
			sadrzaj[i][1] = a.getClanImeIPrezime();
			sadrzaj[i][2] = a.getDatumIzdavanja();
			sadrzaj[i][3] = a.getDatumVracanja();
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
	
	private void initActions(){
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent i){
				if (i.getClickCount() == 2){
					int red = table.getSelectedRow();
					String oznaka = (String)table.getValueAt(red, 0);
					Iznamljivanje iznamljivanje = videoteka.nadjiIznamljivanje(oznaka);
					if (iznamljivanje != null){
						int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni?", "Potvrda iznamljivanja",JOptionPane.YES_NO_OPTION);
						if(izbor == JOptionPane.YES_OPTION){
							for(PrimerciFilma primerak : iznamljivanje.getFilmovi()){
								Vracanje.this.videoteka.povecajBrojPrimeraka(primerak);
						}
						iznamljivanje.setStatus(false);
						Vracanje.this.videoteka.snimiIznamljivanje();
							
						}
					}else{
						JOptionPane.showMessageDialog(null, "Ne moze da se nadje iznamljivanje");
					}
					
				}
				
			}
		});
		
	}

}
