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
		
		//Query voor 'alles'
		ResultSet res = db.executeQuery("SELECT * FROM `wedstrijd`");
				
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

	/**
	 * Methode voegWedstrijdToe(), voegt de wedstrijd toe aan de database.
	 * @param wedstrijd
	 */
	public void voegWedstrijdToe(Wedstrijd wedstrijd)
	{
		//Prop alle argumenten in één String en voer de query uit.
		String argumenten = "(" + String.valueOf(wedstrijd.getWedstrijd_id()) + ", " + wedstrijd.getDatumString() + ", " + wedstrijd.getLocatie() + ", " + 
				String.valueOf(wedstrijd.isInschrijvingOpen()) + ", " + String.valueOf(wedstrijd.isBeoordelingOpen()) + ")";
		db.executeQuery("INSERT INTO wedstrijd VALUES " + argumenten);
	}

	
	/**
	 * updateWedstrijd, update het object in de database aan de hand van het wedstrijd_id
	 * @param wedstrijd		Het up te daten wedstrijd object
	 */
	public void updateWedstrijd(Wedstrijd wedstrijd)
	{
		int wedstrijd_id = wedstrijd.getWedstrijd_id();
		String waarden = "datum = `" + wedstrijd.getDatumString() + "`, locatie = `" + wedstrijd.getLocatie() + 
				"`, inschrijvingOpen = `" + String.valueOf(wedstrijd.isInschrijvingOpen()) + "`, beoordelingOpen = `" 
				+ String.valueOf(wedstrijd.isBeoordelingOpen());

		db.executeQuery("UPDATE wedstrijd SET " + waarden + " WHERE p_wedstrijd_id = " + String.valueOf(wedstrijd_id));
	}

}
