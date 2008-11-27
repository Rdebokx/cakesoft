import java.sql.*;
import java.util.*;

/**
 * Deze klasse bevat methoden om met Reacties te werken
 * @author Groep 11
 * @version 1.0
 *
 */
public class BeheerReactie {

	private Database database;

	/**
	 * Maakt een nieuw BeheerReactie object aan, gekoppelt aan de opgegeven database
	 * @param db	bevat de Database waaraan het BeheerReactie object is gekoppelt
	 */
	public BeheerReactie(Database db)
	{
		database = db;
	}
	
	/**
	 * Deze methode haalt alle reacties uit de database voor het opgegeven baksel
	 * @param baksel	
	 * @return
	 */
	public ArrayList<Reactie> getReactiesOpBaksel(Baksel baksel)
	{
		int bakselID = baksel.getBaksel_id();
		String voorwaarde = "baksel_id=" + bakselID;
		
		ResultSet result = database.select("reactie", voorwaarde);
		
		ArrayList<Reactie> reacties = new ArrayList<Reactie>();
		
		//nu omzetten naar een ArrayList van Reacties
		try
		{

			while(result.next())
			{
				Reactie res = new Reactie(result.getInt("reactie_id"), result.getInt("lid_id"), result.getString("bericht"));
				reacties.add(res);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e.toString());
		}
		return reacties;
	}
	
	public void voegReactie(Reactie reactie, Baksel baksel)
	{
		
		String waarden = "," + reactie.getBericht() + "," + baksel.getBaksel_id() + "," + reactie.getLid_id();  //eerste waarde niet invoeren, is auto-increment
		
		//de gegevens in de database invoeren
		int reactie_id = database.insert("reactie", waarden);
		reactie.setReactie_id(reactie_id);
	}
	
}
