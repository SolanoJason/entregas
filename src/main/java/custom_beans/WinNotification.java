package custom_beans;

import java.awt.*;
import java.awt.TrayIcon.MessageType;

public enum WinNotification {
	;
	private static final String NOMBRE_PROGRAMA = "Entrega de equipos";
	private static final Image ICONO = Toolkit.getDefaultToolkit().createImage(ClassLoader.getSystemClassLoader().getResource("icons/iconounasam.png"));
	private static final SystemTray SYSTEM_TRAY = SystemTray.getSystemTray();

	public static void mostrarNotificacion(String titulo, String contenido, MessageType tipo) throws AWTException {
		TrayIcon trayIcon = new TrayIcon(ICONO, NOMBRE_PROGRAMA);
		trayIcon.setImageAutoSize(true);
		trayIcon.setToolTip("Notificación de " + NOMBRE_PROGRAMA);
		SYSTEM_TRAY.add(trayIcon);
		trayIcon.displayMessage(titulo, contenido, tipo);
	}
}