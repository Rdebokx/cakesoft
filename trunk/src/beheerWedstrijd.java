import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Yorick
 * beheerWedstrijd klasse, beheert de wedstrijd-objecten.
 */
public class beheerWedstrijd {
	private Database db;
	
	/**
	 * Constructor voor beheerWedstrijd, maakt een nieuw object aan en vult de Database in.
	 * @param db	De te gebruiken Database
	 */
	public beheerWedstrijd(Database db)
	{
		this.db = db;
	}

	/**
	 * Methode getAlleWedstrijden, geeft een ArrayList terug met alle Wedstrijden uit de database.
	 * @return	ArrayList met alle Wedstrijden die op dit moment in de database zitten.
	 */
	public ArrayList<Wedstrijd> getAlleWedstrijden()
	{
		ArrayList<Wedstrijd> uitvoerLijst = new ArrayList<Wedstrijd>();
		int wedstrijdid = -1;
		Date datum = null;
		String locatie = null;
		boolean inschrijvingOpen = false;
		boolean beoordelingOpen = false;
		
		//Query voor 'alles'
		ResultSet res = db.query("wedstrijd", null, null);
				
		try {
			while(res.next())
			{
				//Stel de waarden in
				wedstrijdid = res.getInt("p_wedstrijd_id");
				datum = res.getDate("datum");
				locatie = res.getString("locatie");
				inschrijvingOpen = res.getBoolean("inschrijvingOpen");
				beoordelingOpen = res.getBoolean("beoordelingOpen");
				
				//Voeg toe aan de arrayList
				uitvoerLijst.add(new Wedstrijd(wedstrijdid, datum, locatie, inschrijvingOpen, beoordelingOpen));				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return uitvoerLijst;
	}

	public void voegWedstrijdToe(Wedstrijd wedstrijd)
	{
		
		db.insert("wedstrijd", new String[{String.valueOf(wedstrijd.getWedstrijd_id()), wedstrijd.getDatumString(), wedstrijd.getLocatie(), String.valueOf(wedstrijd.isInschrijvingOpen()), String.valueOf(wedstrijd.isBeoordelingOpen())}]);
	}
	//post:: wedstrijd is toegevoegd aan de database en de wedstrijd_id van wedstrijd is aangepast

	public void updateWedstrijd(Wedstrijd wedstrijd)
	{
		
	}
	//post:: de entry in de wedstrijd tabel in de db met de corresponderende wedstrijd_id is geupdate met de gegevens uit wedstrijd
	
}
