
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

}
