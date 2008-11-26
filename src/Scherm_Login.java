import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Scherm_Login extends JFrame implements ActionListener
{
	private ProgrammaController programmaC;
	//Inhoud van het Inlogscherm
	private JPanel paneel = new JPanel(new GridLayout(2,2));
	private JPanel paneel2 = new JPanel(new GridLayout(1,1));
	private JLabel lidnr = new JLabel("Lidnummer");
	private JLabel pass = new JLabel("Wachtwoord");
	private JLabel filler = new JLabel("");
	private JTextField lidnr_veld = new JTextField();
	private JTextField pass_veld = new JTextField();
	private JButton loginknop = new JButton("Login");
	
	public Scherm_Login(ProgrammaController programmaC)
	{
		this.programmaC=programmaC;
		
  		//basis-instellingen scherm
		setTitle("CakeSoft");
		setSize(600,400);
		setLocationRelativeTo(null); //centrering
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		//alles aan panelen toevoegen
		paneel.add(lidnr);
		paneel.add(lidnr_veld);
		paneel.add(pass);
		paneel.add(pass_veld);
		paneel2.add(loginknop);
		paneel.setBounds(150,100,300,90);
		paneel2.setBounds(150,195,300,45);
		
		//aan het frame toevoegen
		add(paneel);
		add(paneel2);
		
		//scherm-object luistert naar de events
		loginknop.addActionListener(this);
		
		setVisible(true);
	}
	
	public Lid getLid()
	{
		Lid lid=new Lid();
		int lid_id=0;
		
		try
		{
			lid_id=Integer.parseInt(this.lidIdVeld.getText());
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
