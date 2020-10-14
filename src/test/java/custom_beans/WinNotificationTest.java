package custom_beans;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SuppressWarnings("ClassWithoutLogger")
class WinNotificationTest {

	@Test
	void systemSupportNotification() {
		assertDoesNotThrow(() -> WinNotification.mostrarNotificacion("Prueba", "Prueba de notificaion", TrayIcon.MessageType.INFO));
	}

	@Test
	void testNotification() {
		assertDoesNotThrow(() -> {
			WinNotification.mostrarNotificacion("Advertencia", "Mensaje de advertencia", TrayIcon.MessageType.WARNING);
			WinNotification.mostrarNotificacion("Info", "Mensaje info", TrayIcon.MessageType.INFO);
			WinNotification.mostrarNotificacion("Error", "Mensaje de Error", TrayIcon.MessageType.ERROR);
			WinNotification.mostrarNotificacion("Mensaje Simple", "Mensaje simple", TrayIcon.MessageType.NONE);
		});
	}
}