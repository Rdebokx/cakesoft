import java.sql.*;


public class Database {
	
	private Connection connection;
	private Statement statement;
	private String url;
	private String username;
	private String password;
	
	//als er geen voorwaarden zijn bij het setten van de attributen, zijn hier geen aparte set-methoden voor gemaakt
	
	public Database(String urlin, String user, String passw) // de constructor
	{
		//post: heeft een verbinding gemaakt met de database met de opgegeven instellingen
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

}
