import java.sql.*;
import java.util.*;

/**
 * Deze klasse bevat methoden om met Reacties te werken
 * @author Groep 11
 */
public class beheerReactie {

	private Database database;

	/**
	 * Maakt een nieuw BeheerReactie object aan, gekoppelt aan de opgegeven database
	 * @param db	bevat de Database waaraan het BeheerReactie object is gekoppelt
	 */ 
	public beheerReactie(Database db)
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
		
		//Maak query aan en stel voorwaarde in.
		querySelect selecteerQuery = new querySelect("reactie, lid");
		selecteerQuery.stelVoorwaardeIn("reactie.baksel_id", query.GELIJK, bakselID);
		selecteerQuery.stelLinkVoorwaardeIn("reactie.lid_id",query.GELIJK,"lid.lid_id");
		selecteerQuery.stelSorteringIn("reactie.reactie_id",false);
		
		ResultSet result = database.select(selecteerQuery);
		Lid schrijver=null;
		int lid_id;
		String naam,wachtwoord;
		boolean hoofdbeheer;
		ArrayList<Reactie> reacties = new ArrayList<Reactie>();
		Reactie reactie;
		
		//Nu omzetten naar een ArrayList van Reacties
		try
		{

			while(result.next())
			{
				reactie = new Reactie(result.getInt("reactie_id"), result.getInt("lid_id"), result.getString("bericht"));
				
				lid_id = result.getInt("lid_id");
				naam = result.getString("naam");
				wachtwoord = result.getString("wachtwoord");
				hoofdbeheer = result.getBoolean("hoofdbeheer");
				
				schrijver=new Lid(naam, lid_id, wachtwoord, hoofdbeheer);
				reactie.setSchrijver(schrijver);
				reacties.add(reactie);
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
		//De gegevens in de database invoeren
		queryInsert insertQuery=new queryInsert("reactie");
		insertQuery.stelNieuwIn("bericht", reactie.getBericht());
		insertQuery.stelNieuwIn("baksel_id", baksel.getBaksel_id());
		insertQuery.stelNieuwIn("lid_id", reactie.getLid_id());
		
		int reactie_id = database.insert(insertQuery);
		reactie.setReactie_id(reactie_id);
	}
	
}