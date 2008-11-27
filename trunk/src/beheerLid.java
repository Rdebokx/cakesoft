import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Yorick
 * beheerWLid klasse, beheert de Leden.
 */
public class beheerLid {
	Database db;

	/**
	 * Constructor van beheerLid, stelt db in.
	 * @param db	De in te stellen database.
	 */
	public beheerLid(Database db)
	{
		this.db = db;
	}

	/**
	 * zoekOpNaam, zoekt op deel van naam van Lid.
	 * @param naam_deel		Deel van de naam om op te zoeken
	 * @return				ArrayList met Lid objecten die voldoen aan de eisen.
	 */
	public ArrayList<Lid> zoekOpNaam(String naam_deel)
	{
		ArrayList<Lid> uitvoerLid = new ArrayList<Lid>();
		int lid_id = -1;
		String naam = null;
		String wachtwoord = null;
		boolean hoofdbeheerder = false;
		
		ResultSet res = db.executeQuery("SELECT * FROM lid WHERE naam LIKE `%" + naam_deel + "%`");
				
		try {
			while(res.next())
			{
				lid_id = res.getInt("p_lid_id");
				naam = res.getString("naam");
				wachtwoord = res.getString("wachtwoord");
				hoofdbeheerder = res.getBoolean("hoofdbeheerder");
				uitvoerLid.add(new Lid(naam, lid_id, wachtwoord, hoofdbeheerder));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return uitvoerLid;
	}

	/**
	 * getLidVanReactie, geeft het Lid terug waaraan de reactie toebehoort.
	 * @param reactie	Reactie waarvan het Lid opgevraagd wordt.
	 * @return			Lid die bij de reactie hoort.
	 */
	public Lid getLidVanReactie(Reactie reactie)
	{
		int reactie_id = reactie.getReactie_id();
		int lid_id = -1;
		String naam = null;
		String wachtwoord = null;
		boolean hoofdbeheerder = false;
		Lid uitvoer = null;
		
		try {
			ResultSet res = db.select("reactie", "p_reactie_id = " + String.valueOf(reactie_id));
			
			lid_id = res.getInt("p_lid_id");
			naam = res.getString("naam");
			wachtwoord = res.getString("wachtwoord");
			hoofdbeheerder = res.getBoolean("hoofdbeheerder");
			
			uitvoer = new Lid(naam, lid_id, wachtwoord, hoofdbeheerder);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return uitvoer;
	}

	/**
	 * getLidDoorLogin, controleert het loginid en wachtwoord van het geven Lid, geeft een Lid terug als het klopt, zo niet null. 
	 * @param lid
	 * @return
	 */
	public Lid getLidDoorLogin(Lid lid)
	{
		int lid_id = -1;
		String naam = null;
		String wachtwoord = null;
		boolean hoofdbeheerder = false;
		Lid uitvoer = null;
		
		try {
			ResultSet res = db.select("lid", "p_lid_id = " + String.valueOf(lid.getLid_id()) + " AND wachtwoord = " + lid.getLid_id());
			
			while(res.next())
			{
				lid_id = res.getInt("p_lid_id");
				naam = res.getString("naam");
				wachtwoord = res.getString("wachtwoord");
				hoofdbeheerder = res.getBoolean("hoofdbeheerder");
				
				uitvoer = new Lid(naam, lid_id, wachtwoord, hoofdbeheerder);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return uitvoer;
	}
}
