import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Scherm_foutmelding extends JFrame implements ActionListener
{
	private ProgrammaController programmaC;
	private JLabel foutTekst=new JLabel();
	private JButton okKnop=new JButton("ok");
	
	public Scherm_foutmelding(ProgrammaController programmaC, String foutBericht)
	{
		this.programmaC=programmaC;
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(new Rectangle(100,100,500,300));
		this.setTitle("Cakesoft - Login");
		
		this.foutTekst.setText(foutBericht);
		
		JPanel panel=new JPanel(new GridLayout(3,1));
		panel.add(this.foutTekst);
		panel.add(this.okKnop);
		
		this.getContentPane().add(panel);
		
		this.okKnop.addActionListener(this);
		
		this.setVisible(true);
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==this.okKnop)
			this.dispose();
	}
}
