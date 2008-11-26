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
			//System.out.println(connection+"A");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e.toString());
		}
	}
	
	/**
	 * Deze methode voert de opgegeven query uit.
	 * N.B. voor INSERT is een aparte methode
	 * @param query			bevat de query die moet worden uitgevoerd.
	 * @return 				geeft de resultset van de uitgevoerde query terug.
	 */
	public ResultSet executeQuery(String query)
	{
		ResultSet res = null;
		
		try {
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
	public int insert(String query)
	{
		int id=-1;
		
		try
		{
			Statement statement = connection.createStatement();
			statement.execute(query);
			ResultSet res = statement.executeQuery("SELECT LAST_INSERT_ID() as new_id");
			res.next();
			id=res.getInt("new_id");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println(e.toString());
		}
		
		return id;	
	}
}
