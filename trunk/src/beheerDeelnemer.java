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
		q.stelNieuwIn("baksel_id", baksel.getBaksel_id());
		
		//de gegevens in de database invoeren
		int deelnemer_id = database.insert(q);
		deelnemer.setDeelnemer_id(deelnemer_id);
		
	}
	
	/**
	 * getDeelnemers geeft alle deelnemers terug van de gegeven wedstrijd.
	 * @param wedstrijd	De te doorzoeken Wedstrijd
	 * @return		ArrayList met Deelnemers van bepaalde Wedstrijd.
	 */
	public ArrayList<Deelnemer> getDeelnemers(Wedstrijd wedstrijd)
	{
		try
		{
			querySelect q= new querySelect("deelnemer, lid");
			q.stelVoorwaardeIn("deelnemer.wedstrijd_id",query.GELIJK,wedstrijd.getWedstrijd_id());
			q.stelLinkVoorwaardeIn("deelnemer.lid_id",query.GELIJK,"lid.lid_id");
			ResultSet res=database.select(q);
			ArrayList<Deelnemer> deelnemers=new ArrayList<Deelnemer>();
			querySelect q2;
			ResultSet res2;
			Baksel baksel;
			
			
			while(res.next())
			{
				Deelnemer deelnemer=new Deelnemer(res.getInt("deelnemer_id"),res.getString("naam"),res.getInt("lid_id"),res.getString("wachtwoord"),res.getInt("hoofdbeheer")==1);
				
				q2 = new querySelect("baksel");
				q2.stelVoorwaardeIn("baksel_id",query.GELIJK,Integer.toString(res.getInt("baksel_id")));
				res2=database.select(q2);
				res2.next();
				baksel = new Baksel(res2.getInt("baksel_id"),res2.getString("ingredienten"),res2.getString("recept"),res2.getString("naam"),res2.getString("categorie"),res2.getDouble("prijs"));
				deelnemer.setBaksel(baksel);
				
				deelnemers.add(deelnemer);
			}
			
			return deelnemers;
		}
		catch(Exception e)
		{
			return null;
		}
		
	}
	
	/**
	 * getDeelnemerVanWedstrijd, geeft het Deelnemer-object terug als een Lid staat ingeschreven voor de gegeven Wedstrijd, anders null.
	 * @param lid		Het te controleren Lid
	 * @param wedstrijd	De betreffende Wedstrijd
	 * @return			Deelnemer, desda lid deelneemt aan wedstrijd
	 */
	public Deelnemer getDeelnemerVanWedstrijd(Lid lid, Wedstrijd wedstrijd)
	{
		Deelnemer deelnemer = null;
		int bakselID = -1;
		try
		{
			//Maak een nieuwe query aan waarbij het wedstrijd_id van de gegeven wedstrijd is en het lid_id ook overeenkomt.
			querySelect selecteerDeelnemer = new querySelect("deelnemer, lid");
			selecteerDeelnemer.stelVoorwaardeIn("deelnemer.wedstrijd_id", query.GELIJK, wedstrijd.getWedstrijd_id());
			selecteerDeelnemer.stelVoorwaardeIn("deelnemer.lid_id", query.GELIJK, lid.getLid_id());
			selecteerDeelnemer.stelLinkVoorwaardeIn("deelnemer.lid_id",query.GELIJK,"lid.lid_id");
			//Voer query uit
			ResultSet res = database.select(selecteerDeelnemer);
			
			//Wanneer er iets gevonden is zoeken we vrolijk door.
			if(res.next())
			{
				deelnemer = new Deelnemer(res.getInt("deelnemer_id"), res.getString("naam"), res.getInt("lid_id"), res.getString("wachtwoord"),
						res.getInt("hoofdbeheer")==1);
				
				bakselID = res.getInt("baksel_id");
				
				querySelect selecteerBaksel = new querySelect("baksel");
				selecteerBaksel.stelVoorwaardeIn("baksel_id", query.GELIJK, bakselID);
				
				//Voer query uit
				ResultSet res2 = database.select(selecteerBaksel);
				
				//Yay, het is raak, we zoeken door.
				if(res2.next())
				{
					//Baksel gevonden, stel de boel in;
					int baksel_id = res2.getInt("baksel_id");
					String ingredienten = res2.getString("ingredienten");
					String recept = res2.getString("recept");
					String naam = res2.getString("naam");
					String categorie = res2.getString("categorie");
					double prijs = res2.getDouble("prijs");
					Baksel baksel = new Baksel(baksel_id, ingredienten, recept, naam, categorie, prijs);
					 
					//Stel baksel in
					deelnemer.setBaksel(baksel);
				}
			}
		}
		catch(Exception e)
		{
			return null;
		}
		
		return deelnemer;
		
	}
	

}
