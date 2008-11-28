import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Scherm_foutmelding extends JFrame implements ActionListener
{
	private JLabel foutTekst=new JLabel();
	private JButton okKnop=new JButton("ok");
	
	public Scherm_foutmelding(String foutBericht)
	{
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setBounds(new Rectangle(100,100,400,100));
		this.setSize(400,110);
		this.setLocationRelativeTo(null);
		//Point oude_locatie=this.getLocation();
		//oude_locatie.y+=100;
		//this.setLocation(oude_locatie);
		this.setTitle("Cakesoft - Fout");
		this.setLayout(null);
		
		
		this.foutTekst.setText(foutBericht);
		
		Dimension tekstSize =this.foutTekst.getMinimumSize();
		
		this.foutTekst.setBounds((400-tekstSize.width)/2,5,380,20);
		this.okKnop.setBounds(150,40,100,25);
		
		add(this.foutTekst);
		add(this.okKnop);
		
		
		
		this.okKnop.addActionListener(this);
		
		this.setVisible(true);
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==this.okKnop)
			this.dispose();
	}
}
