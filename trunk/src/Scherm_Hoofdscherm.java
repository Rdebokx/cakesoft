import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class Scherm_Hoofdscherm extends JFrame implements ActionListener
{
	//Eclipse-fix
	private static final long serialVersionUID = 1L;
	//
	
	private ProgrammaController programmaC;
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
	private JButton bekijkWedstrijd_knop = new JButton("Bekijk wedstrijd");
	private JButton nieuwWedstrijd_knop = new JButton("Nieuwe wedstrijd");
	private JButton verwijderBestelling_knop = new JButton("Verwijder bestelling");
	private JButton loguit_knop = new JButton("Loguit");
	
	private ArrayList<Wedstrijd> WedstrijdLijst;

		
	public Scherm_Hoofdscherm(ProgrammaController programmaC)
	{
		this.programmaC=programmaC;
		
		this.wedstrijd_items=new String[3];
		this.wedstrijd_items[0]="wedstrijd1";
		this.wedstrijd_items[1]="wedstrijd2";
		this.wedstrijd_items[2]="wedstrijd3";
		this.wedstrijd_lijst.setListData(this.wedstrijd_items);
		this.wedstrijd_scroll.setViewportView(this.wedstrijd_lijst);
		this.ontvangen_items=new String[3];
		this.ontvangen_items[0]="ontvangen1";
		this.ontvangen_items[1]="ontvangen2";
		this.ontvangen_items[2]="ontvangen3";
		this.ontvangen_lijst.setListData(this.ontvangen_items);
		this.ontvangen_scroll.setViewportView(this.ontvangen_lijst);
		this.besteld_items=new String[3];
		this.besteld_items[0]="besteld1";
		this.besteld_items[1]="besteld2";
		this.besteld_items[2]="besteld3";
		this.besteld_lijst.setListData(this.besteld_items);
		this.besteld_scroll.setViewportView(this.besteld_lijst);

  		//basis-instellingen scherm
		setTitle("CakeSoft - Hoofdscherm");
		setSize(750,450);
		setResizable(false);
		setLocationRelativeTo(null); //centrering
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		//alles aan container positioneren
		wedstrijd.setBounds(30,30,300,20);
		wedstrijd_scroll.setBounds(30,50,300,175);
		bekijkWedstrijd_knop.setBounds(30,225,150,20);
		nieuwWedstrijd_knop.setBounds(180,225,150,20);
		
		ontvangen.setBounds(400,30,300,20);
		ontvangen_scroll.setBounds(400,50,300,120);
		verwijderBestelling_knop.setBounds(400,170,300,20);
		
		besteld.setBounds(400,220,300,20);
		besteld_scroll.setBounds(400,240,300,120);
		
		loguit_knop.setBounds(30,380,75,25);
		
		//aan het frame toevoegen
		add(wedstrijd_scroll);
		add(wedstrijd);
		add(bekijkWedstrijd_knop);
		add(nieuwWedstrijd_knop);
		
		add(ontvangen);
		add(ontvangen_scroll);
		add(verwijderBestelling_knop);
		
		add(besteld);
		add(besteld_scroll);
		
		add(loguit_knop);
		
		//scherm-object luistert naar de events
		bekijkWedstrijd_knop.addActionListener(this);
		nieuwWedstrijd_knop.addActionListener(this);
		verwijderBestelling_knop.addActionListener(this);
		loguit_knop.addActionListener(this);
		
		setVisible(true);
	}
		
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == this.bekijkWedstrijd_knop)
		{
			programmaC.actieBekijkWedstrijd();
		}
		else if(e.getSource() == this.nieuwWedstrijd_knop)
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
	}
	
	public void setWedstrijden(ArrayList<Wedstrijd> WedstrijdLijst)
	{
			WedstrijdLijst = this.WedstrijdLijst;
			
			this.wedstrijd_items=new String[WedstrijdLijst.size()];
			for(int i=0;i < WedstrijdLijst.size();i++)
			{
				this.wedstrijd_items[i]= WedstrijdLijst.get(i).toString();
			}
			this.wedstrijd_lijst.setListData(this.wedstrijd_items);
			this.wedstrijd_scroll.setViewportView(this.wedstrijd_lijst);		
	}

}