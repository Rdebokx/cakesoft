import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

public class Panel_Login extends JPanel
{
	
	//Inhoud van het Inlogscherm
	private JPanel paneel;
	private JPanel paneel2;
	private JLabel lidnr;
	private JLabel pass;
	private JTextField lidnr_veld;
	private JTextField pass_veld;
	//private Achtergrond bg=new Achtergrond();
	
	private JButton loginknop;
	
	public Panel_Login()
	{
		
//		this.setBackground(Color.PINK);
		//System.out.println("Making panel...");
		paneel=new JPanel(new GridLayout(2,2));
		paneel2= new JPanel(new GridLayout(1,1));
		lidnr = new JLabel("Lidnummer");
		pass = new JLabel("Wachtwoord");
		lidnr_veld = new JTextField();
		pass_veld = new JTextField();
		loginknop = new IconButton("_Icons/user.png", "Login").show();
		
		
		/*try{
			bg.setLocation(0,0);
			Dimension dim=new Dimension(750,500);
			bg.setPreferredSize(dim);
			bg.setMinimumSize(dim);
			bg.setMaximumSize(dim);
			bg.setSize(dim);
			bg.setLayout(null);
			add(bg,new Integer(3),0);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}*/
		//Image bg_img=new Image(getClass().getResource("taart.png"));
		//bg_img.paintIcon(arg0, arg1, arg2, arg3)
		
		
  		//basis-instellingen scherm
		/*setTitle("CakeSoft");
		setSize(600,400);
		setResizable(false);
		setLocationRelativeTo(null); //centrering
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
		setLayout(null);
		
		//alles aan panelen toevoegen
		paneel.add(lidnr,new Integer(1),0);
		paneel.add(lidnr_veld);
		paneel.add(pass);
		paneel.add(pass_veld);
		paneel2.add(loginknop);
		paneel.setBounds(225,150,300,90);
		paneel2.setBounds(225,245,300,45);
		
		//aan het frame toevoegen
		add(paneel,new Integer(1),0);
		add(paneel2,new Integer(1),0);
		
		
		//scherm-object luistert naar de events
		/*
		lidnr_veld.addActionListener(this);
		pass_veld.addActionListener(this);
		loginknop.addActionListener(this);
		*/
		
		repaint();
		revalidate();
		setVisible(true);
	}
	
	public Lid getLid()
	{
		Lid lid=new Lid();
		int lid_id=0;
		
		try
		{
			lid_id=Integer.parseInt(lidnr_veld.getText());
		}
		catch(Exception e)
		{
			//foutmelding (nog) weergeven
		}
		
		lid.setLid_id(lid_id);
		lid.setWachtwoord(pass_veld.getText());
				
		return lid;
	}
	
	public JButton getLoginKnop()
	{
		return this.loginknop;
	}
	
	public JTextField getLidnr_veld()
	{
		return this.lidnr_veld;
	}
	
	public JTextField getPass_veld()
	{
		return this.pass_veld;
	}
	
	/*public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==this.loginknop || e.getSource()==this.lidnr_veld || e.getSource()==this.pass_veld)
			this.programmaC.actieLogin();
	}*/
}
