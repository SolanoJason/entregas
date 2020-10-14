package clases;

import com.google.common.flogger.FluentLogger;
import com.google.common.flogger.StackSize;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

/***
 * Esta clase se va a encargar del manejo de contraseñas, la salt generada es de 64 de tamaño (a lo loco)
 */
public enum Password {
	;
	private static final FluentLogger logger = FluentLogger.forEnclosingClass();

	private static final SecureRandom RAND = new SecureRandom();
	private static final int ITERATIONS = 65536;
	private static final int KEY_LENGTH = 512;

	// Para ver los algoritmos posibles visitar
	// https://docs.oracle.com/javase/8/docs/technotes/guides/security/StandardNames.html#SecretKeyFactory
	private static final String ALGORITHM = "PBKDF2WithHmacSHA512";

	/***
	 * Genera la sal para la contraseña, con una longitud de 64 caracteres
	 * @return la sal.
	 */
	public static String generateSalt() {
		int length = 64;
		byte[] salt = new byte[length];
		RAND.nextBytes(salt);
		return Base64.getEncoder().encodeToString(salt);
	}

	/***
	 * Función que calcula el hash de la contraseña, y lo puede comparar con el hash devuelto por el servidor
	 * Usa {@link Optional} para no tener que manejar null, y tener las horribles consecuencias de un null pointer exception
	 * Puede retornar una excepción en caso que no se encuentre los algoritmos necesarios para calcular el hash
	 * @param password La contraseña a ser hasheada
	 * @param salt La sal que condimenta a la contraseña, es obtenida del servidor en caso de verificar, en caso de que sea nueva es generada por {@link Password#generateSalt()}
	 * @return un {@link Optional} del resultado del hash
	 */
	public static Optional<String> hashPassword(char[] password, String salt) {
		byte[] bytes = salt.getBytes();
		PBEKeySpec spec = new PBEKeySpec(password, bytes, ITERATIONS, KEY_LENGTH);
		Arrays.fill(password, Character.MIN_VALUE);
		try {
			SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
			byte[] securePassword = factory.generateSecret(spec).getEncoded();
			return Optional.of(Base64.getEncoder().encodeToString(securePassword));
		} catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
			logger.atSevere().withCause(ex).withStackTrace(StackSize.FULL).log("Excepción encontrada al hashear la contraseña");
			return Optional.empty();
		} finally {
			spec.clearPassword();
		}
	}

	/***
	 * Permite verificar la contraseña ingresada es la correcta
	 * @param password Contraseña como char array
	 * @param key Hash previamente generado, viene de la base de datos normalmente
	 * @param salt Salt usado para calcular el hash
	 * @return true si es valida la contraseña, caso contrario false
	 */
	public static boolean verificarPassword(char[] password, String key, String salt) {
		Optional<String> optEncrypted = hashPassword(password, salt);
		// En caso de que el hash pueda hacerse se compara con el hash obtenido de la base de datos y se comparan
		return optEncrypted.map(s -> s.equals(key)).orElse(false);
	}
}
