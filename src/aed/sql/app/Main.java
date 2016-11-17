package aed.sql.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;

public class Main {

	public static void main(String[] args) throws ParseException {
		System.out.println("ALELUYA");
		try {
			Main main = new Main();
			Connection conn = DriverManager
					.getConnection("jdbc:ucanaccess://C:/Users/profesor/Documents;jackcessOpener=CryptCodecOpener", "root", "");
		} catch (SQLException  ex) {
			ex.printStackTrace();
		}
	}

}