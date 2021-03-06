import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class HoofdScherm extends JFrame implements ActionListener
{
	private ProgrammaController programmaC;
	private JLabel dummyLabel=new JLabel("HoofdPanel komt hier");
	private JButton loguitKnop=new JButton("Loguit");
	
	public HoofdScherm(ProgrammaController programmaC)
	{
		this.programmaC=programmaC;
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(new Rectangle(100,100,700,500));
		this.setTitle("Overzicht scherm");
		
		JPanel panel=new JPanel(new GridLayout(2,1));
		panel.add(this.dummyLabel);
		panel.add(this.loguitKnop);
		
		this.getContentPane().add(panel);
		this.loguitKnop.addActionListener(this);
		
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==this.loguitKnop)
			this.programmaC.actieLoguit();
	}
}
