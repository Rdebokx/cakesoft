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
		String driver = "com.mysql.jdbc.Driver";
		url = urlin;
		username = user;
		password = passw;
		
		//verbinding maken met de database
		try{
			Class.forName(driver).newInstance();
			connection = DriverManager.getConnection(url, username, password);
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
		String query = "SELECT * FROM " + tabel + " WHERE " + vwkolom + "=" + voorwaarde;
		ResultSet res;
		
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
		String query = "SELECT * FROM " + tabel + " WHERE " + vwkolom + "=" + voorwaarde; //dit gedeelte van de statement moet zowiezo worden uitgevoerd	
		if(oplopend)
		{	//als oplopend true is, moeten de resultaten oplopend geordend worden aan de hand van sorteerOp
			query = query + " ORDER BY " + sorteerOp + " ASC";
		}
		else if(!oplopend)
		{ //als oplopend false is, moeten de resultaten aflopend worden geordend aan de hand van sorteerOp 
			query = query + " ORDER BY " + sorteerOp + " DESC";
		}
		
		ResultSet res;
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
	
	
}
