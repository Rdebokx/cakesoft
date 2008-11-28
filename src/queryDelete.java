
public class queryDelete extends query
{
	
	public queryDelete(String tabel)
	{
		super(tabel);
	}
	
	public String getQuery()
	{
		int i;
		String query="DELETE FROM "+this.tabel+" WHERE ";
		
		for(i=0;i<this.voorwaarden.size();i++)
			query+=this.voorwaarden.get(i)+(i<this.voorwaarden.size()-1?" AND ":"");
		
		
		return query;
	}
	
	
}
