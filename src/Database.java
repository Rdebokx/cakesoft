import java.sql.*;

/**
 * klasse Database zorgt voor de interactie tussen het programma en de database.
 * Hij maakt verbinding en voert querys uit.
 *@author Groep 11
 *@version 1.0
 */
public class Database {
	
	private Connection connection;
	private String url;
	private String username;
	private String password;
	
	private String driver= "com.mysql.jdbc.Driver";
	
	//als er geen voorwaarden zijn bij het setten van de attributen, zijn hier geen aparte set-methoden voor gemaakt
	
	/**
	 * Database, maakt een nieuw Database object aan.
	 * maakt verbinding met de database met de opgegeven instellingen
	 * @param urlin	bevat de url van de database
	 * @param user	bevat de gebruikersnaam
	 * @param passw	bevat het wachtwoord dat nodig is om toegang tot de database te verkrijgen
	 */
	public Database(String urlin, String user, String passw) // de constructor
	{
		url = urlin;
		username = user;
		password = passw;
		
		//verbinding maken met de database
		try{
			Class.forName(driver).newInstance();
			connection = DriverManager.getConnection(url, username, password);
			System.out.println(connection+"A");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e.toString());
		}
	}
	
	/**
	 * Deze methode selecteert alle kolommen van een de rijen die aan de voorwaarde voldoen
	 * @param tabel			bevat de naam van de tabel waaruit geselecteerd moet worden.
	 * @param vwkolom		bevat de kolomnaam waarover de voorwaarde gaat
	 * @param voorwaarde	bevat de waarde die de kolom moet hebben, als string.
	 * @return 				geeft de resultset van de uitgevoerde query terug.
	 */
	public ResultSet query(String tabel, String vwkolom, String voorwaarde)
	{
		String query = "SELECT * FROM " + tabel + " WHERE " + vwkolom + "='" + voorwaarde + "'";
		ResultSet res = null;
		System.out.println(query);
		
		try{
			Statement statement = connection.createStatement();
			res = statement.executeQuery(query);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e.toString());
		}
		
		return res;
	}
	
	/**
	 * Deze methode voert een query uit, waarmee je alle colommen selecteerd waarbij de opgegeven kolom voldoet aan de eis.
	 * Tevens worden de resultaten aan de hand van een opgegeven kolom op- of aflopend gerangschikt
	 * @param tabel			bevat de naam van de tabel
	 * @param vwkolom		bevat de naam van de kolom waaraan de voorwaarde wordt gesteld
	 * @param voorwaarde	bevat de waarde die records in vwkolom moeten hebben 
	 * @param sorteerOp		bevat de naam van de kolom waarop gesorteerd moet worden
	 * @param oplopend		Als true, worden de resutlaten oplopend gerangschikt, indien false, aflopend
	 */
	public ResultSet query(String tabel, String vwkolom, String voorwaarde, String sorteerOp, boolean oplopend)
	{
		String query = "SELECT * FROM " + tabel + " WHERE " + vwkolom + "='" + voorwaarde + "'"; //dit gedeelte van de statement moet zowiezo worden uitgevoerd	
		if(oplopend)
		{	//als oplopend true is, moeten de resultaten oplopend geordend worden aan de hand van sorteerOp
			query = query + " ORDER BY " + sorteerOp + " ASC";
		}
		else if(!oplopend)
		{ //als oplopend false is, moeten de resultaten aflopend worden geordend aan de hand van sorteerOp 
			query = query + " ORDER BY " + sorteerOp + " DESC";
		}
		
		ResultSet res = null;
		try
		{
			Statement statement = connection.createStatement();
			res = statement.executeQuery(query);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e.toString());
		}
		
		return res;
	}
	
	/**
	 * Deze methode print de Resultset af, enkel bedoelt voor debug doeleinden
	 * @param result	bevat de ResultSet die afgedrukt moet worden.
	 */
	public void printResultSet(ResultSet result)
	{
		try
		{
			ResultSetMetaData meta = result.getMetaData();
			int cols = meta.getColumnCount();
			String res = "";
			
			while(result.next())
			{
				for(int i=1; i<= cols; i++)
					res = res + result.getString(i) + ", ";
				
				res = res + '\n';
			}
			
			System.out.println(res);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println(e);
		}
	}
	
	/**
	 * Deze methode insert een nieuwe record in de tabel en returnt het id van de record
	 * @param tabel		bevat de naam van de tabel
	 * @param waarden	bevat de waarden die in die kolommen moeten worden geinsert. N.B. Deze moeten in de goeie volgorde gelevert worden
	 * @return			geeft het id terug van de nieuwe record
	 */
	public int insert(String tabel, String[] waarden)
	{
		String query = "INSERT INTO " + tabel + " VALUES (";
		int lengte = waarden.length;
		for (int i=0; i<(lengte-1); i++)
		{
			query = "'" + query + waarden[i] + "', ";
		}
		query = query + "'" + waarden[lengte] + "')";
		int id = -1;
		
		System.out.println(query);
		
		try
		{
			Statement statement = connection.createStatement();
			statement.executeQuery(query);
			ResultSet res = statement.executeQuery("SELECT LAST_INSERT_ID as new_id");
			id = Integer.parseInt(res.toString());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println(e.toString());
		}
		
		return id;	
	}
	
	/**
	 * Deze methode update de opgegeven tabel. Er kunnen geen meerdere veranderingen tegelijk worden doorgevoerd
	 * @param tabel				bevat de naam van de tabel
	 * @param veranderingen		bevat de veranderingen die moeten worden doorgevoerd, in sql, gescheiden door een komma
	 * @param voorwaardeKolom	bevat de naam van de kolom waaraan
	 * @param voorwaarde		bevat de waarde die de voorwaardeKolom moet hebben
	 */
	public void update(String tabel, String veranderingen, String voorwaardeKolom, String voorwaarde) //aanpassingen moeten in sql geschreven worden
	{
		String query = "UPDATE " + tabel + " SET " + veranderingen + " WHERE " + voorwaardeKolom + "=" + voorwaarde;
		try
		{
			Statement statement = connection.createStatement();
			statement.executeQuery(query);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println(e.toString());
		}
	}
	
	public void delete(String tabel, String voorwaardeKolom, String voorwaarde)
	{
		String query = "DELETE FROM " + tabel + " WHERE " + voorwaardeKolom + "=" + voorwaarde;
		try
		{
			Statement statement = connection.createStatement();
			statement.executeQuery(query);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println(e.toString());
		}
	}
	
	
}
