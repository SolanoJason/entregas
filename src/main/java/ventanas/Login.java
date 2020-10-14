/*
 * Created by JFormDesigner on Sat Oct 10 22:41:31 COT 2020
 */

package ventanas;

import com.google.common.flogger.FluentLogger;
import custom_beans.ModernJFrame;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author Arley
 */
public final class Login extends ModernJFrame {
	private static final FluentLogger logger = FluentLogger.forEnclosingClass();
	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JPanel vSpacer1;
	private JLabel label1;
	private JLabel label2;
	private JTextField textField1;
	private JLabel label3;
	private JPasswordField passwordField1;
	private JPanel panel1;
	private JButton buttonIngresar;
	private JButton buttonCancelar;
	private JPanel vSpacer2;

	public Login() {
		initComponents();
		useModernStyle();
	}

	private void changeButtonForm() {
		buttonCancelar.putClientProperty("JButton.buttonType", "roundRect");
		buttonIngresar.putClientProperty("JButton.buttonType", "roundRect");
	}

	private void validarUsuarioPassword(ActionEvent e) {

	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		vSpacer1 = new JPanel(null);
		label1 = new JLabel();
		label2 = new JLabel();
		textField1 = new JTextField();
		label3 = new JLabel();
		passwordField1 = new JPasswordField();
		panel1 = new JPanel();
		buttonIngresar = new JButton();
		buttonCancelar = new JButton();
		vSpacer2 = new JPanel(null);

		//======== this ========
		Container contentPane = getContentPane();
		contentPane.setLayout(new MigLayout("hidemode 3",
		                                    // columns
		                                    "[fill]" + "[grow,fill]",
		                                    // rows
		                                    "[grow,sizegroup 1]" + "[center]" + "[center]" + "[center]" + "[center]" + "[grow,sizegroup 1]"));
		contentPane.add(vSpacer1, "cell 0 0 2 1");

		//---- label1 ----
		label1.setText("Ingreso");
		label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 3f));
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(label1, "cell 0 1 2 1");

		//---- label2 ----
		label2.setText("Usuario");
		contentPane.add(label2, "cell 0 2");
		contentPane.add(textField1, "cell 1 2");

		//---- label3 ----
		label3.setText("Contrase\u00f1a");
		contentPane.add(label3, "cell 0 3");
		contentPane.add(passwordField1, "cell 1 3");

		//======== panel1 ========
		{
			panel1.setLayout(new MigLayout("hidemode 3",
			                               // columns
			                               "[grow,sizegroup 1,fill]" + "[grow,sizegroup 1,fill]",
			                               // rows
			                               "[center]"));

			//---- buttonIngresar ----
			buttonIngresar.setText("Ingresar");
			buttonIngresar.addActionListener(e -> validarUsuarioPassword(e));
			panel1.add(buttonIngresar, "cell 0 0");

			//---- buttonCancelar ----
			buttonCancelar.setText("Cancelar");
			panel1.add(buttonCancelar, "cell 1 0");
		}
		contentPane.add(panel1, "cell 0 4 2 1");
		contentPane.add(vSpacer2, "cell 0 5 2 1");
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
