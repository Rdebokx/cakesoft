import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


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
	
	public query(String tabel)
	{
		this.tabel=tabel;
	}
	
	public void stelVoorwaardeIn(Object kolom, String operator, Object waarde)
	{
		this.voorwaarden.add(new queryVoorwaarde(kolom,operator,waarde));
	}
	
	public void stelVoorwaardeIn(queryVoorwaarde voorwaarde)
	{
		this.voorwaarden.add(voorwaarde);
	}
	
	public void stelNieuwIn(String kolom, Object waarde)
	{
		this.nieuwKolommen.add(kolom);
		this.nieuwWaardes.add(query.objectToString(waarde));
	}
	
	public void stelSorteringIn(String kolom, boolean oplopend)
	{
		this.sorteerKolom=kolom;
		this.sorteerAsc=oplopend;
	}
	
	public static String objectToString(Object waarde)
	{
		String strWaarde="";
		
		if(waarde instanceof Integer)
			strWaarde=Integer.toString((Integer)waarde);
		
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
			//TODO: werkend maken
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
