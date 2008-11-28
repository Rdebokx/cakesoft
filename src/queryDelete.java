/**
 * queryDelete, bevat een query op basis van de ingestelde voorwaarden.
 * @author Groep 11
 */
public class queryDelete extends query
{
	
	/**
	 * Constructor voor queryDelete, maakt een queryDelete aan op basis van de gegeven tabel en geeft deze door.
	 * @param tabel	De te gebruiken tabel.
	 */
	public queryDelete(String tabel)
	{
		super(tabel);
	}
	
	/**
	 * getQuery, geeft de gegeven query terug in de vorm . 
	 * @return	String van de vorm DELETE FROM tabel WHERE voorwaarden
	 */
	public String getQuery()
	{
		int i;
		String query="DELETE FROM "+this.tabel+" WHERE ";
		
		for(i=0;i<this.voorwaarden.size();i++)
			query+=this.voorwaarden.get(i)+(i<this.voorwaarden.size()-1?" AND ":"");
		
		return query;
	}
	
	
}
