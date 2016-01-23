package invocing.jdbc;

import static invocing.jdbc.DBConnect.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SimpleQueries {

	public static void main(String[] args) {
		// 1. Load Db Driver
		try {
			Class.forName(dbDriver);
			// 2. & 3. Crate DB connection and statement
			try (Connection c = DriverManager.getConnection(dbURL, user, password); Statement s = c.createStatement()) {
				// 4. Execute query
				ResultSet rs = s.executeQuery("SELECT * FROM items");
				// 5. Process ResultSet
				printTable(rs);
				//OR Execute update
//				int numberUpdates = s.executeUpdate("INSERT INTO items (name,vendor,price)"
//						+ " VALUES ('Dummy Product', 'Noname Vendor', 15.60);");
//				int numberUpdates = s.executeUpdate("UPDATE items SET price=60.2"
//						+ " WHERE name='Dummy Product';");
				int numberUpdates = s.executeUpdate("DELETE FROM items "
						+ " WHERE name='Dummy Product';");
				System.out.println("\nNumber records changed: " + numberUpdates);
				rs = s.executeQuery("SELECT * FROM items");
				printTable(rs);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static void printTable(ResultSet rs) throws SQLException {
		int count = rs.getMetaData().getColumnCount();
		for (int i = 1; i <= count; i++) {
			String name = rs.getMetaData().getColumnName(i);
			System.out.print(String.format("%-16s |", name));
		}
		System.out.println();

		while (rs.next()) {
			for (int column = 1; column <= count; column++) {
				String value = rs.getString(column);
				System.out.print(String.format("%-16s |", 
						value.substring(0, Math.min(16, value.length()))));
			}
			System.out.println();
		}
	}

}
