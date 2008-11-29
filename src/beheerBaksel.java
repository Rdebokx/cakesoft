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
		queryInsert query1 = new queryInsert("baksel");
		query1.stelNieuwIn("naam",baksel.getNaam());
		query1.stelNieuwIn("prijs",baksel.getPrijs());
		query1.stelNieuwIn("categorie",baksel.getCategorie());
		query1.stelNieuwIn("ingredienten",baksel.getIngredienten());
		query1.stelNieuwIn("recept",baksel.getRecept());
		

		datab.insert(query1);
	}

    /**
	 * getBakselsVanWedstrijd, geeft een lijst van alle baksels in een wedstrijd
	 * @param wedstrijd	Bevat de wedstrijd waarvan men de deelnemende baksels wilt hebben
	 * @return			Geeft een ArrayList terug met alle baksels van die wedstrijd
	 */
	public ArrayList<Baksel> getBakselsVanWedstrijd(Wedstrijd wedstrijd)
	{
		ArrayList<Baksel> bakselList = new ArrayList<Baksel>();

		querySelect query3=new querySelect("baksel"); 
		query3.stelVoorwaardeIn("baksel.baksel_id", query.GELIJK, "deelnemer.baksel_id");
		query3.stelVoorwaardeIn("deelnemer.wedstrijd_id", query.LIKE, wedstrijd.getWedstrijd_id());

		ResultSet res = datab.select(query3);
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

}