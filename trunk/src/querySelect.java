/**
 * querySelect, specificeert een select-query op basis van de query klasse.
 * @author Groep 11
 */
public class querySelect extends query
{
	
	/**
	 * Constructor voor querySelect, maakt een querySelect aan en geeft de tabel door aan de query klasse.
	 * @param tabel	De te gebruiken tabel.
	 */
	public querySelect(String tabel)
	{
		super(tabel);
	}
	
	
	
	/**
	 * getQuery, geeft de opgegeven query terug, compleet met escapen van quotes in de vorm SELECT * FROM tabel WHERE voorwaarden.
	 * @return	String-representatie van de query in de vorm SELECT * FROM tabel WHERE voorwaarden
	 */
	public String getQuery()
	{
		int i;
		String query="SELECT * FROM " + this.tabel + " WHERE ";
		
		for(i=0;i<this.voorwaarden.size();i++)
			query+=this.voorwaarden.get(i)+(i<this.voorwaarden.size()-1?" AND ":"");
		
		if(!this.sorteerKolom.equals(""))
			query+=" ORDER BY "+this.sorteerKolom+(this.sorteerAsc?"":" DESC");
		
		return query;
	}
	
	
}
