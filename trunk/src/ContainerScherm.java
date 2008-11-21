import javax.swing.*;
import java.util.*;
import java.awt.*;
//import java.awt.event.*;

public class ContainerScherm extends JFrame
{
	private ArrayList<JPanel> panels;
	
	public ContainerScherm()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(new Rectangle(100,100,700,500));
		setTitle("Cakesoft");
		
		setVisible(true);
	}
	
}
