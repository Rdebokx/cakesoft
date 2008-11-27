import java.util.*;
import java.sql.*;

public class beheerBaksel
{
	Database datab;
	
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
		datab.insert("INSERT into Baksel (naam, categorie, prijs, ingredienten, recept) VALUES (" + baksel.getNaam() + baksel.getPrijs() + baksel.getCategorie() + baksel.getIngredienten() + baksel.getRecept() + ")");
	}

    /**
	 * getBakselsVanWedstrijd, geeft een lijst van alle baksels in een wedstrijd
	 * @param wedstrijd	Bevat de wedstrijd waarvan men de deelnemende baksels wilt hebben
	 * @return			Geeft een ArrayList terug met alle baksels van die wedstrijd
	 */
	public ArrayList<Baksel> getBakselsVanWedstrijd(Wedstrijd wedstrijd)
	{
		ArrayList<Baksel> bakselList = new ArrayList<Baksel>();
		ResultSet res = datab.executeQuery("Select * FROM Baksel WHERE Baksel.baksel_id = Deelnemer.baksel_id AND Deelnemer.wedstrijd_id = " + wedstrijd.getWedstrijd_id());
		try
		{
			while (res.next())
			{
				int baksel_id = res.getInt("baksel_id");
				String naam = res.getString("naam");
				double prijs = res.getDouble("prijs");
				String categorie = res.getString("categorie");
				String ingredienten = res.getString("ingredienten");
				String recept = res.getString("recept");
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
		ResultSet res = datab.executeQuery("Select * FROM Deelnemer, Lid WHERE Deelnemer.lid_id = Lid.lid_id AND Deelnemer.wedstrijd_id = " + wedstrijd.getWedstrijd_id());
		try
		{
			while (res.next())
			{
				int deelnemer_id = res.getInt("deelnemer_id");
				int lid_id = res.getInt("lid_id");
				String naam = res.getString("naam");
				String wachtwoord = res.getString("wachtwoord");
				boolean hoofdbeheer = res.getBoolean("hoofdbeheer");
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



