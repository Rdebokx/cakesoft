import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Scherm_Hoofdscherm extends JFrame implements ActionListener
{
	private ProgrammaController programmaC;
	//Inhoud van het Inlogscherm
	private JPanel paneel = new JPanel(new GridLayout(2,1));
	private JPanel paneel2 = new JPanel(new GridLayout(1,1));

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

	private JLabel filler = new JLabel("");
	
	private JTextField lidnr_veld = new JTextField();
	private JTextField pass_veld = new JTextField();
	
	private JButton bekijkWedstrijd_knop = new JButton("Bekijk wedstrijd");
	private JButton nieuwWedstrijd_knop = new JButton("Nieuwe wedstrijd");
	private JButton verwijderBestelling_knop = new JButton("Verwijder bestelling");
	private JButton loguit_knop = new JButton("Loguit");
	
	
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
		setSize(750,400);
		setResizable(false);
		setLocationRelativeTo(null); //centrering
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		//alles aan container positioneren
		wedstrijd.setBounds(50,30,300,20);
		wedstrijd_scroll.setBounds(50,50,300,70);
		bekijkWedstrijd_knop.setBounds(50,120,150,20);
		nieuwWedstrijd_knop.setBounds(200,120,150,20);
		
		ontvangen.setBounds(400,30,300,20);
		ontvangen_scroll.setBounds(400,50,300,70);
		verwijderBestelling_knop.setBounds(400,120,300,20);
		
		besteld.setBounds(400,180,300,20);
		besteld_scroll.setBounds(400,200,300,70);
		
		loguit_knop.setBounds(50,300,75,25);
		
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
		loguit_knop.addActionListener(this);
		
		setVisible(true);
	}
	
	public Lid getLid()
	{
		Lid lid=new Lid();
		int lid_id=0;
		
		try
		{
		}
		catch(Exception e)
		{
			
		}
		
		lid.setLid_id(lid_id);
		lid.setWachtwoord("");
		
		
		return lid;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==this.loginKnop)
			this.programmaC.actieLogin();
	}
}