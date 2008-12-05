import javax.swing.*;
import java.util.*;

public class Panel_Hoofdscherm extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	//Inhoud van het Inlogscherm
	private JLabel wedstrijd = new JLabel("Wedstrijden");
	private JLabel ontvangen = new JLabel("Ontvangen Bestellingen");
	private JLabel besteld = new JLabel("Geplaatste Bestellingen");	
	private JScrollPane wedstrijd_scroll = new JScrollPane();
	private JScrollPane ontvangen_scroll = new JScrollPane();	
	private JScrollPane besteld_scroll = new JScrollPane();	
	private JList wedstrijd_lijst = new JList();
	private JList ontvangen_lijst = new JList();
	private JList besteld_lijst = new JList();
	private String[] wedstrijd_items;
	private String[] ontvangen_items;
	private String[] besteld_items;	
	private JButton bekijkWedstrijd_knop = new IconButton("_Icons/charts.png", "Bekijk wedstrijd").show();
	private JButton nieuwWedstrijd_knop = new IconButton("_Icons/add.png", "Nieuwe wedstrijd").show();
	private JButton verwijderBestelling_knop = new IconButton("_Icons/close.png", "Verwijder bestelling").show();
	private JButton loguit_knop = new IconButton("_Icons/key.png", "Loguit").show();
	
	private ArrayList<Wedstrijd> WedstrijdLijst;
	private ArrayList<Bestelling> InBestellingLijst;
	private ArrayList<Bestelling> UitBestellingLijst;
	
	private boolean hoofdbeheer;

		
	/**
	 * Constructor van Panel_Hoofdscherm, neemt de waarde mbt hoofdbeheer over, stelt alle objecten vervolgens in
	 *  en indien nodig worden beheeropties weergegeven.
	 * @param hoofdbeheer	Boolean, wel of geen beheerder
	 */
	public Panel_Hoofdscherm(boolean hoofdbeheer)
	{
		this.hoofdbeheer=hoofdbeheer;
		
  		//basis-instellingen scherm
		setLayout(null);
		
		//alles aan container positioneren
		wedstrijd.setBounds(30,30,300,20);
		wedstrijd_scroll.setBounds(30,50,300,220);
		
		bekijkWedstrijd_knop.setBounds(30,275,145,35);
		
		if(this.hoofdbeheer)
			nieuwWedstrijd_knop.setBounds(185,275,145,35);
		
		ontvangen.setBounds(400,30,300,20);
		ontvangen_scroll.setBounds(400,50,300,150);
		verwijderBestelling_knop.setBounds(400,205,300,35);
		
		besteld.setBounds(400,260,300,20);
		besteld_scroll.setBounds(400,280,300,150);
		
		loguit_knop.setBounds(30,430,75,35);
		
		//aan het frame toevoegen
		add(wedstrijd_scroll);
		add(wedstrijd);
		add(bekijkWedstrijd_knop);
		if(this.hoofdbeheer)
			add(nieuwWedstrijd_knop);
		
		add(ontvangen);
		add(ontvangen_scroll);
		add(verwijderBestelling_knop);
		
		add(besteld);
		add(besteld_scroll);
		
		add(loguit_knop);
		
		//scherm-object luistert naar de events
		
		setVisible(true);
	}
	
	/**
	 * Geeft de geselecteerde Wedstrijd terug.
	 * @return	Geeft de geselecteerde wedstrijd terug.
	 */
	public Wedstrijd getGeselecteerdeWedstrijd()
	{
		int geselecteerd=this.wedstrijd_lijst.getSelectedIndex();
		
		if(geselecteerd<0)
			return null;
		else
			return this.WedstrijdLijst.get(geselecteerd);
	}
	
	/**
	 * Geeft de geselecteerde bestelling terug.
	 * @return	Geeft de geselecteerde bestelling terug.
	 */
	public Bestelling getGeselecteerdeInBestelling()
	{
		int geselecteerd=this.ontvangen_lijst.getSelectedIndex();
		
		if(geselecteerd<0)
			return null;
		else
			return this.InBestellingLijst.get(geselecteerd);
	}
	
	/**
	 * Stelt de wedstrijden in aan de hand van de gegeven arrayList.
	 * @param WedstrijdLijst	De in te stellen Wedstrijden in ArrayList.
	 */
	public void setWedstrijden(ArrayList<Wedstrijd> WedstrijdLijst)
	{
		this.WedstrijdLijst = WedstrijdLijst;
		
		this.wedstrijd_items=new String[this.WedstrijdLijst.size()];
		for(int i=0;i < this.WedstrijdLijst.size();i++)
		{
			this.wedstrijd_items[i]= this.WedstrijdLijst.get(i).toString();
		}
		this.wedstrijd_lijst.setListData(this.wedstrijd_items);
		this.wedstrijd_scroll.setViewportView(this.wedstrijd_lijst);		
	}
	
	/**
	 * Stelt de gegeven bestellingen in.
	 * @param InBestellingLijst	De in te stellen bestellingen in een ArrayList.
	 */
	public void setBestellingInkomend(ArrayList<Bestelling> InBestellingLijst)
	{
		this.InBestellingLijst = InBestellingLijst;
		
		this.ontvangen_items=new String[this.InBestellingLijst.size()];
		for(int i=0;i < this.InBestellingLijst.size();i++)
		{
			this.ontvangen_items[i]= this.InBestellingLijst.get(i).toStringInkomend();
		}
		this.ontvangen_lijst.setListData(this.ontvangen_items);
		this.ontvangen_scroll.setViewportView(this.ontvangen_lijst);	
	}
	
	/**
	 * Stelt de gegeven uitgaande bestellingen in.
	 * @param UitBestellingLijst	De in te stellen uitgaande bestellingen in ArrayList.
	 */
	public void setBestellingUitgaand(ArrayList<Bestelling> UitBestellingLijst)
	{
		this.UitBestellingLijst = UitBestellingLijst;
		
		this.besteld_items=new String[this.UitBestellingLijst.size()];
		for(int i=0;i < this.UitBestellingLijst.size();i++)
		{
			this.besteld_items[i]= this.UitBestellingLijst.get(i).toStringUitgaand();
		}
		this.besteld_lijst.setListData(this.besteld_items);
		this.besteld_scroll.setViewportView(this.besteld_lijst);
	}
	
	
	/**
	 * @return	BekijkWedstrijd_knop.
	 */
	public JButton getBekijkWedstrijd_knop()
	{
		return this.bekijkWedstrijd_knop;
	}
	
	/**
	 * @return	getNieuwWedstrijd_knop
	 */
	public JButton getNieuwWedstrijd_knop()
	{
		return this.nieuwWedstrijd_knop;
	}
	
	/**
	 * @return	verwijderBestelling_knop
	 */
	public JButton getVerwijderBestelling_knop()
	{
		return this.verwijderBestelling_knop;
	}
	
	/**
	 * @return	loguit_knop
	 */
	public JButton getLoguit_knop()
	{
		return this.loguit_knop;
	}

}