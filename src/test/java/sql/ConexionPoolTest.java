package sql;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ConexionPoolTest {

	@Test
	void getConnection() {
		assertDoesNotThrow(() -> ConexionPool.getConnection());
	}
}