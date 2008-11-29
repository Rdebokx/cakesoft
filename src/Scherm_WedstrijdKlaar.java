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
	
	//Inhoud van het Inlogscherm//
	private JLabel deelnemers = new JLabel("Deelnemers");
	private JLabel locatie = new JLabel("Locatie");
	private JLabel datum = new JLabel("Datum");	
	private JLabel winnaar = new JLabel("Winnaar");
	private JLabel baksel = new JLabel("Baksel:");
	private JLabel naam = new JLabel("Naam - De chocoladetaart");
	private JLabel categorie = new JLabel("Categorie - Taarten");
	private JLabel prijs = new JLabel("Prijs - 10 euro");	
	private JLabel ingredienten = new JLabel("Ingredienten");
	private JLabel recept = new JLabel("Recept");
	//beoordeel-labels
	private JLabel beoordeling = new JLabel("Beoordeling:");
	private JLabel kwaliteit = new JLabel("Kwaliteit");
	private JLabel kwaliteit_punt = new JLabel(".../10");
	private JLabel kosten = new JLabel("Prijshoogte");
	private JLabel kosten_punt = new JLabel(".../10");
	private JLabel smaak = new JLabel("Smaak");
	private JLabel smaak_punt = new JLabel(".../10");
	private JLabel calorieen = new JLabel("Caloriegehalte");
	private JLabel calorieen_punt = new JLabel(".../10");
	private JLabel commentaar = new JLabel("Commentaar");	
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
	private JButton reactie_knop = new JButton("Bekijk Reacties");
	private JButton bestel_knop = new JButton("Bestel");
	private JLabel bestellen = new JLabel("Bestel een aantal baksels:");
	private JTextField bestellen_veld = new JTextField();
	private JPanel baksel_paneel = new JPanel(null);
	private JComboBox jurylid_drop = new JComboBox();
		
	public Scherm_WedstrijdKlaar(ProgrammaController programmaC, Wedstrijd wedstrijd)
	{
		this.programmaC=programmaC;
		this.wedstrijd=wedstrijd;
		
		this.datum.setText("Datum: "+this.wedstrijd.getDatumString());
		this.locatie.setText("Locatie: "+this.wedstrijd.getLocatie());
		
		if(this.wedstrijd.getWinnaar()!=null)
			this.winnaar.setText("Winnaar: "+this.wedstrijd.getWinnaar().getNaam());
		else
			this.winnaar.setText("Winnaar onbekend");
		
		jurylid_drop.addItem("Gemiddeld");
		jurylid_drop.addItem("Jury1");
		jurylid_drop.addItem("Jury2");
		jurylid_drop.addItem("Jury3");		
		
		commentaar_tekst.setEditable(false);
		commentaar_tekst.setText("Smaakt goed. Ik ben alleen allergisch voor meuk. Jeuk, bulten, jeweetwel.");
		commentaar_scroll.setViewportView(commentaar_tekst);
		
  		//basis-instellingen scherm
		setTitle("CakeSoft - Wedstrijd");
		setSize(750,500);
		setResizable(false);
		setLocationRelativeTo(null); //centrering
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		//alles aan container positioneren
		datum.setBounds(30,30,300,20);
		locatie.setBounds(30,50,300,20);
		winnaar.setBounds(30,80,300,20);
				
		deelnemers.setBounds(30,140,200,20);
		deelnemers_scroll.setBounds(30,160,200,120);
		bekijkDeelnemer_knop.setBounds(30,280,200,20);

	//Baksel-paneel		
		baksel_paneel.setBounds(275,30,this.getWidth()-275,this.getHeight()-30);
		baksel.setBounds(0,0,250,20);
		naam.setBounds(0,30,250,20);
		categorie.setBounds(0,50,250,20);
		prijs.setBounds(0,70,250,20);
		bestellen.setBounds(210,30,200,20);
		bestellen_veld.setBounds(210,60,100,25);
		bestel_knop.setBounds(315,60,75,25);
		
		beoordeling.setBounds(0,110,80,20);
		jurylid_drop.setBounds(80,110,100,20);
		kwaliteit.setBounds(0,140,175,20);
		kwaliteit_punt.setBounds(100,140,60,20);
		kosten.setBounds(0,160,175,20);
		kosten_punt.setBounds(100,160,60,20);
		calorieen.setBounds(0,180,175,20);
		calorieen_punt.setBounds(100,180,60,20);
		smaak.setBounds(0,200,175,20);
		smaak_punt.setBounds(100,200,60,20);
		commentaar.setBounds(210,110,200,20);
		commentaar_scroll.setBounds(210,130,200,100);
		
		ingredienten.setBounds(0,245,200,20);
		ingredienten_scroll.setBounds(0,265,200,100);
		recept.setBounds(210,245,200,20);
		recept_scroll.setBounds(210,265,200,100);

		reactie_knop.setBounds(255,385,150,40);
		
		baksel_paneel.setVisible(false);
	//
		
		terug_knop.setBounds(30,430,75,25);
		
		
		//aan het frame toevoegen
		add(deelnemers_scroll);
		add(deelnemers);
		add(bekijkDeelnemer_knop);
		
		add(datum);		
		add(locatie);
		add(winnaar);
		
		baksel_paneel.add(baksel);
		baksel_paneel.add(naam);
		baksel_paneel.add(categorie);
		baksel_paneel.add(prijs);
		baksel_paneel.add(bestellen);
		baksel_paneel.add(bestellen_veld);
		baksel_paneel.add(bestel_knop);
		baksel_paneel.add(ingredienten);
		baksel_paneel.add(ingredienten_scroll);
		baksel_paneel.add(recept);
		baksel_paneel.add(recept_scroll);
		baksel_paneel.add(beoordeling);
		baksel_paneel.add(jurylid_drop);
		baksel_paneel.add(kwaliteit);
		baksel_paneel.add(kwaliteit_punt);
		baksel_paneel.add(kosten);
		baksel_paneel.add(kosten_punt);
		baksel_paneel.add(calorieen);
		baksel_paneel.add(calorieen_punt);
		baksel_paneel.add(smaak);
		baksel_paneel.add(smaak_punt);
		baksel_paneel.add(commentaar);
		baksel_paneel.add(commentaar_scroll);
		baksel_paneel.add(reactie_knop);
		add(baksel_paneel);
		
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
		
		this.ingredienten_tekst.setEditable(false);
		this.ingredienten_tekst.setText(deelnemer.getBaksel().getIngredienten());
		this.ingredienten_scroll.setViewportView(ingredienten_tekst);

		this.recept_tekst.setEditable(false);
		this.recept_tekst.setText(deelnemer.getBaksel().getRecept());
		this.recept_scroll.setViewportView(recept_tekst);

		this.baksel_paneel.setVisible(true);
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