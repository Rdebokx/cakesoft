
public class queryUpdate extends query
{
	
	public queryUpdate(String tabel)
	{
		super(tabel);
	}
	
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
