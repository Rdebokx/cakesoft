import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Achtergrond extends JComponent
{
	private BufferedImage afbeelding=null;
	
	public Achtergrond()
	{
		super();
		try
		{
			this.afbeelding=ImageIO.read(new File(getClass().getResource("taart.png").getFile()));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	protected void paintComponent(Graphics g)
	{
		if(afbeelding!=null)
		{
			g.drawImage(afbeelding,0,0,this);
		}
		
	}
	
}
