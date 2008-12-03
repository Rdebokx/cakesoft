import java.sql.*;

/**
 * klasse Database zorgt voor de interactie tussen het programma en de database.
 * Hij maakt verbinding en voert querys uit.
 *@author Groep 11
 *@version 3.2
 */
public class Database {

	private Connection connection;
	private String url;
	private String username;
	private String password;

	private String driver = "com.mysql.jdbc.Driver";

	/**
	 * Database, maakt een nieuw Database object aan.
	 * Maakt verbinding met de database met de opgegeven instellingen
	 * @param urlin 	Bevat de url van de database
	 * @param user  	Bevat de gebruikersnaam
	 * @param passw 	Bevat het wachtwoord dat nodig is om toegang tot de database te verkrijgen
	 */
	public Database(String urlin, String user, String passw)
	{
		url = urlin;
		username = user;
		password = passw;

		//Verbinding maken met de database
		try 
		{
			Class.forName(driver).newInstance();
			connection = DriverManager.getConnection(url, username, password);
			
			//Debug informatie in console
			System.out.println("Er is een verbinding gemaakt met de server.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Deze methode selecteert alle kolommen van een de rijen die aan de voorwaarde voldoen
	 * @param 	tabel               Bevat de naam van de tabel waaruit geselecteerd moet worden.
	 * @param 	voorwaarde   		Bevat de voorwaarde, volledig in SQL stijl (excl. WHERE)
	 * @return                      Geeft de resultset van de uitgevoerde query terug.
	 */
	public ResultSet select(querySelect query)
	{
		String queryStr = query.getQuery();
		ResultSet res = null;
		//Geef de query weer in console voor debugging
		System.out.println(queryStr);

		try
		{
			Statement statement = connection.createStatement();
			res = statement.executeQuery(queryStr);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return res;
	}
	
	/**
	 * Deze methode insert een nieuwe record in de tabel en returnt het id van de record
	 * @param 	query	De insertquery die uitgevoerd zal moeten worden, bevat de tabelnaam, waarden e.d.
	 * @return			Geeft het ID terug van het aangemaakte record.
	 */
	public int insert(queryInsert query)
	{
		String queryStr = query.getQuery();
		int id = -1;
		
		//Geef de query weer in console voor debugging
		System.out.println(queryStr);

		try
		{
			Statement statement = connection.createStatement();
			statement.execute(queryStr);

			ResultSet res = statement.executeQuery("SELECT LAST_INSERT_ID() as new_id");
			res.next();
			id = res.getInt("new_id");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
		}

		return id;      
	}

	/**
	 * Deze methode update de opgegeven tabel. Er kunnen geen meerdere veranderingen tegelijk worden doorgevoerd
	 * @param query	De updatequery die uitgevoerd zal moeten worden, bevat de tabelnaam, kolommen, voorwaarden e.d.
	 */
	public void update(queryUpdate query)
	{
		String queryStr = query.getQuery();
		//Geef de query weer in console voor debugging
		System.out.println(queryStr);
		try
		{
			Statement statement = connection.createStatement();
			statement.execute(queryStr);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
		}
	}

	
	/**
	 * Deze methode verwijdert de record(s) die aan de voorwaarde voldoet/voldoen
	 * @param query		De deletequery die uitgevoerd zal moeten worden, bevat de tabelnaam, kolommen, voorwaarden e.d.
	 */
	public void delete(queryDelete query)
	{
		String queryStr = query.getQuery();
		//Geef de query weer in console voor debugging
		System.out.println(queryStr);

		try
		{
			Statement statement = connection.createStatement();
			statement.execute(queryStr);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
		}
	}


}
