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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		setVisible(true);
	}
}
