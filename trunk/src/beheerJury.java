import java.sql.ResultSet;


/**
 * De klasse beheerJury bevat alle methoden om met jury objecten te werken
 * @author Groep 11
 * @version 2.0
 */
public class beheerJury {
	
	private Database database;
	
	/**
	 * Dit is de constructor, maakt een nieuw beheerJury object aan, met de opgegeven database
	 * @param db	bevat de database waraan dit object gekoppelt is.
	 */
	public beheerJury(Database db)
	{
		database = db;
	}
	
	/**
	 * Deze methode voegt een nieuwe jurylid toe aan de database en set het jury_id, 
	 * 	aan de hand van de auto-increment functie van de database
	 * @param jury			bevat het juryobject wat toegevoegd moet worden
	 * @param wedstrijd		bevat de wedstrijd waaraan de jury gekoppelt is.
	 */
	public void voegJuryToe(Jury jury, Wedstrijd wedstrijd)
	{
		//query construeren
		queryInsert q = new queryInsert("jury");
		q.stelNieuwIn("lid_id", jury.getLid_id());
		q.stelNieuwIn("wedstrijd_id", wedstrijd.getWedstrijd_id());
		
		//de gegevens in de database invoeren
		int jury_id = database.insert(q);
		jury.setJury_id(jury_id);
	}
	
	public Jury getJuryVanWedstrijd(Wedstrijd wedstrijd, Lid lid)
	{
		Jury jury = null;
		try
		{
			//Maak een nieuwe query aan waarbij het wedstrijd_id van de gegeven wedstrijd is en het lid_id ook overeenkomt.
			querySelect selecteerJury = new querySelect("jury, lid");
			selecteerJury.stelVoorwaardeIn("jury.wedstrijd_id", query.GELIJK, wedstrijd.getWedstrijd_id());
			selecteerJury.stelLinkVoorwaardeIn("jury.lid_id", query.GELIJK, lid.getLid_id());
			
			//Voer query uit
			ResultSet res = database.select(selecteerJury);
			
			//Wanneer er iets gevonden is zoeken we vrolijk door.
			if(res.next())
			{
				jury = new Jury(res.getInt("jury_id"), res.getString("naam"), res.getInt("lid_id"), res.getString("wachtwoord"),
						res.getBoolean("hoofdbeheer"));
				
			}
		}
		catch(Exception e)
		{
			return null;
		}
		
		return jury;
	}

}
