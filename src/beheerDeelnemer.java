
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
	

}
