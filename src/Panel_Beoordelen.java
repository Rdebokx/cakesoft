import javax.swing.*;

import java.awt.event.*;
import java.util.*;


public class Panel_Beoordelen extends JPanel {

	
	private JLabel naam = new JLabel("Naam:");
	private JLabel naam_waarde = new JLabel();
	
	private JLabel prijs = new JLabel("Prijshoogte:");
	private JLabel kwaliteit = new JLabel("Kwaliteit:");
	private JLabel calorie = new JLabel("Caloriegehalte:");
	private JLabel smaak = new JLabel("Smaak:");
	
	private JTextField prijs_veld = new JTextField();
	private JTextField kwaliteit_veld = new JTextField();
	private JTextField calorie_veld = new JTextField();
	private JTextField smaak_veld = new JTextField();
	
	private JLabel commentaar = new JLabel("Commentaar:");
	private JTextArea commentaar_veld = new JTextArea();
	
	private JButton beoordeel_knop = new IconButton("_Icons/star.png","Beoordeel").show();
	private JButton terug_knop = new IconButton("_Icons/arrow_left.png","Terug").show();
	
	private ArrayList <Deelnemer> DeelnemerLijst;
	
	public Panel_Beoordelen()
	{
		
  		/*basis-instellingen scherm
		setTitle("CakeSoft - Beoordelen");
		setSize(750,450);
		setResizable(false);
		setLocationRelativeTo(null); //centrering
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
		
		setLayout(null);
		
		//alles aan container positioneren
		
		naam.setBounds(30,30,200,20);
		naam_waarde.setBounds(80,30,150,20);
		
		kwaliteit.setBounds(30,80,200,20);
		kwaliteit_veld.setBounds(150,80,150,25);
		
		prijs.setBounds(30,110,200,20);
		prijs_veld.setBounds(150,110,150,25);
		
		calorie.setBounds(30,140,200,20);
		calorie_veld.setBounds(150,140,150,25);
		
		smaak.setBounds(30,170,200,20);
		smaak_veld.setBounds(150,170,150,25);
		
		commentaar.setBounds(400,80,200,20);
		commentaar_veld.setBounds(400,105,200,100);
		
		beoordeel_knop.setBounds(250,250,200,35);
		
		terug_knop.setBounds(30,430,75,35);
		
		
		//Moet dit hier?? en zo ja hoe?
		//naampane.setText(deelnemer.getNaam());
		
		//aan het Frame toevoegen
		
		
		add(naam);
		add(naam_waarde);
		
		add(kwaliteit);
		add(kwaliteit_veld);
		
		add(prijs);
		add(prijs_veld);
		
		add(calorie);
		add(calorie_veld);
		
		add(smaak);
		add(smaak_veld);
		
		add(commentaar);
		add(commentaar_veld);
		
		add(beoordeel_knop);
		add(terug_knop);	
		
		//scherm-object luistert naar de events
		
		/*beoordeel_knop.addActionListener(this);
		terug_knop.addActionListener(this);*/
		
		setVisible(true);
		
	}
	
	public Beoordeling getBeoordeling()
	{
		Beoordeling beoordeling=null;
		
		int calo=0,kwaliteit=0,prijs=0,smaak=0;
		
		try
		{
			calo=Integer.valueOf(this.calorie_veld.getText());
			kwaliteit=Integer.valueOf(this.kwaliteit_veld.getText());
			prijs=Integer.valueOf(this.prijs_veld.getText());
			smaak=Integer.valueOf(this.smaak_veld.getText());
		}
		catch(Exception e)
		{
			//
		}
		
		if(calo<1 || calo>10)
		{
			new Scherm_foutmelding("U hebt geen geldige waardering voor het caloriegehalte ingevuld.");
			return null;
		}
		if(kwaliteit<1 || kwaliteit>10)
		{
			new Scherm_foutmelding("U hebt geen geldige waardering voor de kwaliteit ingevuld.");
			return null;
		}
		if(prijs<1 || prijs>10)
		{
			new Scherm_foutmelding("U hebt geen geldige waardering voor de prijshoogte ingevuld.");
			return null;
		}
		if(smaak<1 || smaak>10)
		{
			new Scherm_foutmelding("U hebt geen geldige waardering voor de smaak ingevuld.");
			return null;
		}
		
		beoordeling=new Beoordeling();
		
		beoordeling.setCalo(calo);
		beoordeling.setKwaliteit(kwaliteit);
		beoordeling.setPrijs(prijs);
		beoordeling.setSmaak(smaak);
		
		beoordeling.setCommentaar(this.commentaar_veld.getText());
		
		
		return beoordeling;
	}
	
	
	public JButton getBeoordeel_knop()
	{
		return this.beoordeel_knop;
	}
	
	public JButton getTerug_knop()
	{
		return this.terug_knop;
	}
}