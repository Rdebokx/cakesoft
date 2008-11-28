//Bijna af - Benjamin

import java.util.*;
import java.sql.*;

public class beheerBestelling
{
	Database datab;
	
	/**
	 * Constructor, maakt een nieuw beheerBestelling object aan
	 * @param db	Bevat het Database object waar deze klasse mee moet werken
	 */
	public beheerBestelling(Database db)
	{
		datab = db;
	}
	
	/**
	 * getBestellingenInkomend, geeft een lijst van alle bestellingen die bij een lid zijn geplaatst
	 * @param lid	Bevat het Lid dat de zoekopdracht uitvoert
	 * @return			Geeft een ArrayList terug met alle bestellingen die bij dit lid geplaatst zijn
	 */
	public ArrayList<Bestelling> getBestellingenInkomend(Lid lid)
	{
		ArrayList<Bestelling> bestelList = new ArrayList<Bestelling>();
		ResultSet res = datab.select("Bestelling", "Bestelling.baksel_id = Deelnemer.baksel_id AND Deelnemer.lid_id = Lid.lid_id AND Lid.lid_id = " + lid.getLid_id());
		ResultSet res2;
		int aantal;
		int bestelling_id;
		int baksel_id;
		String ingredienten;
		String recept;
		String naam;
		String categorie;
		double prijs;
		try
		{
			while (res.next())
			{
				aantal = res.getInt("aantal");
				bestelling_id = res.getInt("bestelling_id");

				//Geen while loop nodig voor zover ik weet, aangezien je nooit meer dan één baksel terug krijgt.
				res2 = datab.select("Baksel", "Baksel.baksel_id = Bestelling.baksel_id AND Bestelling.bestelling_id = " + bestelling_id);
				baksel_id = res2.getInt("baksel_id");
				naam = res2.getString("naam");
				prijs = res2.getDouble("prijs");
				categorie = res2.getString("categorie");
				ingredienten = res2.getString("ingredienten");
				recept = res2.getString("recept");
				bestelList.add(new Bestelling(aantal, bestelling_id, new Baksel(baksel_id, ingredienten, recept, naam, categorie, prijs)));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return bestelList;
	}
	
	/**
	 * getBestellingenInkomend, geeft een lijst van alle bestellingen die bij een lid zijn geplaatst
	 * @param lid	Bevat het Lid dat de zoekopdracht uitvoert
	 * @return			Geeft een ArrayList terug met alle bestellingen die bij dit lid geplaatst zijn
	 */
	public ArrayList<Bestelling> getBestellingenUitgaand(Lid lid)
	{
		ArrayList<Bestelling> bestelList = new ArrayList<Bestelling>();
		ResultSet res = datab.select("Bestelling", "Bestelling.baksel_id = Deelnemer.baksel_id AND Deelnemer.lid_id = Lid.lid_id AND Lid.lid_id = " + lid.getLid_id());
		ResultSet res2;
		int aantal;
		int bestelling_id;
		int baksel_id;
		String ingredienten;
		String recept;
		String naam;
		String categorie;
		double prijs;
		try
		{
			while (res.next())
			{
				aantal = res.getInt("aantal");
				bestelling_id = res.getInt("bestelling_id");

				//Geen while loop nodig voor zover ik weet, aangezien je nooit meer dan één baksel terug krijgt.
				res2 = datab.select("Baksel", "Baksel.baksel_id = Bestelling.baksel_id AND Bestelling.bestelling_id = " + bestelling_id);
				baksel_id = res2.getInt("baksel_id");
				naam = res2.getString("naam");
				prijs = res2.getDouble("prijs");
				categorie = res2.getString("categorie");
				ingredienten = res2.getString("ingredienten");
				recept = res2.getString("recept");
				bestelList.add(new Bestelling(aantal, bestelling_id, new Baksel(baksel_id, ingredienten, recept, naam, categorie, prijs)));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return bestelList;
	}
}

