package invocing.jdbc;

//: c15:jdbc:CIDConnect.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// Database connection information for
// the community interests database (CID).

public class DBConnect {
  // All the information specific to CloudScape:
  public static String dbDriver = "com.mysql.jdbc.Driver";
  public static String dbURL = "jdbc:mysql://localhost:3306/java32ed";
  public static String user = "root";
  public static String password = "root";
} ///:~