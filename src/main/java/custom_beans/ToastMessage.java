package custom_beans;

import com.google.common.flogger.FluentLogger;
import com.google.common.flogger.StackSize;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * mensaje tipo toast (como los que tiene android)
 */
public final class ToastMessage extends JDialog {
	private static final FluentLogger logger = FluentLogger.forEnclosingClass();
	private static final long serialVersionUID = 1L;
	private static boolean spamProtect = false;
	private int milliseconds = 1500;

	/**
	 * Muestra un mensaje toast
	 * @param caller La ventana desde la que se llama, en JFrame usar {@code this.rootPane}
	 * @param mensaje mensaje a mostrar en el toast
	 * @param time Tiempo que se va a mostrar el toast en milisegundos
	 */
	public ToastMessage(JComponent caller, String mensaje, int time){
		this.milliseconds = time;
		new ToastMessage(caller, mensaje);
	}

	/**
	 * Muestra un mensaje toast por 1.5 segundos
	 * @param caller La ventana desde la que se llama, en JFrame usar {@code this.rootPane}
	 * @param mensaje mensaje a mostrar en el toast
	 */
	public ToastMessage(JComponent caller, String mensaje) {
		if(spamProtect) {
			return;
		}
		setUndecorated(true);
		setAlwaysOnTop(true);
		setFocusableWindowState(false);
		setLayout(new GridBagLayout());

		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		panel.setBackground(new Color(160, 160, 160));
		JLabel toastLabel = new JLabel(mensaje);
		toastLabel.setForeground(Color.WHITE);
		panel.add(toastLabel);
		add(panel);
		pack();

		Window window = SwingUtilities.getWindowAncestor(caller);
		int xcoord = window.getLocationOnScreen().x + window.getWidth() / 2 - getWidth() / 2;
		int ycoord = window.getLocationOnScreen().y + (int)((double)window.getHeight() * 0.75) - getHeight() / 2;
		setLocation(xcoord, ycoord);
		setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 30, 30));
		setVisible(true);

		new Thread(() -> {
			try {
				spamProtect = true;
				Thread.sleep(milliseconds);
				dispose();
				spamProtect = false;
			} catch (InterruptedException e) {
				logger.atSevere().withStackTrace(StackSize.FULL).withCause(e).log("Se produjo un interrupt mientras se mostraba el toast");
			}
		}).start();
	}
}