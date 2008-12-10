import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Locale;

import javax.swing.*;


/**
 * Klasse ProgrammaScherm, beschrijft het JFrame waarop de JPanels getekend worden door de ProgrammaController 
 * @author Groep 11
 */
public class ProgrammaScherm extends JFrame
{
	/**
	 * Constructor van ProgrammaScherm, stelt het programma in. 
	 */
	public ProgrammaScherm()
	{
		setTitle("CakeSoft");
		setSize(750,500);
		setResizable(false);
		setLocationRelativeTo(null); //centrering
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.addWindowListener(this);
		setLayout(null);
		
		try
		{
			
			LookAndFeel l=(LookAndFeel) Class.forName(UIManager.getSystemLookAndFeelClassName()).newInstance();
			//l.installBorder(getRootPane());
			
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		
		try
		{
			javax.swing.plaf.FontUIResource f = new javax.swing.plaf.FontUIResource("Arial",0,14);
			java.util.Enumeration keys = UIManager.getDefaults().keys();
			while(keys.hasMoreElements())
			{
				Object key = keys.nextElement();
				Object value = UIManager.get(key);
				if(value instanceof javax.swing.plaf.FontUIResource)
					UIManager.put(key,f);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		setVisible(true);
	}

	protected void processWindowEvent(WindowEvent e)
	{
		if(e.getID()==WindowEvent.WINDOW_CLOSING)
		{
			
			int exit = JOptionPane.showConfirmDialog(this,"Weet u dit zeker?","",JOptionPane.YES_NO_OPTION);
			if(exit==0)
				System.exit(0);
		}
		else
			super.processWindowEvent(e);
	}
}
