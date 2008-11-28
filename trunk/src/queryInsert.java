/**
 * queryInsert, beschrijft een insert query op basis van de query klasse.
 * @author Groep 11
 */
public class queryInsert extends query
{
	
	/**
	 * Constructor voor de query, maakt een nieuwe Query aan en geeft de tabel door aan de hoofdklasse.
	 * @param tabel	De door te geven tabel.
	 */
	public queryInsert(String tabel)
	{
		super(tabel);
	}
	
	/**
	 * getQuery, geeft de opgegeven query terug in het formaat INSERT INTO tabel VALUES (waarden)
	 * @return	String in het formaat INSERT INTO tabel VALUES (waarden)
	 */
	public String getQuery()
	{
		int i;
		String query="INSERT INTO "+this.tabel+" (";
		
		for(i=0;i<this.nieuwKolommen.size();i++)
			query+=this.nieuwKolommen.get(i)+(i<this.nieuwKolommen.size()-1?",":"");
		
		query+=") VALUES(";
		
		for(i=0;i<this.nieuwWaardes.size();i++)
			query+=this.nieuwWaardes.get(i)+(i<this.nieuwWaardes.size()-1?",":"");
		
		query+=")";
		
		return query;
	}
	
	
}
