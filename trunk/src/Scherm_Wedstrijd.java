import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class Scherm_Wedstrijd extends JFrame implements ActionListener
{
	//Eclipse-fix
	private static final long serialVersionUID = 1L;
	//
	
	private ProgrammaController programmaC;
	//Inhoud van het Inlogscherm
	private JLabel deelnemers = new JLabel("Deelnemers");
	private JLabel locatie = new JLabel("Locatie");
	private JLabel datum = new JLabel("Datum");	
	private JLabel baksel = new JLabel("Baksel:");
	private JLabel naam = new JLabel("Naam - De chocoladetaart");
	private JLabel categorie = new JLabel("Categorie - Taarten");
	private JLabel prijs = new JLabel("Prijs - 10 euro");
	private JLabel ingredienten = new JLabel("Ingredienten");
	private JLabel recept = new JLabel("Recept");
	private JLabel bestellen = new JLabel("Aantal gebak bestellen:");
	private JScrollPane ingredienten_scroll = new JScrollPane();
	private JScrollPane recept_scroll = new JScrollPane();
	private JTextPane ingredienten_tekst = new JTextPane();
	private JTextPane recept_tekst = new JTextPane();
	private JScrollPane deelnemers_scroll = new JScrollPane();
	private JList deelnemers_lijst = new JList();
	private String[] deelnemers_items;
	private JButton bekijkDeelnemer_knop = new JButton("Bekijk Deelnemer");
	private JButton nieuwWedstrijd_knop = new JButton("Nieuwe wedstrijd");
	private JButton inschrijven_knop = new JButton("Schrijf in");
	private JButton terug_knop = new JButton("Terug");
	private JButton bestel_knop = new JButton("Bestel dit");
	private JTextField bestellen_veld = new JTextField();
	
		
	public Scherm_Wedstrijd(ProgrammaController programmaC)
	{
		this.programmaC=programmaC;
		
		this.deelnemers_items=new String[3];
		this.deelnemers_items[0]="deelnemer1";
		this.deelnemers_items[1]="deelnemer2";
		this.deelnemers_items[2]="deelnemer3";
		this.deelnemers_lijst.setListData(this.deelnemers_items);
		this.deelnemers_scroll.setViewportView(this.deelnemers_lijst);
		
		ingredienten_tekst.setEditable(false);
		ingredienten_tekst.setText("Slagroom\n Chocolade\n Stukjes pinda's");
		ingredienten_scroll.setViewportView(ingredienten_tekst);

		recept_tekst.setEditable(false);
		recept_tekst.setText("Meng de slagroom met de chocolade. Gooi er vervolgens de stukjes pinda in.");
		recept_scroll.setViewportView(recept_tekst);

  		//basis-instellingen scherm
		setTitle("CakeSoft - Wedstrijd");
		setSize(750,450);
		setResizable(false);
		setLocationRelativeTo(null); //centrering
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		//alles aan container positioneren		
		datum.setBounds(30,30,300,20);
		locatie.setBounds(30,60,300,20);

		deelnemers.setBounds(30,120,200,20);
		deelnemers_scroll.setBounds(30,140,200,120);
		bekijkDeelnemer_knop.setBounds(30,260,200,20);

		inschrijven_knop.setBounds(500,30,200,50);

		baksel.setBounds(275,120,300,20);
		naam.setBounds(275,150,300,20);
		categorie.setBounds(275,170,300,20);
		prijs.setBounds(275,190,300,20);

		bestellen.setBounds(485,120,200,20);
		bestellen_veld.setBounds(485,150,100,25);
		bestel_knop.setBounds(485,175,100,25);

		ingredienten.setBounds(275,260,200,20);
		ingredienten_scroll.setBounds(275,280,200,100);
		recept.setBounds(485,260,200,20);
		recept_scroll.setBounds(485,280,200,100);
						
		terug_knop.setBounds(30,380,75,25);
		
		//aan het frame toevoegen
		add(deelnemers_scroll);
		add(deelnemers);
		add(bekijkDeelnemer_knop);
		add(nieuwWedstrijd_knop);

		add(datum);		
		add(locatie);
		
		add(baksel);
		add(naam);
		add(categorie);
		add(prijs);
		add(bestellen);
		add(bestellen_veld);
		add(bestel_knop);
		add(ingredienten);
		add(ingredienten_scroll);
		add(recept);
		add(recept_scroll);
		
		add(inschrijven_knop);
		
		add(terug_knop);
		
		//scherm-object luistert naar de events
		bekijkDeelnemer_knop.addActionListener(this);
		inschrijven_knop.addActionListener(this);
		terug_knop.addActionListener(this);
		
		setVisible(true);
	}
		
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == this.bekijkDeelnemer_knop)
		{
			programmaC.actieBekijkDeelnemer();
		}
		else if(e.getSource() == this.inschrijven_knop)
		{
			programmaC.actieInschrijven();
		}
		else if(e.getSource() == this.terug_knop)
		{
			programmaC.actieTerug();
		}		
	}
	
}