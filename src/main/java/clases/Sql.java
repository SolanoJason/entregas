package clases;

import com.google.common.flogger.FluentLogger;
import com.google.common.flogger.StackSize;
import sql.ConexionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public enum Sql {
	;
	private static final FluentLogger logger = FluentLogger.forEnclosingClass();

	public static void insert(String sqlString) {
		try (Connection connection = ConexionPool.getConnection()) {
			try (Statement statement = connection.createStatement()) {
				if (statement.execute(sqlString)) {
					logger.atInfo().log("Se hizo el insert de %s", sqlString);
				} else {
					logger.atWarning().log("No se pudo realizar el insert de %s", sqlString);
				}
			}
		} catch (SQLException exception) {
			logger.atSevere().withCause(exception).withStackTrace(StackSize.FULL).log("Excepción al momento de ejecutar %s", sqlString);
		}
	}
}
