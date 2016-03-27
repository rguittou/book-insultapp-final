package org.openshift;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

public class InsultGenerator {
	public String generateInsult() {
		try {
		String databaseURL = "jdbc:postgresql://";
		databaseURL += System.getenv("POSTGRESQL_SERVICE_HOST");
		databaseURL += "/" + System.getenv("POSTGRESQL_DATABASE");
		
		String username = System.getenv("POSTGRESQL_USER");
		String password = System.getenv("PGPASSWORD");
		Connection connection = DriverManager.getConnection(databaseURL, username, password);
		
		if(connection != null) {
			String SQL = "SELECT string FROM short_adjective";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            String returnStatement = "";
            while(rs.next()){
            	returnStatement += rs.getString("string") + " ";
            }
            
			return returnStatement;
		} else {
			return "Not Connected";
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*
		String words[][] = {{"Artless", "Bawdy", "Beslubbering"}, {"Base-court", "Bat-fowling", "Beef-witted"}, {"Apple-john", "Baggage", "Barnacle"}};
		String vowels = "AEIOU";
		String article = "an";
		String firstAdjective = words[0][new Random().nextInt(words[0].length)];
		String secondAdjective = words[1][new Random().nextInt(words[1].length)];
		String noun = words[2][new Random().nextInt(words[2].length)];
		if (vowels.indexOf(firstAdjective.charAt(0)) == -1) {
			article = "a";
		}
		return String.format("Thou art %s %s %s %s!", article, firstAdjective, secondAdjective, noun);
		*/
		
		return "Hi";
	}
}