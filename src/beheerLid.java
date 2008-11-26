import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


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
		
		ResultSet res = db.query("lid", "naam", "%" + naam_deel + "%");
				
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

	public Lid getLidVanReactie(Reactie reactie)
	{
		int reactie_id = reactie.getReactie_id();
		int lid_id = -1;
		String naam = null;
		String wachtwoord = null;
		boolean hoofdbeheerder = false;
		Lid uitvoer = null;
		
		ResultSet res = db.query("reactie", "p_reactie_id", String.valueOf(reactie_id));
		
		try {
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
	//post:: geeft het lid terug dat reactie heeft geplaatst

	public Lid getLidDoorLogin(Lid lid)
	{
		return null;
	}
	//Pre: Het object lid bevat lid_id en wachtwoord die waarden zijn die de gebruiker in heeft gevuld
	////post:: geeft null als dit geen goede login is, anders het Lid object voor deze lid_id 
}
