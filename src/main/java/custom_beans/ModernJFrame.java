package custom_beans;

import com.formdev.flatlaf.ui.JBRCustomDecorations;

import javax.swing.*;

/**
 * Esta clase es una clase abstracta que implementa la funcionalidad para poder usar una barra integrada, en vez de la que
 * da el sistema operativo, en los sistemas operativos que permitan esta funcionalidad.
 * Para poderla usar se debe llamar useModernStyle al final del builder de la clase.
 */
public abstract class ModernJFrame extends JFrame {
	public void useModernStyle() {
		boolean supportsWindowDecorations = UIManager.getLookAndFeel().getSupportsWindowDecorations() || JBRCustomDecorations.isSupported();
		if (supportsWindowDecorations) {
			dispose();
			setUndecorated(true);
			getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
			setVisible(true);
			menuBarEmbeddedChanged();
		}
	}

	private void menuBarEmbeddedChanged() {
		// alternative method for all frames and menu bars in an application
		UIManager.put("TitlePane.menuBarEmbedded", true);
		revalidate();
		repaint();
	}
}
