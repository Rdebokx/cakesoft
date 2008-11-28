
public class queryInsert extends query
{
	
	public queryInsert(String tabel)
	{
		super(tabel);
	}
	
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
