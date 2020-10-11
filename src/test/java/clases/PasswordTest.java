package clases;

import com.google.common.flogger.FluentLogger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import sql.ConexionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("OptionalGetWithoutIsPresent")
class PasswordTest {
	private static final FluentLogger logger = FluentLogger.forEnclosingClass();

	private static Stream<Arguments> defaultUserData() {
		Stream<Arguments> stream = Stream.empty();
		try (Connection connection = ConexionPool.getConnection()) {
			try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT u.nickname,u.contra,u.salt,u.pista FROM mydb.usuario u WHERE u.id<=10")) {
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					while (resultSet.next()) {
						stream = Stream.concat(stream, Stream.of(Arguments.of(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4))));
					}
				}
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return stream;
	}

	@Test
	@DisplayName("Salt Size")
	void generateSalt() {
		assertEquals(88, Password.generateSalt().length());
	}

	@SuppressWarnings("SpellCheckingInspection")
	@Test
	void hashPassword() {
		assertEquals("QCweiqyjrTo2s+WpxmD0IQvFBdR4BR4+S/mL4jPg+94t0NNAXg4CxCKTYO1viKc35L2GffNSGtZTFNk9bfwixw==", Password.hashPassword("l33tsupah4x0r".toCharArray(), "mfl1DnjuN32cCwuM4/dKIpTg3aZmZdCG8Fet0gxGkeBpCUBx95x9IfBpkbpwM1AGwUCl5iQn/7Fs2wP6LIpRiQ==").get());
		assertEquals("necBYKYgMSQGq6hYv1HIrx8Izp0Qp4dfZIjEwCtl3Pmr171K2snMFOXmNlZNTWytblFeKA+IdREbKExE6vfqIA==", Password.hashPassword("l33tsupah4x0r".toCharArray(), "Nn8OVIhfHfqknM5gW7Z+tgaE5TG4ofuB7t5QgJLt4uVjkH8rDHwyP7rUUQIzkTNMQ9nEseAspEkeM1XzksinXQ==").get());
		assertEquals("FVZozN0W1nek5Ik0NlZ3ggdy6+jmCFXZdtTvhxOq2MOch8sC+co3btf5WDrsmkflnqLRYu7gUZI0/2AXmpBXsQ==", Password.hashPassword("l33tsupah4x0r".toCharArray(), "LcK/ySGIeiQwO0A8KnVaYIqI1WMekXMdJZKlACWZzhBsfmBlQtH98QNBEXKRgaheHkql/6VzeBjCFg3V0xy9jA==").get());

		//// Para un salt diferente el hash debe ser diferente
		// 1 carácter de diferencia en la salt
		assertNotEquals("CHVIWk0Fnuw7MT4DxEuN3CKtU0m7+xP+yw3klsA1fj+rO3SCIKfL4W4wd9gp4EtwFxKHl+9cwzMYTBBJ8Es42Q==", Password.hashPassword("l33tsupah4x0r".toCharArray(), "LcK/ySGIeiQwO0A8KnVaYIqI1WMekXMdJZKlABWZzhBsfmBlQtH98QNBEXKRgaheHkql/6VzeBjCFg3V0xy9jA==").get());
		// Salt completamente diferente
		assertNotEquals("CHVIWk0Fnuw7MT4DxEuN3CKtU0m7+xP+yw3klsA1fj+rO3SCIKfL4W4wd9gp4EtwFxKHl+9cwzMYTBBJ8Es42Q==", Password.hashPassword("l33tsupah4x0r".toCharArray(), "aoUf3Xh55zTB+t0ocCMjpIAFrCPwuLNleUJrbbS6vvPqOyM/9s5iaGoAybqpVu3W7zqrbF1xFJKINkGJ4G01tg==").get());
	}

	@SuppressWarnings("SpellCheckingInspection")
	@Test
	void verificarPassword() {
		assertTrue(Password.verificarPassword("l33tsupah4x0r".toCharArray(), "QCweiqyjrTo2s+WpxmD0IQvFBdR4BR4+S/mL4jPg+94t0NNAXg4CxCKTYO1viKc35L2GffNSGtZTFNk9bfwixw==", "mfl1DnjuN32cCwuM4/dKIpTg3aZmZdCG8Fet0gxGkeBpCUBx95x9IfBpkbpwM1AGwUCl5iQn/7Fs2wP6LIpRiQ=="));
		assertTrue(Password.verificarPassword("l33tsupah4x0r".toCharArray(), "necBYKYgMSQGq6hYv1HIrx8Izp0Qp4dfZIjEwCtl3Pmr171K2snMFOXmNlZNTWytblFeKA+IdREbKExE6vfqIA==", "Nn8OVIhfHfqknM5gW7Z+tgaE5TG4ofuB7t5QgJLt4uVjkH8rDHwyP7rUUQIzkTNMQ9nEseAspEkeM1XzksinXQ=="));
		assertTrue(Password.verificarPassword("l33tsupah4x0r".toCharArray(), "FVZozN0W1nek5Ik0NlZ3ggdy6+jmCFXZdtTvhxOq2MOch8sC+co3btf5WDrsmkflnqLRYu7gUZI0/2AXmpBXsQ==", "LcK/ySGIeiQwO0A8KnVaYIqI1WMekXMdJZKlACWZzhBsfmBlQtH98QNBEXKRgaheHkql/6VzeBjCFg3V0xy9jA=="));
	}

	@ParameterizedTest
	@DisplayName("Contraseña de usuarios automáticos validos")
	@MethodSource("defaultUserData")
	void verificarPasswordsUsuariosDefault(String nickname, String contra, String salt, String pista) {
		logger.atFinest().log("nickname: %s, password: %s", nickname, Password.hashPassword(pista.toCharArray(), salt).get());
		assertTrue(Password.verificarPassword(pista.toCharArray(), contra, salt));
	}
}