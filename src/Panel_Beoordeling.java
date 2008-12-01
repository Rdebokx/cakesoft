import javax.swing.*;
import java.awt.event.*;
import java.util.*;


public class Panel_Beoordelen extends JPanel {

	private static final long serialVersionUID = 1L;
	//
	private ProgrammaController programmaC;
	
	private JLabel locatie = new JLabel("Locatie");
	private JLabel datum = new JLabel("Datum");
	
	private JLabel deelnemers = new JLabel("Deelnemers:");
	private JLabel naam = new JLabel("Naam:");
	private JTextPane naampane = new JTextPane();
	
	private JLabel prijs = new JLabel("Prijshoogte:");
	private JLabel kwaliteit = new JLabel("Kwaliteit:");
	private JLabel calorie = new JLabel("Caloriegehalte:");
	private JLabel smaak = new JLabel("Smaak:");
	
	private JTextField prijs_veld = new JTextField();
	private JTextField kwaliteit_veld = new JTextField();
	private JTextField calorie_veld = new JTextField();
	private JTextField smaak_veld = new JTextField();
	
	private JList deelnemers_lijst = new JList();
	private JScrollPane deelnemers_scroll = new JScrollPane();
	private JButton deelnemer_knop = new JButton("Bekijk deelnemer");
	private JButton beoordeel_knop = new JButton("Beoordeel");
	private JButton terug_knop = new JButton("Terug");
	
	private ArrayList <Deelnemer> DeelnemerLijst;
	
	public Panel_Beoordelen()
	{
		this.programmaC=programmaC;
		
  		/*basis-instellingen scherm
		setTitle("CakeSoft - Beoordelen");
		setSize(750,450);
		setResizable(false);
		setLocationRelativeTo(null); //centrering
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
		
		setLayout(null);
		
		//alles aan container positioneren
		
		datum.setBounds(30,30,300,20);
		locatie.setBounds(30,60,300,20);
		
		deelnemers.setBounds(30,140,200,20);
		deelnemers_scroll.setBounds(30,160,200,150);
		deelnemer_knop.setBounds(30,310,200,25);
		
		naam.setBounds(520,140,200,20);
		naampane.setBounds(570,140,150,25);
		
		kwaliteit.setBounds(520,180,200,20);
		kwaliteit_veld.setBounds(570,180,150,20);
		
		prijs.setBounds(520,210,200,20);
		prijs_veld.setBounds(570,210,150,25);
		
		calorie.setBounds(520,240,200,20);
		calorie_veld.setBounds(570,240,150,25);
		
		smaak.setBounds(520,270,200,20);
		smaak_veld.setBounds(570,270,150,25);
		
		beoordeel_knop.setBounds(520,310,200,25);
		
		terug_knop.setBounds(30,380,75,25);
		
		
		//Moet dit hier?? en zo ja hoe?
		//naampane.setText(deelnemer.getNaam());
		
		//aan het Frame toevoegen
		
		add(datum);
		add(locatie);
		
		add(deelnemers);
		add(deelnemers_scroll);
		
		add(naam);
		add(naampane);
		
		add(kwaliteit);
		add(kwaliteit_veld);
		
		add(prijs);
		add(prijs_veld);
		
		add(smaak);
		add(smaak_veld);
		
		add(beoordeel_knop);
		add(terug_knop);	
		
		//scherm-object luistert naar de events
		
		/*beoordeel_knop.addActionListener(this);
		terug_knop.addActionListener(this);*/
		
		setVisible(true);
		
	}
	// TODO Hier moet nog even naar gekeken worden
	public Deelnemer getGeselecteerdeDeelnemer()
	{
		int geselecteerd=this.deelnemers_lijst.getSelectedIndex();
		
		if(geselecteerd<0)
			return null;
		else
			return this.DeelnemerLijst.get(geselecteerd);
	}
	
	
	@Override
	/*public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == this.beoordeel_knop)
		{
			programmaC.actieInschrijvingVerzenden();
		}
		if(e.getSource() == this.terug_knop)
		{
			programmaC.actieTerugNaarWedstrijd();
		}		
		
	}*/
	
	public JButton getBeoordeel_knop()
	{
		return this.beoordeel_knop;
	}
	
	public JButton getTerug_knop()
	{
		return this.terug_knop;
	}
}