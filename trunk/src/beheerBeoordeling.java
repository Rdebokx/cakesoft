import java.util.*;
import java.sql.*;

/**
 * Deze klasse bevat alle methoden om met Beoordeling objecten te werken
 * @author Groep 11
 * @version 2.0
 *
 */
public class beheerBeoordeling {
	
	private Database database;
	
	/**
	 * Dit is de constructor, maakt een nieuw beheerBeoordeling object aan, gekoppelt aan de opgegeven database
	 * @param db	de database waraan het object is gekoppelt
	 */
	public beheerBeoordeling(Database db)
	{
		database = db;
	}
	
	/**
	 * Deze methode voegt een nieuwe beoordeling toe aan de database
	 * en set de beoordeling_id aan de hand van de auto-increment functie van de database
	 * @param beoordeling	bevat het beoordelingsobject dat aan de database moet worden toegevoegd
	 * @param jury			bevat het jury object, waaraan de beoordeling gekoppelt is.
	 * @param baksel		bevat het baksel object, waaraan de beoordeling gekoppelt is.
	 */
	public void voegBeoordelingToe(Beoordeling beoordeling, Jury jury, Baksel baksel)
	{
		String waarden = "," + beoordeling.getCommentaar() + "," + beoordeling.getKwaliteit() + ","; //eerste waarde niet invoeren, is auto-increment
		waarden+= beoordeling.getPrijs() + "," + beoordeling.getCalo() + ",";
		waarden+= beoordeling.getSmaak() + "," + jury.getJury_id() + "," + baksel.getBaksel_id();
		
		//een query construeren
		queryInsert q = new queryInsert("beoordeling");
		q.stelNieuwIn("commentaar", beoordeling.getCommentaar());
		q.stelNieuwIn("kwaliteit", beoordeling.getKwaliteit());
		q.stelNieuwIn("prijs", beoordeling.getPrijs());
		q.stelNieuwIn("calo", beoordeling.getCalo());
		q.stelNieuwIn("smaak", beoordeling.getSmaak());
		q.stelNieuwIn("jury_id", jury.getJury_id());
		q.stelNieuwIn("baksel_id", baksel.getBaksel_id());
		
		//de query uit laten voeren
		int beoordeling_id = database.insert(q);
		beoordeling.setBeoordeling_id(beoordeling_id);
	}
	
	/**
	 * Deze methode retourneert een ArrayList met daarin alle beoordelingen van een baksel, 3 stuks dus.
	 * @param baksel	het baksel waaraan de beoordeling gekoppelt moet zijn
	 * @return			geeft een ArrayList terug, met daarin 3 beoordelingen.
	 */
	public ArrayList<Beoordeling> getBeoordelingenVanBaksel(Baksel baksel)
	{
		ArrayList<Beoordeling> res = new ArrayList<Beoordeling>();
		
		//een query construeren
		querySelect q = new querySelect("beoordeling");
		q.stelVoorwaardeIn("baksel_id", query.GELIJK, baksel.getBaksel_id());
		
		//de queyr uit laten voeren
		ResultSet data = database.select(q);
		
		try
		{
			while(data.next())
			{
				int beoordeling_id = data.getInt("beoordeling_id");
				String commentaar = data.getString("commentaar");
				int kwaliteit = data.getInt("kwaliteit");
				int prijs = data.getInt("prijs");
				int calo = data.getInt("calo");
				int smaak = data.getInt("smaak");
				
				//met deze gegevens een nieuwe beoordeling aanmaken en in de arraylist stoppen
				Beoordeling bdeling = new Beoordeling(beoordeling_id, commentaar, kwaliteit, prijs, calo, smaak);
				res.add(bdeling);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e.toString());
		}
		return res;
	}
	/**
	 * Deze klasse haalt een specifieke beoordeling op uit de database, en retourneert die.
	 * @param jury		het object van door welke jury de beoordeling gegeven is.
	 * @param baksel	aan welk baksel de beoordeling gekoppelt moet zijn.
	 * @return			geeft een specifieke beoordeling terug.
	 */
	public Beoordeling getBeoordelingVanJuryVoorBaksel(Jury jury, Baksel baksel)
	{		
		//query construeren
		querySelect q = new querySelect("beoordeling");
		q.stelVoorwaardeIn("jury_id", query.GELIJK, jury.getJury_id());
		q.stelVoorwaardeIn("baksel_id", query.GELIJK, baksel.getBaksel_id());
		
		ResultSet data = database.select(q);
		Beoordeling res = new Beoordeling();
		
		try
		{
			res.setBeoordeling_id(data.getInt("beoordeling_id"));
			res.setCommentaar(data.getString("commentaar"));
			res.setKwaliteit(data.getInt("kwaliteit"));
			res.setPrijs(data.getInt("prijs"));
			res.setCalo(data.getInt("calo"));
			res.setSmaak(data.getInt("smaak"));
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e.toString());
		}
		return res;
	}

}
