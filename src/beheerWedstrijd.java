import java.sql.ResultSet;
import java.sql.SQLException;
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
		int winnaar_lid_id;
		
		querySelect selecteerQuery = new querySelect("wedstrijd");
		selecteerQuery.stelSorteringIn("datum",false);
		
		//Query voor 'alles'
		ResultSet res = db.select(selecteerQuery);
				
		try {
			while(res.next())
			{
				//Stel de waarden in
				wedstrijdid = res.getInt("wedstrijd_id");
				datum = res.getDate("datum");
				locatie = res.getString("locatie");
				inschrijvingOpen = res.getBoolean("inschrijvingOpen");
				beoordelingOpen = res.getBoolean("beoordelingOpen");
				winnaar_lid_id = res.getInt("winnaar_lid_id");
				//Voeg toe aan de arrayList
				uitvoerLijst.add(new Wedstrijd(wedstrijdid, datum, locatie, inschrijvingOpen, beoordelingOpen, winnaar_lid_id));				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return uitvoerLijst;
	}

	/**
	 * Methode voegWedstrijdToe(), voegt de wedstrijd toe aan de database.
	 * @param wedstrijd
	 */
	public void voegWedstrijdToe(Wedstrijd wedstrijd)
	{
		//Maak een queryInsert aan en vul deze in.		
		queryInsert insertQuery = new queryInsert("wedstrijd");
		insertQuery.stelNieuwIn("datum", wedstrijd.getDatumString());
		insertQuery.stelNieuwIn("locatie", wedstrijd.getLocatie());
		insertQuery.stelNieuwIn("inschrijvingOpen", wedstrijd.isInschrijvingOpen());
		insertQuery.stelNieuwIn("beoordelingOpen", wedstrijd.isBeoordelingOpen());
		insertQuery.stelNieuwIn("winnaar_lid_id",0);
		
		//Voer de query uit.
		int wedstrijd_id=db.insert(insertQuery);
		wedstrijd.setWedstrijd_id(wedstrijd_id);
	}

	
	/**
	 * updateWedstrijd, update het object in de database aan de hand van het wedstrijd_id
	 * @param wedstrijd		Het up te daten wedstrijd object
	 */
	public void updateWedstrijd(Wedstrijd wedstrijd)
	{
		//Vraag het wedstrijd_id op van het wedstrijd-object
		int wedstrijd_id = wedstrijd.getWedstrijd_id();
		
		queryUpdate updateQuery = new queryUpdate("wedstrijd");
		updateQuery.stelVoorwaardeIn("wedstrijd_id", query.GELIJK, wedstrijd_id);
		updateQuery.stelNieuwIn("datum", wedstrijd.getDatumString());
		updateQuery.stelNieuwIn("locatie", wedstrijd.getLocatie());
		updateQuery.stelNieuwIn("inschrijvingOpen", wedstrijd.isInschrijvingOpen());
		updateQuery.stelNieuwIn("beoordelingOpen", wedstrijd.isBeoordelingOpen());
		updateQuery.stelNieuwIn("winnaar_lid_id",wedstrijd.getWinnaar_lid_id());
		
		//Voer de query aan.
		db.update(updateQuery);
	}

}
