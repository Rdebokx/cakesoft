import javax.swing.*;

import java.awt.event.*;
import java.text.NumberFormat;
import java.util.ArrayList;

public class Scherm_WedstrijdKlaar extends JFrame implements ActionListener
{
	//Eclipse-fix
	private static final long serialVersionUID = 1L;
	//
	
	private Wedstrijd wedstrijd;
	private ArrayList<Deelnemer> deelnemerLijst;
	private Deelnemer deelnemer;
	
	private ProgrammaController programmaC;
	
	//Inhoud van het Inlogscherm
	private JLabel deelnemers = new JLabel("Deelnemers");
	private JLabel locatie = new JLabel("Locatie");
	private JLabel datum = new JLabel("Datum");	
	private JLabel winnaar = new JLabel("Winnaar");
	private JLabel baksel = new JLabel("Baksel:");
	private JLabel naam = new JLabel("Naam - De chocoladetaart");
	private JLabel categorie = new JLabel("Categorie - Taarten");
	private JLabel prijs = new JLabel("Prijs - 10 euro");	
	private JLabel beoordeling = new JLabel("Beoordeling:");
	private JLabel kwaliteit = new JLabel("Kwaliteit");
	private JLabel kosten = new JLabel("Prijshoogte");
	private JLabel smaak = new JLabel("Smaak");
	private JLabel calorieen = new JLabel("Caloriegehalte");
	private JLabel ingredienten = new JLabel("Ingredienten");
	private JLabel recept = new JLabel("Recept");
	private JScrollPane ingredienten_scroll = new JScrollPane();
	private JScrollPane recept_scroll = new JScrollPane();
	private JScrollPane commentaar_scroll = new JScrollPane();
	private JTextPane ingredienten_tekst = new JTextPane();
	private JTextPane recept_tekst = new JTextPane();
	private JTextPane commentaar_tekst = new JTextPane();
	private JScrollPane deelnemers_scroll = new JScrollPane();
	private JList deelnemers_lijst = new JList();
	private String[] deelnemers_items;
	private JButton bekijkDeelnemer_knop = new JButton("Bekijk Deelnemer");
	private JButton terug_knop = new JButton("Terug");
	private JButton bestel_knop = new JButton("Bestel dit");
	private JLabel bestellen = new JLabel("Aantal gebak bestellen:");
	private JTextField bestellen_veld = new JTextField();
	private JPanel baksel_paneel = new JPanel(null);
	private JPanel bestel_paneel = new JPanel(null);
		
