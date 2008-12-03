import javax.swing.*;

import java.awt.event.*;
import java.text.NumberFormat;
import java.util.*;

public class Panel_Wedstrijd extends JPanel
{
	//Eclipse-fix
	private static final long serialVersionUID = 1L;
	//
	
	private Wedstrijd wedstrijd;
	private ArrayList<Deelnemer> deelnemerLijst;
	private boolean isJury;
	private boolean isDeelnemer;
	private Deelnemer deelnemer;
	
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
	//private JLabel bestellen = new JLabel("Aantal gebak bestellen:");
	private JScrollPane ingredienten_scroll = new JScrollPane();
	private JScrollPane recept_scroll = new JScrollPane();
	private JTextPane ingredienten_tekst = new JTextPane();
	private JTextPane recept_tekst = new JTextPane();
	private JScrollPane deelnemers_scroll = new JScrollPane();
	private JList deelnemers_lijst = new JList();
	private String[] deelnemers_items;
	private JButton bekijkDeelnemer_knop = new IconButton("_Icons/charts.png","Zie Deelnemer").show();
	private JButton sluitWedstrijd_knop = new IconButton("_Icons/close.png","Sluit Wedstrijd").show();
	private JButton beoordeel_knop = new IconButton("_Icons/star.png","Beoordeel").show();
	private JButton inschrijven_knop = new IconButton("_Icons/download.png","Schrijf in").show();
	private JButton terug_knop = new IconButton("_Icons/arrow_left.png","Terug").show();
	//private JButton bestel_knop = new JButton("Bestel dit");
	//private JTextField bestellen_veld = new JTextField();
	private JPanel baksel_paneel = new JPanel(null);
	private JLabel kan_niet_beoordelen = new JLabel("U hebt dit baksel al beoordeeld");
	
	
		
	public Panel_Wedstrijd( Wedstrijd wedstrijd, boolean isJury, boolean isDeelnemer, boolean toonSluitKnop)
	{
		this.wedstrijd=wedstrijd;
		this.isJury=isJury;
		this.isDeelnemer=isDeelnemer;
		
		this.datum.setText("Datum: "+this.wedstrijd.getDatumString());
		this.locatie.setText("Locatie: "+this.wedstrijd.getLocatie());
		
		ingredienten_tekst.setEditable(false);
		ingredienten_tekst.setText("Slagroom\n Chocolade\n Stukjes pinda's");
		ingredienten_scroll.setViewportView(ingredienten_tekst);

		recept_tekst.setEditable(false);
		recept_tekst.setText("Meng de slagroom met de chocolade. Gooi er vervolgens de stukjes pinda in.");
		recept_scroll.setViewportView(recept_tekst);

  		//basis-instellingen scherm
		/*setTitle("CakeSoft - Wedstrijd");
		setSize(750,450);
		setResizable(false);
		setLocationRelativeTo(null); //centrering
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
		setLayout(null);
		
		//alles aan container positioneren		
		datum.setBounds(30,30,300,20);
		locatie.setBounds(30,60,300,20);
		

		deelnemers.setBounds(30,120,200,20);
		deelnemers_scroll.setBounds(30,140,200,120);
		bekijkDeelnemer_knop.setBounds(30,260,200,35);
		if(toonSluitKnop)
			sluitWedstrijd_knop.setBounds(30,300,200,35);

		if(this.wedstrijd.isInschrijvingOpen() && !this.isJury && !this.isDeelnemer)
			inschrijven_knop.setBounds(500,30,200,50);
		
		baksel_paneel.setBounds(275,120,1000,700);
		baksel.setBounds(0,0,300,20);
		naam.setBounds(0,30,300,20);
		categorie.setBounds(0,50,300,20);
		prijs.setBounds(0,70,300,20);
		ingredienten.setBounds(0,140,200,20);
		ingredienten_scroll.setBounds(0,160,200,100);
		recept.setBounds(210,140,200,20);
		recept_scroll.setBounds(210,160,200,100);
		//TODO goede locatie bedenken voor deze knop
		beoordeel_knop.setBounds(30,100,300,20);
		kan_niet_beoordelen.setBounds(30,100,300,20);//zelfde plek als button, als het kan
		
		baksel_paneel.setVisible(false);
		
		
		//bestellen.setBounds(485,120,200,20);
		//bestellen_veld.setBounds(485,150,100,25);
		//bestel_knop.setBounds(485,175,100,25);
						
		terug_knop.setBounds(30,430,75,35);
		
		//aan het frame toevoegen
		add(deelnemers_scroll);
		add(deelnemers);
		add(bekijkDeelnemer_knop);
		if(toonSluitKnop)
			add(sluitWedstrijd_knop);

		add(datum);		
		add(locatie);
		
		baksel_paneel.add(baksel);
		baksel_paneel.add(naam);
		baksel_paneel.add(categorie);
		baksel_paneel.add(prijs);
		baksel_paneel.add(ingredienten);
		baksel_paneel.add(ingredienten_scroll);
		baksel_paneel.add(recept);
		baksel_paneel.add(recept_scroll);
		if(this.isJury && this.wedstrijd.isBeoordelingOpen())
		{
			baksel_paneel.add(kan_niet_beoordelen);
			baksel_paneel.add(beoordeel_knop);
		}
		add(baksel_paneel);
		
		kan_niet_beoordelen.setVisible(false);
		beoordeel_knop.setVisible(false);
		
		/*add(bestellen);
		add(bestellen_veld);
		add(bestel_knop);*/
		
		if(this.wedstrijd.isInschrijvingOpen() && !this.isJury && !this.isDeelnemer)
			add(inschrijven_knop);
		
		add(terug_knop);
		
		//scherm-object luistert naar de events
		/*bekijkDeelnemer_knop.addActionListener(this);
		if(this.wedstrijd.isInschrijvingOpen() && !this.isJury && !this.isDeelnemer)
			inschrijven_knop.addActionListener(this);
		
		terug_knop.addActionListener(this);
		if(toonSluitKnop)
			sluitWedstrijd_knop.addActionListener(this);
		*/
		
		setVisible(true);
	}
	
