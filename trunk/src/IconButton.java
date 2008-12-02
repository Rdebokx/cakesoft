import javax.swing.*;
import java.awt.*;

//Deze klasse helpt mee bij het creeren van plaatjes in knoppen mbv Icon-klasse
public class IconButton
{
	private JButton button = new JButton();
	private ImageIcon icon;
	
	public IconButton(String iconPath, String textIn)
	{
		icon = new ImageIcon(iconPath);
		
		button.setMargin(new Insets(0,0,0,0));
		button.setIcon(icon);
		button.setText(textIn);		
	}
	
	public JButton show()
	{
		return button;
	}
}