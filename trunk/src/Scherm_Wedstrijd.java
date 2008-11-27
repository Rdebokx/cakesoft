import javax.swing.*;
import java.awt.event.*;

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
		deelnemers.setBounds(30,120,300,20);
		deelnemers_scroll.setBounds(30,140,300,120);
		bekijkDeelnemer_knop.setBounds(30,260,300,20);
		
		datum.setBounds(30,30,300,20);
		locatie.setBounds(30,60,300,20);

		baksel.setBounds(375,120,300,20);
		naam.setBounds(375,150,300,20);
		categorie.setBounds(375,170,300,20);
		prijs.setBounds(375,190,300,20);
		ingredienten.setBounds(375,210,250,20);
		ingredienten_scroll.setBounds(375,230,250,60);
		recept.setBounds(375,290,250,20);
		recept_scroll.setBounds(375,310,250,60);
		
		inschrijven_knop.setBounds(500,30,200,50);
				
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