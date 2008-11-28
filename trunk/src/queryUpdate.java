/**
 * queryUpdate, beschrijft een update-query op basis van de klasse query.
 * @author Groep 11
 */
public class queryUpdate extends query
{
	
	/**
	 * Constructor, maakt een nieuw queryUpdate-object aan met de gegeven tabel doorgegeven aan de hoofdklasse.
	 * @param tabel	De te gebruiken tabel
	 */
	public queryUpdate(String tabel)
	{
		super(tabel);
	}
	
	/**
	 * getQuery, geeft de ingestelde query terug in de vorm UPDATE tabel SET waarden WHERE voorwaarden.
	 * @return		String-representatie van de query in de vorm "UPDATE tabel SET waarden WHERE voorwaarden"
	 */
	public String getQuery()
	{
		int i;
		String query="UPDATE "+this.tabel+" SET ";
		
		for(i=0;i<this.nieuwKolommen.size();i++)
			query+=this.nieuwKolommen.get(i)+"="+this.nieuwWaardes.get(i)+(i<this.nieuwKolommen.size()-1?",":"");
		
		query+=" WHERE ";
		
		for(i=0;i<this.voorwaarden.size();i++)
			query+=this.voorwaarden.get(i)+(i<this.voorwaarden.size()-1?" AND ":"");
		
		return query;
	}
	
	
}
