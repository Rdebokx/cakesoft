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
		
		querySelect q2;
		ResultSet res2;
		Jury jury;
		int beoordeling_id, kwaliteit, prijs, calo, smaak;
		Beoordeling beoordeling;
		
		try
		{
			while(data.next())
			{
				beoordeling_id = data.getInt("beoordeling_id");
				String commentaar = data.getString("commentaar");
				kwaliteit = data.getInt("kwaliteit");
				prijs = data.getInt("prijs");
				calo = data.getInt("calo");
				smaak = data.getInt("smaak");
				
				//met deze gegevens een nieuwe beoordeling aanmaken en in de arraylist stoppen
				beoordeling = new Beoordeling(beoordeling_id, commentaar, kwaliteit, prijs, calo, smaak);
				
				q2 = new querySelect("jury, lid");
				q2.stelVoorwaardeIn("jury.jury_id", query.GELIJK, data.getInt("jury_id"));
				q2.stelLinkVoorwaardeIn("jury.lid_id",query.GELIJK,"lid.lid_id");
				res2 = database.select(q2);
				res2.next();
				
				jury = new Jury(res2.getInt("jury_id"), res2.getString("naam"), res2.getInt("lid_id"), res2.getString("wachtwoord"),res2.getBoolean("hoofdbeheer"));
				beoordeling.setJury(jury);
				
				res.add(beoordeling);
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
		Beoordeling res=null;
		
		try
		{
			if(data.next())
			{
				res = new Beoordeling();
				res.setBeoordeling_id(data.getInt("beoordeling_id"));
				res.setCommentaar(data.getString("commentaar"));
				res.setKwaliteit(data.getInt("kwaliteit"));
				res.setPrijs(data.getInt("prijs"));
				res.setCalo(data.getInt("calo"));
				res.setSmaak(data.getInt("smaak"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e.toString());
		}
		return res;
	}

}