	public Deelnemer getGeselecteerdeDeelnemer()
	{
		int geselecteerd=this.deelnemers_lijst.getSelectedIndex();
		if(geselecteerd<0)
			return null;
		else
			return this.deelnemerLijst.get(geselecteerd);
	}
	
	public void setDeelnemers(ArrayList<Deelnemer> deelnemerLijst)
	{
		this.deelnemerLijst=deelnemerLijst;
		
		this.deelnemers_items=new String[this.deelnemerLijst.size()];
		for(int i=0;i < this.deelnemerLijst.size();i++)
		{
			this.deelnemers_items[i]= this.deelnemerLijst.get(i).toString();
		}
		this.deelnemers_lijst.setListData(this.deelnemers_items);
		this.deelnemers_scroll.setViewportView(this.deelnemers_lijst);	
	}
	
	public void toonDeelnemer(Deelnemer deelnemer, boolean heeftBeoordeeld)
	{
		this.deelnemer=deelnemer;
		this.baksel.setText("Baksel van "+deelnemer.getNaam()+":");
		this.naam.setText("Naam: "+deelnemer.getBaksel().getNaam());
		this.categorie.setText("Categorie: "+deelnemer.getBaksel().getCategorie());
		this.prijs.setText("Prijs: "+NumberFormat.getCurrencyInstance().format(deelnemer.getBaksel().getPrijs()));
		this.ingredienten_tekst.setText(deelnemer.getBaksel().getIngredienten());
		this.recept_tekst.setText(deelnemer.getBaksel().getRecept());
		
		this.beoordeel_knop.setVisible(this.isJury && !heeftBeoordeeld);
		this.kan_niet_beoordelen.setVisible(this.isJury && heeftBeoordeeld);
		
		this.baksel_paneel.setVisible(true);
	}
	
	/*
	{
		if(e.getSource() == this.bekijkDeelnemer_knop)
			programmaC.actieBekijkDeelnemer();
		
		if(e.getSource() == this.inschrijven_knop && this.wedstrijd.isInschrijvingOpen() && !this.isJury && !this.isDeelnemer)
			programmaC.actieInschrijven();
		
		if(e.getSource() == this.terug_knop)
			programmaC.actieTerugNaarHoofdscherm();
		
		if(e.getSource()==this.sluitWedstrijd_knop)
			programmaC.actieSluitWedstrijd();
		
	}
	*/
	

	public JButton getBekijkDeelnemer_knop()
	{
		return this.bekijkDeelnemer_knop;
	}
	
	public JButton getSluitWedstrijd_knop()
	{
		return this.sluitWedstrijd_knop;
	}
	
	public JButton getInschrijven_knop()
	{
		return this.inschrijven_knop;
	}
	
	public JButton getTerug_knop()
	{
		return this.terug_knop;
	}
	
	public JButton getBeoordeel_knop()
	{
		return this.beoordeel_knop;
	}
}