	public Scherm_WedstrijdKlaar(ProgrammaController programmaC, Wedstrijd wedstrijd)
	{
		this.programmaC=programmaC;
		this.wedstrijd=wedstrijd;
		
		this.datum.setText("Datum: "+this.wedstrijd.getDatumString());
		this.locatie.setText("Locatie: "+this.wedstrijd.getLocatie());
		
		ingredienten_tekst.setEditable(false);
		ingredienten_tekst.setText("Slagroom\n Chocolade\n Stukjes pinda's");
		ingredienten_scroll.setViewportView(ingredienten_tekst);

		recept_tekst.setEditable(false);
		recept_tekst.setText("Meng de slagroom met de chocolade. Gooi er vervolgens de stukjes pinda in.");
		recept_scroll.setViewportView(recept_tekst);

		commentaar_tekst.setEditable(false);
		commentaar_tekst.setText("Smaakt goed. Ik ben alleen allergisch voor pinda's.");
		commentaar_scroll.setViewportView(commentaar_tekst);
		
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
		winnaar.setBounds(275,30,200,20);
		
		bestel_paneel.setBounds(485,30,205,55);
		bestellen.setBounds(0,0,200,20);
		bestellen_veld.setBounds(0,30,100,25);
		bestel_knop.setBounds(105,30,100,25);
		bestel_paneel.setVisible(false);
		
		deelnemers.setBounds(30,120,200,20);
		deelnemers_scroll.setBounds(30,140,200,120);
		bekijkDeelnemer_knop.setBounds(30,260,200,20);
		
		baksel_paneel.setBounds(275,120,this.getWidth()-275,this.getHeight()-120);
		baksel.setBounds(0,0,250,20);
		naam.setBounds(0,30,250,20);
		categorie.setBounds(0,50,250,20);
		prijs.setBounds(0,70,250,20);
		ingredienten.setBounds(0,140,200,20);
		ingredienten_scroll.setBounds(0,160,200,100);
		recept.setBounds(210,140,200,20);
		recept_scroll.setBounds(210,160,200,100);
		beoordeling.setBounds(210,0,175,20);
		kwaliteit.setBounds(210,30,175,20);
		kosten.setBounds(210,170,55,20);
		calorieen.setBounds(210,70,175,20);
		smaak.setBounds(210,90,175,20);
		commentaar_scroll.setBounds(210,120,175,50);
		baksel_paneel.setVisible(false);
		
		terug_knop.setBounds(30,380,75,25);
		
		//aan het frame toevoegen
		add(deelnemers_scroll);
		add(deelnemers);
		add(bekijkDeelnemer_knop);
		
		bestel_paneel.add(bestellen);
		bestel_paneel.add(bestellen_veld);
		bestel_paneel.add(bestel_knop);
		add(bestel_paneel);

		add(datum);		
		add(locatie);
		add(winnaar);
		
		baksel_paneel.add(baksel);
		baksel_paneel.add(naam);
		baksel_paneel.add(categorie);
		baksel_paneel.add(prijs);
		baksel_paneel.add(beoordeling);
		baksel_paneel.add(ingredienten);
		baksel_paneel.add(ingredienten_scroll);
		baksel_paneel.add(recept);
		baksel_paneel.add(recept_scroll);
		baksel_paneel.add(kwaliteit);
		baksel_paneel.add(kosten);
		baksel_paneel.add(calorieen);
		baksel_paneel.add(smaak);
		add(baksel_paneel);
//		add(commentaar_scroll);
				
		add(terug_knop);
		
		//scherm-object luistert naar de events
		bekijkDeelnemer_knop.addActionListener(this);
		terug_knop.addActionListener(this);
		bestel_knop.addActionListener(this);
		
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
	
	public Bestelling getBestelling()
	{
		int aantal=-1;
		try
		{
			aantal=Integer.valueOf(this.bestellen_veld.getText());
		}
		catch(Exception e)
		{
			return null;
		}
		
		//maak het aantal veld nu leeg
		this.bestellen_veld.setText("");
		
		Bestelling bestelling=new Bestelling();
		bestelling.setAantal(aantal);
		bestelling.setBaksel(this.deelnemer.getBaksel());
		
		return bestelling;
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
	
	public void setBeoordelingen(ArrayList<Beoordeling> beoordelingen, Beoordeling gemiddelde)
	{
		//
	}
	
	public void toonDeelnemer(Deelnemer deelnemer)
	{
		this.deelnemer=deelnemer;
		this.baksel.setText("Baksel van "+deelnemer.getNaam()+":");
		this.naam.setText("Naam: "+deelnemer.getBaksel().getNaam());
		this.categorie.setText("Categorie: "+deelnemer.getBaksel().getCategorie());
		this.prijs.setText("Prijs: "+NumberFormat.getCurrencyInstance().format(deelnemer.getBaksel().getPrijs()));
		this.ingredienten_tekst.setText(deelnemer.getBaksel().getIngredienten());
		this.recept_tekst.setText(deelnemer.getBaksel().getRecept());
		this.baksel_paneel.setVisible(true);
		this.bestel_paneel.setVisible(true);
	}
		
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == this.bekijkDeelnemer_knop)
		{
			programmaC.actieBekijkDeelnemer();
		}
		if(e.getSource() == this.terug_knop)
		{
			programmaC.actieTerugNaarHoofdscherm();
		}
		if(e.getSource()== this.bestel_knop)
		{
			programmaC.actieBestel();
		}
	}
}