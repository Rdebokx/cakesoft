import javax.swing.*;

import java.awt.event.*;

public class Scherm_WedstrijdKlaar extends JFrame implements ActionListener
{
	//Eclipse-fix
	private static final long serialVersionUID = 1L;
	//
	
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
		
	public Scherm_WedstrijdKlaar(ProgrammaController programmaC)
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

		bestellen.setBounds(485,30,200,20);
		bestellen_veld.setBounds(485,60,100,25);
		bestel_knop.setBounds(590,60,100,25);
		
		deelnemers.setBounds(30,120,200,20);
		deelnemers_scroll.setBounds(30,140,200,120);
		bekijkDeelnemer_knop.setBounds(30,260,200,20);
		
		baksel.setBounds(275,120,250,20);
		naam.setBounds(275,150,250,20);
		categorie.setBounds(275,170,250,20);
		prijs.setBounds(275,190,250,20);
		ingredienten.setBounds(275,260,200,20);
		ingredienten_scroll.setBounds(275,280,200,100);
		recept.setBounds(485,260,200,20);
		recept_scroll.setBounds(485,280,200,100);
		
		beoordeling.setBounds(485,120,175,20);
		kwaliteit.setBounds(485,150,175,20);
		kosten.setBounds(485,170,175,20);
		calorieen.setBounds(485,190,175,20);
		smaak.setBounds(485,210,175,20);
		commentaar_scroll.setBounds(485,240,175,50);
						
		terug_knop.setBounds(30,380,75,25);
		
		//aan het frame toevoegen
		add(deelnemers_scroll);
		add(deelnemers);
		add(bekijkDeelnemer_knop);
		
		add(bestellen);
		add(bestellen_veld);
		add(bestel_knop);

		add(datum);		
		add(locatie);
		add(winnaar);
		
		add(baksel);
		add(naam);
		add(categorie);
		add(prijs);
		add(beoordeling);
		add(ingredienten);
		add(ingredienten_scroll);
		add(recept);
		add(recept_scroll);
		add(kwaliteit);
		add(kosten);
		add(calorieen);
		add(smaak);
//		add(commentaar_scroll);
				
		add(terug_knop);
		
		//scherm-object luistert naar de events
		bekijkDeelnemer_knop.addActionListener(this);
		terug_knop.addActionListener(this);
		
		setVisible(true);
	}
		
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == this.bekijkDeelnemer_knop)
		{
			programmaC.actieBekijkDeelnemer();
		}
		else if(e.getSource() == this.terug_knop)
		{
			programmaC.actieTerugNaarHoofdscherm();
		}		
	}
}