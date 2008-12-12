import javax.swing.*;
import java.awt.*;

//De Klasse IconButton voegt een icoon aan een knop toe
public class IconButton
{
	private JButton button = new JButton();
	private ImageIcon icon;
	
	public IconButton(String iconPath, String textIn)
	{
		try
		{
			
			icon = new ImageIcon(getClass().getResource(iconPath));
			button.setMargin(new Insets(0,0,0,0));
			button.setIcon(icon);
			button.setText(textIn);
		}
		catch(Exception e)
		{
			System.out.println("Bestandsfout: "+iconPath);
		}
	}
	
	public JButton show()
	{
		return button;
	}
}