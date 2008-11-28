import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Scherm_WedstrijdNieuw extends JFrame implements ActionListener
{
	//Eclipse-fix
	private static final long serialVersionUID = 1L;
	//
	
	private ProgrammaController programmaC;
	//Inhoud van het Inlogscherm
	private JPanel paneel = new JPanel(new GridLayout(2,2));
	private JPanel paneel2 = new JPanel(new GridLayout(1,1));
	private JLabel datum = new JLabel("Datum wedstrijd:");
	private JLabel locatie = new JLabel("Locatie wedstrijd:");
	private JTextField datum_veld = new JTextField();
	private JTextField locatie_veld = new JTextField();
	private JButton maak_knop = new JButton("Maak een nieuwe wedstrijd");
	private JButton terug_knop = new JButton("Terug");
	
	public Scherm_WedstrijdNieuw(ProgrammaController programmaC)
	{
		this.programmaC=programmaC;
		
  		//basis-instellingen scherm
		setTitle("CakeSoft - Een nieuwe wedstrijd maken");
		setSize(750,450);
		setResizable(false);
		setLocationRelativeTo(null); //centrering
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		//alles aan panelen toevoegen
		paneel.add(datum);
		paneel.add(datum_veld);
		paneel.add(locatie);
		paneel.add(locatie_veld);
		paneel2.add(maak_knop);
		paneel.setBounds(225,150,300,90);
		paneel2.setBounds(225,240,300,45);
		
		terug_knop.setBounds(30,380,75,25);

		//aan het frame toevoegen
		add(paneel);
		add(paneel2);
		
		add(terug_knop);
		
		//scherm-object luistert naar de events
		maak_knop.addActionListener(this);
		
		setVisible(true);
	}
		
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==this.maak_knop)
			this.programmaC.actieMaak();
	}
}