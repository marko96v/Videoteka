package gui.FormeZaDodavanjeIIzmenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import film.Film;
import film.Zanr;
import gui.FormeZaPrikaz.ZanroviForme;
import net.miginfocom.swing.MigLayout;
import videoteka.Videoteka;

public class ZanroviFZDII extends JDialog {
	
	private JLabel lblOznaka;
	private JTextField txtOznaka;
	private JLabel lblNaziv;
	private JTextField txtNaziv;
	private JButton btnOk;
	private JButton btnCancel;
	
	private Videoteka videoteka;
	private Zanr zanr;
	private ZanroviForme zanroviForme;
	
	public ZanroviFZDII(Videoteka videoteka, Zanr zanr, ZanroviForme zanroviForme){
		
		this.videoteka = videoteka;
		this.zanr = zanr;
		this.zanroviForme = zanroviForme;
		setTitle("Zanr - Dodavanje");
		if (zanr != null){
			setTitle("Zanr - Izmena");
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
		MigLayout layout = new MigLayout("wrap 2", "[][]", "[][][]");
		setLayout(layout);
		
		this.lblOznaka = new JLabel("Oznaka");
		this.txtOznaka = new JTextField(5);
		this.txtOznaka.setEditable(false);
		this.lblNaziv = new JLabel("Naziv");
		this.txtNaziv = new JTextField(20);
		this.btnCancel = new JButton("Cancel");
		this.btnOk = new JButton("Ok");
		
		if(zanr != null){
			initValues();
		}else{
			txtOznaka.setText(videoteka.novaOznakaZanra());
		}
		
		add(lblOznaka);
		add(txtOznaka);
		add(lblNaziv);
		add(txtNaziv);
		add(new JLabel());
		add(btnOk, "split 2");
		add(btnCancel);
		
	}
	
	private void initActions(){
		
		btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String oznaka = txtOznaka.getText().trim();
				String naziv = txtNaziv.getText().trim();
				if(oznaka.equals("") && naziv.equals("")){
					JOptionPane.showMessageDialog(null, "Niste uneli sve podatke");
				}else{
					DefaultTableModel model = (DefaultTableModel)ZanroviFZDII.this.zanroviForme.table().getModel();
					if(zanr == null){
						zanr = new Zanr(oznaka, naziv, true);
						ZanroviFZDII.this.videoteka.getZanrovi().add(zanr);
						model.addRow(new Object[] {oznaka, naziv});
						ZanroviFZDII.this.videoteka.snimiZanrove();
					}else{
						zanr.setOznakaZanra(oznaka);
						zanr.setNazivZanra(naziv);
						zanr.setStatus(true);
						int red = ZanroviFZDII.this.zanroviForme.table().getSelectedRow();
						ZanroviFZDII.this.zanroviForme.table().setValueAt(oznaka, red, 0);
						ZanroviFZDII.this.zanroviForme.table().setValueAt(naziv, red, 1);
						ZanroviFZDII.this.videoteka.snimiZanrove();
						
					}
					ZanroviFZDII.this.setVisible(false);
					ZanroviFZDII.this.dispose();
				}
				
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ZanroviFZDII.this.setVisible(false);
				ZanroviFZDII.this.dispose();
				
			}
		});
		
	}
	
	private void initValues(){
		
		txtOznaka.setText(zanr.getOznakaZanra());
		txtNaziv.setText(zanr.getNazivZanra());
	}

}
