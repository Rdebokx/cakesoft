import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Panel_WedstrijdNieuw extends JPanel
{
	//Eclipse-fix
	private static final long serialVersionUID = 1L;
	//
	
	//Inhoud van het Inlogscherm
	private JPanel paneel = new JPanel(new GridLayout(2,2));
	private JPanel paneel2 = new JPanel(new GridLayout(1,1));
	private JLabel datum = new JLabel("Datum wedstrijd:");
	private JLabel datumBijschrift = new JLabel("(jaar-maand-dag)");
	
	private JLabel locatie = new JLabel("Locatie wedstrijd:");
	private JTextField datum_veld = new JTextField();
	private JTextField locatie_veld = new JTextField();
	private JButton maak_knop = new IconButton("_Icons/ok.png", "Maak een nieuwe wedstrijd").show();
	private JButton terug_knop = new IconButton("_Icons/arrow_left.png","Terug").show();
	
	private JLabel jurylid_1 = new JLabel ("Jurylid 1:");
	private JLabel jurylid_2 = new JLabel ("Jurylid 2:");
	private JLabel jurylid_3 = new JLabel ("Jurylid 3:");
	
	private JTextField juryveld_1 = new JTextField();
	private JTextField juryveld_2 = new JTextField();
	private JTextField juryveld_3 = new JTextField();
	
	
	public Panel_WedstrijdNieuw()
	{
		setLayout(null);
		
		//Stel datum in op huidige.
		datum_veld.setText((new SimpleDateFormat("yyyy-MM-dd")).format(new java.util.Date()));

		add(datumBijschrift);

		datumBijschrift.setBounds(225, 170, 120, 40);
		
		//alles aan panelen toevoegen
		paneel.add(datum);
		paneel.add(datum_veld);
		paneel.add(locatie);
		paneel.add(locatie_veld);
		paneel2.add(maak_knop);
		paneel.setBounds(225,150,300,90);
		paneel2.setBounds(225,240,300,45);
		
		jurylid_1.setBounds(525,30,250,20);
		jurylid_2.setBounds(525,60,250,20);
		jurylid_3.setBounds(525,90,250,20);
		
		juryveld_1.setBounds(600,30,125,25);
		juryveld_2.setBounds(600,60,125,25);
		juryveld_3.setBounds(600,90,125,25);
		
		terug_knop.setBounds(30,430,95,35);

		//aan het frame toevoegen
		add(paneel);
		add(paneel2);
		
		add(jurylid_1);
		add(jurylid_2);
		add(jurylid_3);
		add(juryveld_1);
		add(juryveld_2);
		add(juryveld_3);
		add(terug_knop);
		
		setVisible(true);
	}
	
	public Wedstrijd getWedstrijd()
	{
		Wedstrijd wedstrijd=null;
		Date datum=null;
		
		try
		{
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			datum=format.parse(this.datum_veld.getText());
		}
		catch(Exception e)
		{
			//
		}
		if(datum==null)
		{
			new Scherm_foutmelding("U hebt geen geldige datum ingevuld.");
			return null;
		}
		if(this.locatie_veld.getText().equals(""))
		{
			new Scherm_foutmelding("U hebt geen locatie ingevuld.");
			return null;
		}
		
		wedstrijd=new Wedstrijd();
		wedstrijd.setLocatie(this.locatie_veld.getText());
		wedstrijd.setDatum(datum);
		
		return wedstrijd;
	}
	
	public String getJuryNaam(int nummer)
	{
		if(nummer==1)
			return this.juryveld_1.getText();
		if(nummer==2)
			return this.juryveld_2.getText();
		if(nummer==3)
			return this.juryveld_3.getText();
		
		return null;
	}
		
	/*public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==this.maak_knop)
			this.programmaC.actieMaakWedstrijd();
		if(e.getSource()==this.terug_knop)
			this.programmaC.actieTerugNaarHoofdscherm();
	}*/
	
	public JButton getMaak_knop()
	{
		return this.maak_knop;
	}
	
	public JButton getTerug_knop()
	{
		return this.terug_knop;
	}
}