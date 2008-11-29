import java.sql.*;
import java.util.*;


/**
 * Deze methode bevat alle methoden voor het werken met Deelnemer objecten.
 * @author Groep 11
 * @version 2.0
 *
 */
public class beheerDeelnemer {
	
	private Database database;
	
	/**
	 * Deze methode is de constructor en maakt een nieuw beheerDeelnemer object aan, gekoppelt aan de opgegeven database
	 * @param db	
	 */
	public beheerDeelnemer(Database db)
	{
		database = db;
	}
	
	/**
	 * Deze methode voegt een nieuwe deelnemer toe aan de database en set de id van de deelnemer,
	 * aan de hand van de auto-increment functie van de database
	 * @param deelnemer		bevat het deelnemer object dat moet worden toegevoegd
	 * @param baksel		bevat het baksel waaraan de deelnemer gekoppelt is.
	 * @param wedstrijd		bevat de wedstrijd waaraan de deelnemer meedoet.
	 */
	public void voegDeelnemerToe(Deelnemer deelnemer, Baksel baksel, Wedstrijd wedstrijd)
	{
		//een query construeren
		queryInsert q = new queryInsert("deelnemer");
		q.stelNieuwIn("lid_id", deelnemer.getLid_id());
		q.stelNieuwIn("wedstrijd_id", wedstrijd.getWedstrijd_id());
		q.stelNieuwIn("baksel", baksel.getBaksel_id());
		
		//de gegevens in de database invoeren
		int deelnemer_id = database.insert(q);
		deelnemer.setDeelnemer_id(deelnemer_id);
		
	}
	
	public ArrayList<Deelnemer> getDeelnemers(Wedstrijd wedstrijd)
	{
		try
		{
			querySelect q= new querySelect("deelnemer, lid");
			q.stelVoorwaardeIn("deelnemer.wedstrijd_id",query.GELIJK,wedstrijd.getWedstrijd_id());
			q.stelLinkVoorwaardeIn("deelnemer.lid_id",query.GELIJK,"lid.lid_id");
			ResultSet res=database.select(q);
			ArrayList<Deelnemer> deelnemers=new ArrayList<Deelnemer>();
			/*querySelect q2;
			ResultSet res2;
			Baksel baksel;*/
			
			
			while(res.next())
			{
				Deelnemer deelnemer=new Deelnemer(res.getInt("deelnemer_id"),res.getString("naam"),res.getInt("lid_id"),res.getString("wachtwoord"),res.getInt("hoofdbeheer")==1);
				
				/*q2 = new querySelect("baksel");
				q2.stelVoorwaardeIn("baksel_id",query.GELIJK,Integer.toString(res.getInt("baksel_id")));
				res2=database.select(q2);
				res2.next();
				baksel = new Baksel(res2.getInt("baksel_id"),res2.getString("ingredienten"),res2.getString("recept"),res2.getString("naam"),res2.getString("categorie"),res2.getDouble("prijs"));
				deelnemer.setBaksel(baksel);*/
				
				deelnemers.add(deelnemer);
			}
			
			return deelnemers;
		}
		catch(Exception e)
		{
			return null;
		}
		
	}
	

}
