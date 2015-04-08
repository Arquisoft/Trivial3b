package es.uniovi.asw.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Jdbc {
    private static final String URL = "jdbc:postgresql://ec2-79-125-21-70.eu-west-1.compute.amazonaws.com:5432/d30bcf19jdr4m7?user=friuvgklmddpfp&password=q2VNQqLtoF2LgwO4kGlkj12d3M&ssl=true&sslmode=require";
    private static final String DRV = "org.postgresql.Driver";
    
    static {
        try {
            Class.forName(DRV);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver not found in classpath", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void close(ResultSet rs, Statement st, Connection c) {
        close(rs);
        close(st);
        close(c);
    }

    public static void close(ResultSet rs, Statement st) {
        close(rs);
        close(st);
    }

	protected static void close(ResultSet rs) {
		if (rs != null) try { rs.close(); } catch(SQLException e) {};
	}

	public static void close(Statement st) {
		if (st != null ) try { st.close(); } catch(SQLException e) {};
	}

	public static void close(Connection c) {
		if (c != null) try { c.close(); } catch(SQLException e) {};
	}
}
