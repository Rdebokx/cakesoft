import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HoofdPanel extends JPanel implements ActionListener
{
	private ProgrammaController programmaC;
	private ContainerScherm scherm;
	private JLabel dummyLabel=new JLabel("HoofdPanel komt hier");
	private JButton loguitKnop=new JButton("Loguit");
	
	public HoofdPanel(ProgrammaController programmaC, ContainerScherm scherm)
	{
		this.programmaC=programmaC;
		this.scherm=scherm;
		
		this.setLayout(new GridLayout(2,1));
		this.add(this.dummyLabel);
		this.add(this.loguitKnop);
		
		this.loguitKnop.addActionListener(this);
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==this.loguitKnop)
			this.programmaC.actieLoguit();
	}
	
}
