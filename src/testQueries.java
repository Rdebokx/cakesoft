
public class testQueries {
	public static void main(String[] args)
	{
		//inserten
		queryInsert query1=new queryInsert("tabelnaam");
		query1.stelNieuwIn("bericht",1==1);
		query1.stelNieuwIn("bericht2","Da's de winnaar ook");
		
		System.out.println(query1.getQuery());
		
		//updaten
		queryUpdate query2=new queryUpdate("tabelnaam");
		query2.stelVoorwaardeIn("eenkolom",query.GELIJK,"iets");
		query2.stelVoorwaardeIn("nogeenkolom",query.KLEINER_DAN,3);
		query2.stelNieuwIn("andereKolom","ietsAnders");
		
		System.out.println(query2.getQuery());
		
		//selecteren
		querySelect query3=new querySelect("tabelnaam");
		query3.stelVoorwaardeIn("eenkolom",query.LIKE,"%iets%");
		query3.stelSorteringIn("datum",false);
		
		System.out.println(query3.getQuery());
		
		
		//verwijderen
		queryDelete query4=new queryDelete("tabelnaam");
		query4.stelVoorwaardeIn("eenkolom",query.LIKE,"%iets%");
		
		
		System.out.println(query4.getQuery());
		
		//selecteren met moeilijke voorwaarden
		querySelect query5=new querySelect("tabelnaam");
		queryVoorwaarde vw1=new queryVoorwaarde("naam",query.GELIJK,"jan");
		queryVoorwaarde vw2=new queryVoorwaarde("naam",query.GELIJK,"piet");
		query5.stelVoorwaardeIn(vw1,query.OF,vw2);
		
		System.out.println(query5.getQuery());
		
	}
}
