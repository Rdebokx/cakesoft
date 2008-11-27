
/**
 * Deze methode bevat alle methoden voor het werken met Deelnemer objecten.
 * @author Groep 11
 * @version 1.0
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
		String waarden = "," + deelnemer.getLid_id() + "," + wedstrijd.getWedstrijd_id() + "," + baksel.getBaksel_id();  //eerste waarde niet invoeren, is auto-increment
		
		//de gegevens in de database invoeren
		int deelnemer_id = database.insert("deelnemer", waarden);
		deelnemer.setDeelnemer_id(deelnemer_id);
	}
	

}
