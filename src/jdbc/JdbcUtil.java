package jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcUtil {
	public static void rollback(Connection con) {
		try {
			con.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close(AutoCloseable... ins) {
		for (AutoCloseable i : ins) {
			if (i != null) {
				try {
					i.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
