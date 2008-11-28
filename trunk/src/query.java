import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * query klasse, beschrijft een query en handelt het controleren van de waarden af om zo SQL-inserts en onbedoelde fouten met 
 * betrekking tot het gebruik van quotes e.d.
 * @author Groep 11
 */
public class query
{
	public static final String GELIJK = "=";
	public static final String KLEINER_DAN = "<";
	public static final String GROTER_DAN = ">";
	public static final String LIKE = " LIKE ";
	public static final String EN = " *en* ";
	public static final String OF = " *of* ";
	
	protected String tabel;
	protected ArrayList<queryVoorwaarde> voorwaarden=new ArrayList<queryVoorwaarde>();
	protected ArrayList<String> nieuwKolommen=new ArrayList<String>();
	protected ArrayList<String> nieuwWaardes=new ArrayList<String>();
	protected String sorteerKolom="";
	protected boolean sorteerAsc=true;
	
	/**
	 * Constructor, stelt de tabel in
	 * @param tabel	De in te stellen tabelnaam
	 */
	public query(String tabel)
	{
		this.tabel=tabel;
	}
	
	/**
	 * selVoorwaardeIn, stelt de gegeven voorwaarde in.
	 * @param kolom		Kolomnaam
	 * @param operator	Operator, zoals query.LIKE, query.LIKE en query.KLEINER_DAN
	 * @param waarde	De te controleren waarde.
	 */
	
	public void stelLinkVoorwaardeIn(Object kolom, String operator, Object waarde)
	{
		this.voorwaarden.add(new queryVoorwaarde(kolom,operator,waarde,true));
	}
	
	public void stelVoorwaardeIn(Object kolom, String operator, Object waarde)
	{
		this.voorwaarden.add(new queryVoorwaarde(kolom,operator,waarde));
	}
	
	/**
	 * stelVoorwaardeIn, stelt de gegeven queryVoorwaarde in.
	 * @param voorwaarde	De in te stellen queryVoorwaarde
	 */
	public void stelVoorwaardeIn(queryVoorwaarde voorwaarde)
	{
		this.voorwaarden.add(voorwaarde);
	}
	
	/**
	 * stelNieuweIn, voegt een nieuwe kolomwaarde in.
	 * @param kolom		De betreffende kolom.
	 * @param waarde	De in te stellen waarde.
	 */
	public void stelNieuwIn(String kolom, Object waarde)
	{
		this.nieuwKolommen.add(kolom);
		this.nieuwWaardes.add(query.objectToString(waarde));
	}
	
	/**
	 * stelSorteringIn, stelt de gegeven sortering in op de kolom
	 * @param kolom		Naam van de kolom.
	 * @param oplopend	Oplopend, true; Aflopend false.
	 */
	public void stelSorteringIn(String kolom, boolean oplopend)
	{
		this.sorteerKolom=kolom;
		this.sorteerAsc=oplopend;
	}
	
	/**
	 * objectToString, maakt een String van het gegeven object.
	 * @param waarde	De om te zetten waarde.
	 * @return			Een string-representatie van het object, afhankelijk van het type object.
	 */
	public static String objectToString(Object waarde)
	{
		String strWaarde="";
		
		if(waarde instanceof Integer)
			strWaarde=Integer.toString((Integer)waarde);
		
		if(waarde instanceof Double)
			strWaarde=Double.toString((Double)waarde);
		
		if(waarde instanceof String)
		{
			strWaarde=(String)waarde;
			strWaarde=strWaarde.replaceAll("`","\\\\`");
			strWaarde=strWaarde.replaceAll("'","\\\\\'");
			strWaarde=strWaarde.replaceAll("\"","\\\\\"");
			strWaarde="'"+strWaarde+"'";
		}
		
		if(waarde instanceof Date)
		{
			Date uitvoerDatum = (Date)waarde;
			
			SimpleDateFormat dateformatYYYYMMDD = new SimpleDateFormat("yyyyMMdd");
			//Maak een nieuwe String aan met de datum in ons format
			String datumYYYYMMDD = new String( dateformatYYYYMMDD.format( uitvoerDatum ) );

			//Geef een String terug
			strWaarde = "'" + datumYYYYMMDD.toString() + "'";
		}
		
		return strWaarde;
	}
}