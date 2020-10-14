package ventanas;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.extras.FlatInspector;
import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.icons.google_material_design_icons.GoogleMaterialDesignIcons;
import jiconfont.swing.IconFontSwing;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

/**
 * @author Arley
 */
public final class Menu extends JFrame {

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JButton buttonEscuela;
	private JButton buttonFacultad;
	private JButton buttonIngresarAlumno;
	private JButton buttonReportes;
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
		FlatLightLaf.install();
		FlatInspector.install("ctrl shift alt X");
		IconFontSwing.register(FontAwesome.getIconFont());
		IconFontSwing.register(GoogleMaterialDesignIcons.getIconFont());
	}

	/**
	 * Esta función es la encargada de animar el cambio de estilo, de modo oscuro a modo claro
	 */
	private void animatedLafChangeChanged() {
		System.setProperty("flatlaf.animatedLafChange", "true");
	}

	/**
	 * Configura las acciones de los botones con unos bonitos lambdas
	 */
	private void setActions() {
		buttonEscuela.addActionListener(e -> {
			this.setExtendedState(Frame.ICONIFIED);
			new Escuela(this).setVisible(true);
		});
		buttonFacultad.addActionListener(e -> {
			this.setExtendedState(Frame.ICONIFIED);
			new Facultad(this).setVisible(true);
		});
		buttonIngresarAlumno.addActionListener(e -> {
			this.setExtendedState(Frame.ICONIFIED);
			new IngresarAlumno(this).setVisible(true);
		});
		buttonReportes.addActionListener(e -> {
			this.setExtendedState(Frame.ICONIFIED);
			new Reportes(this).setVisible(true);
		});
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		buttonEscuela = new JButton();
		buttonFacultad = new JButton();
		buttonIngresarAlumno = new JButton();
		buttonReportes = new JButton();

		//======== this ========
		setTitle("Men\u00fa grupo 2");
		setMinimumSize(new Dimension(600, 600));
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		Container contentPane = getContentPane();
		contentPane.setLayout(new MigLayout("fill,insets 15,hidemode 3,align center center",
		                                    // columns
		                                    "[grow,sizegroup 1,fill]" + "[grow,sizegroup 1,fill]" + "[grow,sizegroup 1,fill]",
		                                    // rows
		                                    "[grow,sizegroup 1,fill]" + "[grow,sizegroup 1,fill]" + "[grow,sizegroup 1,fill]"));

		//---- buttonEscuela ----
		buttonEscuela.setText("Escuela");
		contentPane.add(buttonEscuela, "cell 0 0");

		//---- buttonFacultad ----
		buttonFacultad.setText("Facultad");
		contentPane.add(buttonFacultad, "cell 1 0");

		//---- buttonIngresarAlumno ----
		buttonIngresarAlumno.setText("Ingresar alumno");
		contentPane.add(buttonIngresarAlumno, "cell 2 0");

		//---- buttonReportes ----
		buttonReportes.setText("Reportes");
		contentPane.add(buttonReportes, "cell 0 1");
		pack();
		setLocationRelativeTo(null);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

}
