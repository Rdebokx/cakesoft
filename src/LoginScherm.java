import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class LoginScherm extends JFrame implements ActionListener
{
	private ProgrammaController programmaC;
	private JLabel lidIdLabel=new JLabel("Lid nummer:");
	private JTextField lidIdVeld=new JTextField();
	private JButton loginKnop=new JButton("Login");
	
	public LoginScherm(ProgrammaController programmaC)
	{
		this.programmaC=programmaC;
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(new Rectangle(100,100,500,300));
		this.setTitle("Cakesoft - Login");
		
		JPanel panel=new JPanel(new GridLayout(3,1));
		panel.add(this.lidIdLabel);
		panel.add(this.lidIdVeld);
		panel.add(this.loginKnop);
		
		this.getContentPane().add(panel);
		
		this.loginKnop.addActionListener(this);
		
		this.setVisible(true);
	}
	
	public Lid getLid()
	{
		Lid lid=new Lid();
		
		//stel lid is en wachtwoord in
		
		return lid;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==this.loginKnop)
			this.programmaC.actieLogin();
	}
}
