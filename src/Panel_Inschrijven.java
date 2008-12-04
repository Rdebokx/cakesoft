import javax.swing.*;

import java.awt.event.*;

public class Panel_Inschrijven extends JPanel
{
	
	
	//Eclipse-fix
	private static final long serialVersionUID = 1L;
	
	private Wedstrijd wedstrijd;
	
	//Inhoud van het Inschrijfscherm
	private JLabel datum = new JLabel("Datum:");
	private JLabel locatie = new JLabel("Locatie:");
	private JLabel inschrijving = new JLabel("Inschrijving:");	
	private JLabel bakselnaam = new JLabel("Bakselnaam:");
	private JLabel categorie = new JLabel("Categorie:");
	private JLabel prijs = new JLabel("Prijs:");
	private JLabel recept = new JLabel("Recept: ");
	
	private JTextPane recept_tekst = new JTextPane();
	private JScrollPane recept_scroll = new JScrollPane();
	
	private JLabel ingredienten = new JLabel("Ingrediënten:");
	
	private JScrollPane ingredienten_scroll = new JScrollPane();
	private JTextPane ingredienten_tekst = new JTextPane();
	
	private JTextField catogorie_veld = new JTextField();
	private JTextField prijs_veld = new JTextField();
	private JTextField bakselnaam_veld = new JTextField();
		
	private JButton terug_knop = new IconButton("_Icons/arrow_left.png","Terug").show();
	private JButton inschrijf_knop = new IconButton("_Icons/ok.png","Inschrijven").show();
		
	
	public Panel_Inschrijven(Wedstrijd wedstrijd)
	{
		this.wedstrijd=wedstrijd;
		
		this.datum.setText("Datum: "+this.wedstrijd.getDatumString());
		this.locatie.setText("Locatie: "+this.wedstrijd.getLocatie());
		
		recept_tekst.setEditable(true);
		recept_tekst.setText("");
		recept_scroll.setViewportView(recept_tekst);
		
		ingredienten_tekst.setEditable(true);
		ingredienten_tekst.setText("");
		ingredienten_scroll.setViewportView(ingredienten_tekst);
		
		//basis-instellingen scherm
		/*setTitle("CakeSoft - Inschrijven");
		setSize(750,450);
		setResizable(false);
		setLocationRelativeTo(null); //centrering
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
		setLayout(null);
		
		//alles aan container positioneren
		
		inschrijving.setBounds(30,120,300,20);
		
		datum.setBounds(30,30,300,20);
		locatie.setBounds(30,60,300,20);

		bakselnaam.setBounds(30,120,300,20);
		bakselnaam_veld.setBounds(120,120,120,20);
		categorie.setBounds(30,140,300,20);
		catogorie_veld.setBounds(120,145,120,20);
		prijs.setBounds(30,165,300,20);
		prijs_veld.setBounds(120,170,120,20);
		ingredienten.setBounds(30,200,250,20);
		ingredienten_scroll.setBounds(30,220,250,120);
		recept.setBounds(350,200,250,20);
		recept_scroll.setBounds(350,220,360,120);
		
		inschrijf_knop.setBounds(520,415,200,50);		
		terug_knop.setBounds(30,430,75,35);
		
		//aan het frame toevoegen
		add(datum);		
		add(locatie);
		
		add(bakselnaam);
		add(bakselnaam_veld);
		add(categorie);
		add(catogorie_veld);
		add(prijs);
		add(prijs_veld);
		add(ingredienten);
		add(ingredienten_scroll);
		add(recept);
		add(recept_scroll);
		
		add(inschrijf_knop);
		add(terug_knop);
		
		//scherm-object luistert naar de events
		/*inschrijf_knop.addActionListener(this);
		terug_knop.addActionListener(this);
		*/
		
		setVisible(true);
		
	}

	public Baksel getBaksel()
	{
		Baksel baksel=null;
		double prijs;
		
		if(this.bakselnaam_veld.getText().equals(""))
		{
			new Scherm_foutmelding("U hebt geen naam voor uw baksel ingevuld.");
			return null;
		}
		
		if(this.catogorie_veld.getText().equals(""))
		{
			new Scherm_foutmelding("U hebt geen categorie ingevuld.");
			return null;
		}
		
		try
		{
			String prijsStr=this.prijs_veld.getText();
			prijsStr=prijsStr.replace(',','.');
			prijs=Double.valueOf(prijsStr);
		}
		catch(Exception e)
		{
			new Scherm_foutmelding("U hebt geen geldige prijs ingevoerd.");
			return null;
		}
		
		if(this.ingredienten_tekst.getText().equals(""))
		{
			new Scherm_foutmelding("U hebt geen ingrediënten ingevuld.");
			return null;
		}
		
		if(this.recept_tekst.getText().equals(""))
		{
			new Scherm_foutmelding("U hebt geen recept ingevuld.");
			return null;
		}
		
		
		baksel=new Baksel();
		baksel.setCategorie(this.catogorie_veld.getText());
		baksel.setIngredienten(this.ingredienten_tekst.getText());
		baksel.setNaam(this.bakselnaam_veld.getText());
		baksel.setRecept(this.recept_tekst.getText());
		baksel.setPrijs(prijs);
		
		return baksel;
	}

	/*
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == this.inschrijf_knop)
		{
			programmaC.actieInschrijvingVerzenden();
		}
		if(e.getSource() == this.terug_knop)
		{
			programmaC.actieTerugNaarWedstrijd();
		}		
	}*/
	
	public JButton getInschrijf_knop()
	{
		return this.inschrijf_knop;
	}
	
	public JButton getTerug_knop()
	{
		return this.terug_knop;
	}
	
}
