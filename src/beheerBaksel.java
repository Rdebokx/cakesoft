import java.util.*;
import java.sql.*;

public class beheerBaksel
{
	private Database datab;

	/**
	 * Constructor, maakt een nieuw beheerBaksel object aan
	 * @param db	Bevat het Database object waar deze klasse mee moet werken
	 */
	public beheerBaksel(Database db)
	{
		datab = db;
	}

    /**
	 * voegBakselToe, voegt een baksel toe aan de database
	 * @param baksel	Bevat het baksel dat ingevoerd moet worden
	 */
	public void voegBakselToe(Baksel baksel)
	{
		datab.insert("Baksel", "" + baksel.getNaam() + baksel.getPrijs() + baksel.getCategorie() + baksel.getIngredienten() + baksel.getRecept());
	}

    /**
	 * getBakselsVanWedstrijd, geeft een lijst van alle baksels in een wedstrijd
	 * @param wedstrijd	Bevat de wedstrijd waarvan men de deelnemende baksels wilt hebben
	 * @return			Geeft een ArrayList terug met alle baksels van die wedstrijd
	 */
	public ArrayList<Baksel> getBakselsVanWedstrijd(Wedstrijd wedstrijd)
	{
		ArrayList<Baksel> bakselList = new ArrayList<Baksel>();
		ResultSet res = datab.select("Baksel", "Baksel.baksel_id = Deelnemer.baksel_id AND Deelnemer.wedstrijd_id = " + wedstrijd.getWedstrijd_id());
		int baksel_id;
		String naam;
		double prijs;
		String categorie;
		String ingredienten;
		String recept;
		try
		{
			while (res.next())
			{
				baksel_id = res.getInt("baksel_id");
				naam = res.getString("naam");
				prijs = res.getDouble("prijs");
				categorie = res.getString("categorie");
				ingredienten = res.getString("ingredienten");
				recept = res.getString("recept");
				bakselList.add(new Baksel(baksel_id, ingredienten, recept, naam, categorie, prijs));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return bakselList;
	}
	
	/**
	 * getDeelnemersVanWedstrijd, geeft een lijst die alle deelnemers van een wedstrijd bevat.
	 * @param wedstrijd	Bevat de wedstrijd waarvan men de deelnemers wilt weten
	 * @return			Geeft een ArrayList terug met alle Deelnemers van de wedstrijd
	 */
	public ArrayList<Deelnemer> getDeelnemersVanWedstrijd(Wedstrijd wedstrijd)
	{
		ArrayList<Deelnemer> deelList = new ArrayList<Deelnemer>();
		ResultSet res = datab.select("Deelnemer, Lid", "Deelnemer.lid_id = Lid.lid_id AND Deelnemer.wedstrijd_id = " + wedstrijd.getWedstrijd_id());
        int deelnemer_id;
        int lid_id;
        String naam;
        String wachtwoord;
        boolean hoofdbeheer;
		try
		{
			while (res.next())
			{
				deelnemer_id = res.getInt("deelnemer_id");
				lid_id = res.getInt("lid_id");
				naam = res.getString("naam");
				wachtwoord = res.getString("wachtwoord");
				hoofdbeheer = res.getBoolean("hoofdbeheer");
				deelList.add(new Deelnemer(deelnemer_id, naam, lid_id, wachtwoord, hoofdbeheer));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return deelList;
	}
}



