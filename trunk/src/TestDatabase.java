import java.sql.*;

public class TestDatabase {

	public static void main(String[] args) {
		//een korte test om te testen of de databaseklasse werkt
		System.out.println("Database db = new Database(\"jdbc:mysql://localhost/cakesoft\", \"root\", \"koekje\");");
		Database db = new Database("jdbc:mysql://localhost/cakesoft", "root", "koekje");
		
		System.out.println("String query = \"INSERT INTO reactie (bericht, baksel_id, lid_id) VALUES ('Hallooow', 2, 3) ('lekker man', 1, 2)\";");
		String query = "INSERT INTO reactie (bericht, baksel_id, lid_id) VALUES ('Hallooow', 2, 3)";
		
		System.out.println("int positie = db.insert(query);");
		int positie = db.insert(query);
		
		System.out.println("System.out.println(positie);");
		System.out.println(positie);
		
		
		//de executeQuery testen op dingen uit de database halen
		System.out.println("String query2 = \"SELECT * FROM lid WHERE wachtwoord <> 'ww1'\";");
		String query2 = "SELECT * FROM lid WHERE wachtwoord <> 'ww1'";
		
		System.out.println("ResultSet res1 = db.executeQuery(query2);");
		ResultSet res1 = db.executeQuery(query2);
		
		//en de resultset printen
		System.out.println("db.printResultSet(res1);");
		db.printResultSet(res1);
		
	}

}
