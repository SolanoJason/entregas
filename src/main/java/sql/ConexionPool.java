package sql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public enum ConexionPool {
	;
	private static final HikariConfig config = new HikariConfig("src/main/resources/hikari.properties");
	private static final HikariDataSource ds;

	static {
		ds = new HikariDataSource(config);
	}


	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}
}

