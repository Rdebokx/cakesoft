import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class loginPanel extends JPanel implements ActionListener
{
	private ProgrammaController programmaC;
	private ContainerScherm scherm;
	private JLabel lidIdLabel=new JLabel("Lid nummer:");
	private JTextField lidIdVeld=new JTextField();
	private JButton loginKnop=new JButton("Login");
	
	public loginPanel(ProgrammaController programmaC, ContainerScherm scherm)
	{
		this.programmaC=programmaC;
		this.scherm=scherm;
		
		this.setLayout(new GridLayout(3,1));
		this.add(this.lidIdLabel);
		this.add(this.lidIdVeld);
		this.add(this.loginKnop);
		
		this.loginKnop.addActionListener(this);
		
		//this.scherm.veranderPanel(this);
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
