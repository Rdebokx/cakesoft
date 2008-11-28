
public class querySelect extends query
{
	
	public querySelect(String tabel)
	{
		super(tabel);
	}
	
	
	
	public String getQuery()
	{
		int i;
		String query="SELECT * FROM "+this.tabel+" WHERE ";
		
		for(i=0;i<this.voorwaarden.size();i++)
			query+=this.voorwaarden.get(i)+(i<this.voorwaarden.size()-1?" AND ":"");
		
		if(!this.sorteerKolom.equals(""))
			query+=" ORDER BY "+this.sorteerKolom+(this.sorteerAsc?"":" DESC");
		
		return query;
	}
	
	
}
