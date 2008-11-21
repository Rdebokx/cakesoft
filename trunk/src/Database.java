/**
 * klasse Database zorgt voor de interactie tussen het programma en de database.
 * Hij maakt verbinding en voert querys uit.
 *@author Groep 11
 *@version 1.0
 */



import java.sql.*;


public class Database {
	
	private Connection connection;
	private Statement statement = connection.createStatement();
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
	 * query, selecteert alle kolommen van een de rijen die aa nde voorwaarde voldoen
	 * @param tabel			bevat de naam van de tabel waaruit geselecteerd moet worden.
	 * @param vwkolom		bevat de kolomnaam waarover de voorwaarde gaat
	 * @param voorwaarde	bevat de waarde die de kolom moet hebben.
	 * @return 				geeft de resultset van de uitgevoerde query terug.
	 */

	public ResultSet query(String tabel, String vwkolom, String voorwaarde)
	{
		String query = "SELECT * FROM " + tabel + " WHERE " + vwkolom + "=" + voorwaarde;
		
		ResultSet res = statement.executeQuery(query);
	}
	
}
