package ventanas;

import com.formdev.flatlaf.extras.FlatInspector;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialDarkerIJTheme;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

/**
 * @author Arley
 */
public class Menu extends JFrame {

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JButton buttonEscuela;
	private JButton buttonFacultad;
        private JButton buttonIngalum;
	// JFormDesigner - End of variables declaration  //GEN-END:variables

	public Menu() {
		initComponents();
		this.setVisible(true);
		setActions();
	}

	/**
	 * Main que abre el menu y expone las opciones en forma de un 3x3
	 *
	 * @param args Los valores CLI (nunca implementados)
	 */
	public static void main(String[] args) {
		setTheme();
		new Menu().setVisible(true);
	}

	/**
	 * Configura el tema a usar, hay muchos muchos para elegir
	 * <p>
	 * https://www.formdev.com/flatlaf/
	 * <p>
	 * https://www.formdev.com/flatlaf/themes/
	 *
	 * @author Arley
	 */
	private static void setTheme() {
		FlatMaterialDarkerIJTheme.install();
		FlatInspector.install("ctrl shift alt X");
	}

	/**
	 * Configura las acciones de los botones con unos bonitos lambdas
	 */
	private void setActions() {
		buttonEscuela.addActionListener(e -> {
			this.setExtendedState(Frame.ICONIFIED);
			new Escuela().setVisible(true);
		});
		buttonFacultad.addActionListener(e -> {
			this.setExtendedState(Frame.ICONIFIED);
			new Facultad().setVisible(true);
		});
                buttonIngalum.addActionListener(e -> {
			this.setExtendedState(Frame.ICONIFIED);
			new ingalum(this).setVisible(true);
		});
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		buttonEscuela  = new JButton();
		buttonFacultad = new JButton();
                buttonIngalum = new JButton();
		//======== this ========
		setTitle("Men\u00fa grupo 2");
		setMinimumSize(new Dimension(600, 600));
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		Container contentPane = getContentPane();
		contentPane.setLayout(new MigLayout(
				"fill,insets 15,hidemode 3,align center center",
				// columns
				"[grow,sizegroup 1,fill]" +
						"[grow,sizegroup 1,fill]" +
						"[grow,sizegroup 1,fill]",
				// rows
				"[grow,sizegroup 1,fill]" +
						"[grow,sizegroup 1,fill]" +
						"[grow,sizegroup 1,fill]"));

		//---- buttonEscuela ----
		buttonEscuela.setText("Escuela");
		contentPane.add(buttonEscuela, "cell 0 0");

		//---- buttonFacultad ----
		buttonFacultad.setText("Facultad");
		contentPane.add(buttonFacultad, "cell 1 0");
                
                //---- buttonIngalum ----
                buttonIngalum.setText("Registrar Alumno");
                contentPane.add(buttonIngalum, "cell 2 0");
		pack();
		setLocationRelativeTo(null);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

}
