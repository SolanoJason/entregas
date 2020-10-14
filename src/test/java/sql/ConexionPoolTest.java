package sql;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ConexionPoolTest {

	@Test
	void getConnection() {
		assertDoesNotThrow(ConexionPool::getConnection);
	}

	@Test
	void testCantidadFacultades() {
		try (Connection connection = ConexionPool.getConnection()) {
			try (Statement statement = connection.createStatement()) {
				try (ResultSet resultSet = statement.executeQuery("SELECT COUNT(f.id) FROM mydb.facultad f")) {
					resultSet.next(); //Ir al resultado
					assertEquals(12, resultSet.getInt(1));
				}
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}
}