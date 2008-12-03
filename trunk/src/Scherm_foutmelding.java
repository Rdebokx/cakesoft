import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Scherm_foutmelding extends JFrame implements ActionListener
{
	private JTextArea foutTekst=new JTextArea();
	private JButton okKnop=new JButton("Sluit");
	
	public Scherm_foutmelding(String foutBericht)
	{
		this.init();
		this.setTekst(foutBericht);
	}
	
	public Scherm_foutmelding(String foutBericht, String titel)
	{
		this.init();
		this.setTekst(foutBericht);
		this.setTitle(titel);
	}
	
	public void setTekst(String tekst)
	{
		this.foutTekst.setText(tekst);
		
		Dimension tekstSize =this.foutTekst.getMinimumSize();
		this.foutTekst.setBounds((400-tekstSize.width)/2,5,tekstSize.width,tekstSize.height);
		this.okKnop.setBounds(150,20+tekstSize.height,100,35);
		
		this.setSize(400,90+tekstSize.height);		
	}
	
	public void init()
	{
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setBounds(new Rectangle(100,100,400,100));
		this.setSize(400,110);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		//Point oude_locatie=this.getLocation();
		//oude_locatie.y+=100;
		//this.setLocation(oude_locatie);
		this.setTitle("Cakesoft - Fout");
		this.setLayout(null);
		
		this.foutTekst.setBackground(this.getBackground());
		this.foutTekst.setEditable(false);
		this.foutTekst.setBorder(null);
		this.okKnop.setBounds(150,40,100,25);
		
		this.add(this.foutTekst);
		this.add(this.okKnop);
		
		
		
		this.okKnop.addActionListener(this);
		
		this.setVisible(true);
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==this.okKnop)
			this.dispose();
	}
}
