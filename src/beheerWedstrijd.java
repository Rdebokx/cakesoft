import java.sql.ResultSet;
import java.text.SimpleDateFormat;

/**
 * @author Yorick
 * beheerWedstrijd klasse, beheert de wedstrijd-objecten.
 */
public class beheerWedstrijd {
	private Database db;
	
	public beheerWedstrijd(Database db)
	{
		this.db = db;
	}
	//post:: heeft een nieuw beheerWedstrijd object gemaakt en de Database ingesteld

	public ArrayList<Wedstrijd> getAlleWedstrijden()
	{
		ResultSet res = db.query("wedstrijd", null, null);
		int wedstrijdid = -1;
		Date datum = null;
		String locatie = null;
		boolean inschrijvingOpen = false;
		boolean beoordelingOpen = false;
				
		while(res.next())
		{
			wedstrijdid = res.getInt(1);
			datum = res.getDate("datum");
			datum.parse(null);
		}
	}
	//post:: geeft een geordende lijst terug met alle wedstrijden, waarin de nieuwste wedstrijd bovenaan staat

	public void voegWedstrijdToe(Wedstrijd wedstrijd)
	{
		
	}
	//post:: wedstrijd is toegevoegd aan de database en de wedstrijd_id van wedstrijd is aangepast

	public void updateWedstrijd(Wedstrijd wedstrijd)
	{
		
	}
	//post:: de entry in de wedstrijd tabel in de db met de corresponderende wedstrijd_id is geupdate met de gegevens uit wedstrijd
	
}
