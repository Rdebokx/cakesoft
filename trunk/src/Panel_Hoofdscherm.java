import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class Panel_Hoofdscherm extends JPanel
{
	//Eclipse-fix
	private static final long serialVersionUID = 1L;
	//
	
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

		
	public Panel_Hoofdscherm(boolean hoofdbeheer)
	{
		//this.programmaC=programmaC;
		this.hoofdbeheer=hoofdbeheer;
		
  		//basis-instellingen scherm
		/*setTitle("CakeSoft - Hoofdscherm");
		setSize(750,450);
		setResizable(false);
		setLocationRelativeTo(null); //centrering
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
		setLayout(null);
		
		//alles aan container positioneren
		wedstrijd.setBounds(30,30,300,20);
		wedstrijd_scroll.setBounds(30,50,300,220);
		bekijkWedstrijd_knop.setBounds(30,270,150,35);
		if(this.hoofdbeheer)
			nieuwWedstrijd_knop.setBounds(180,270,150,35);
		
		ontvangen.setBounds(400,30,300,20);
		ontvangen_scroll.setBounds(400,50,300,150);
		verwijderBestelling_knop.setBounds(400,200,300,35);
		
		besteld.setBounds(400,250,300,20);
		besteld_scroll.setBounds(400,270,300,150);
		
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
		
		/*bekijkWedstrijd_knop.addActionListener(this);
		if(this.hoofdbeheer)
			nieuwWedstrijd_knop.addActionListener(this);
		verwijderBestelling_knop.addActionListener(this);
		loguit_knop.addActionListener(this);
		*/
		
		setVisible(true);
	}
	
	public Wedstrijd getGeselecteerdeWedstrijd()
	{
		int geselecteerd=this.wedstrijd_lijst.getSelectedIndex();
		
		if(geselecteerd<0)
			return null;
		else
			return this.WedstrijdLijst.get(geselecteerd);
	}
	
	public Bestelling getGeselecteerdeInBestelling()
	{
		int geselecteerd=this.ontvangen_lijst.getSelectedIndex();
		
		if(geselecteerd<0)
			return null;
		else
			return this.InBestellingLijst.get(geselecteerd);
	}
		
	/*public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == this.bekijkWedstrijd_knop)
		{
			programmaC.actieBekijkWedstrijd();
		}
		else if(e.getSource() == this.nieuwWedstrijd_knop && this.hoofdbeheer)
		{
			programmaC.actieNieuwWedstrijd();
		}
		else if(e.getSource() == this.verwijderBestelling_knop)
		{
			programmaC.actieVerwijderBestelling();
		}
		else if(e.getSource() == this.loguit_knop)
		{
			programmaC.actieLoguit();
		}		
	}*/
	
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
	
	
	public JButton getBekijkWedstrijd_knop()
	{
		return this.bekijkWedstrijd_knop;
	}
	
	public JButton getNieuwWedstrijd_knop()
	{
		return this.nieuwWedstrijd_knop;
	}
	
	public JButton getVerwijderBestelling_knop()
	{
		return this.verwijderBestelling_knop;
	}
	
	public JButton getLoguit_knop()
	{
		return this.loguit_knop;
	}

}