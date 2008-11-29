//Bijna af - Benjamin

import java.util.*;
import java.sql.*;

public class beheerBestelling
{
	private Database datab;
	
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
		querySelect query3 = new querySelect("bestelling, deelnemer, baksel");
		query3.stelVoorwaardeIn("deelnemer.lid_id",query.GELIJK,lid.getLid_id());
		query3.stelLinkVoorwaardeIn("bestelling.baksel_id",query.GELIJK,"deelnemer.baksel_id");
		query3.stelLinkVoorwaardeIn("baksel.baksel_id",query.GELIJK,"deelnemer.baksel_id");
		
		querySelect query4;
		ResultSet res = datab.select(query3);
		ResultSet res2;
		int aantal;
		int bestelling_id;
		int baksel_id;
		String ingredienten;
		String recept;
		String naam;
		String categorie;
		double prijs;
		Bestelling bestelling;
		try
		{
			while (res.next())
			{
				aantal = res.getInt("aantal");
				bestelling_id = res.getInt("bestelling_id");

				//Geen while loop nodig voor zover ik weet, aangezien je nooit meer dan één baksel terug krijgt.
				/*querySelect query4 = new querySelect("baksel, bestelling");
				query4.stelVoorwaardeIn("baksel.baksel_id",query.GELIJK,"bestelling.baksel_id");
				query4.stelVoorwaardeIn("bestelling.bestelling_id",query.GELIJK,bestelling_id);
				res2 = datab.select(query4);*/
				baksel_id = res.getInt("baksel_id");
				naam = res.getString("naam");
				prijs = res.getDouble("prijs");
				categorie = res.getString("categorie");
				ingredienten = res.getString("ingredienten");
				recept = res.getString("recept");
				
				
				query4=new querySelect("lid");
				query4.stelVoorwaardeIn("lid_id",query.GELIJK,res.getInt("lid_id"));
				res2=datab.select(query4);
				res2.next();
				
				Lid lid_besteller=new Lid(res2.getString("naam"),res2.getInt("lid_id"),res2.getString("wachtwoord"),res2.getInt("hoofdbeheer")==1);
				
				bestelling=new Bestelling(aantal, bestelling_id, new Baksel(baksel_id, ingredienten, recept, naam, categorie, prijs));
				bestelling.setLid_bakker(lid);
				bestelling.setLid_besteller(lid_besteller);
				
				bestelList.add(bestelling);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return bestelList;
	}
	
	/**
	 * getBestellingenInkomend, geeft een lijst van alle bestellingen die een lid geplaatst heeft
	 * @param lid	Bevat het Lid dat de zoekopdracht uitvoert
	 * @return			Geeft een ArrayList terug met alle bestellingen die door dit lid geplaatst zijn
	 */
	public ArrayList<Bestelling> getBestellingenUitgaand(Lid lid)
	{
		ArrayList<Bestelling> besteldList = new ArrayList<Bestelling>();
		querySelect query3 = new querySelect("bestelling, baksel");
		query3.stelVoorwaardeIn("bestelling.lid_id",query.GELIJK,lid.getLid_id());
		query3.stelLinkVoorwaardeIn("baksel.baksel_id",query.GELIJK,"bestelling.baksel_id");

		ResultSet res = datab.select(query3);
		ResultSet res2;

		int aantal;
		int bestelling_id;
		int baksel_id;
		String ingredienten;
		String recept;
		String naam;
		String categorie;
		double prijs;
		Bestelling bestelling;
		querySelect query4;
		
		try
		{
			while (res.next())
			{
				aantal = res.getInt("aantal");
				bestelling_id = res.getInt("bestelling_id");

				//Geen while loop nodig voor zover ik weet, aangezien je nooit meer dan één baksel terug krijgt.
				//query4 = new querySelect("baksel");
				//query4.stelVoorwaardeIn("baksel.baksel_id",query.GELIJK,bestelling_id);
				
				//res2 = datab.select(query4);
				baksel_id = res.getInt("baksel_id");
				naam = res.getString("naam");
				prijs = res.getDouble("prijs");
				categorie = res.getString("categorie");
				ingredienten = res.getString("ingredienten");
				recept = res.getString("recept");
								
				query4=new querySelect("lid, deelnemer");
				query4.stelVoorwaardeIn("deelnemer.baksel_id",query.GELIJK,baksel_id);
				query4.stelLinkVoorwaardeIn("deelnemer.lid_id",query.GELIJK,"lid.lid_id");
				res2=datab.select(query4);
				res2.next();
				
				Lid lid_bakker=new Lid(res2.getString("naam"),res2.getInt("lid_id"),res2.getString("wachtwoord"),res2.getInt("hoofdbeheer")==1);
				
				bestelling=new Bestelling(aantal, bestelling_id, new Baksel(baksel_id, ingredienten, recept, naam, categorie, prijs));
				bestelling.setLid_besteller(lid);
				bestelling.setLid_bakker(lid_bakker);
				
				besteldList.add(bestelling);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return besteldList;
	}
	
	/**
	 * voegBestellingToe, voegt een bestelling toe aan de database
	 * @param lbestelling	Bevat de bestelling die toegevoegd moet worden
	 */
	public void voegBestellingToe(Bestelling bestelling)
	{
		queryInsert query1 = new queryInsert("bestelling");
		query1.stelNieuwIn("aantal",bestelling.getAantal());
		query1.stelNieuwIn("baksel_id",bestelling.getBaksel().getBaksel_id());
		query1.stelNieuwIn("lid_id",bestelling.getLid_besteller().getLid_id());
		datab.insert(query1);
	}
	
	/**
	 * verwijderBestelling, verwijdert een bestelling uit de database
	 * @param bestelling	Bevat de bestelling die verwijderd moet worden
	 */
	public void verwijderBestelling(Bestelling bestelling)
	{
		queryDelete query3=new queryDelete("bestelling");
		query3.stelVoorwaardeIn("bestelling_id",query.GELIJK,bestelling.getBestelling_id());
		//query3.stelVoorwaardeIn("aantal",query.GELIJK,bestelling.getAantal());
		//query3.stelVoorwaardeIn("lid_id",query.GELIJK,bestelling.getLid().getLid_id());
		datab.delete(query3);
	}
}

