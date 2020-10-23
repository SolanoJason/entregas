/*
 * Created by JFormDesigner on Mon Oct 12 15:17:55 COT 2020
 */

package ventanas.login;

import com.google.common.flogger.FluentLogger;
import custom_beans.JPlaceholderTextField;
import custom_beans.ToastMessage;
import net.miginfocom.swing.MigLayout;
import sql.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author Arley
 */
public class IngresarPanel extends JPanel {
	private static final FluentLogger logger = FluentLogger.forEnclosingClass();
	private final List<Usuario> usuarios;
	LoginFrame frame;
	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JPlaceholderTextField inputUsuario;
	private JButton buttonVerificarUsuario;
	private JButton buttonCancelar;

	public IngresarPanel(Usuario[] usuarios, LoginFrame frame) {
		this.usuarios = Arrays.asList(usuarios);
		this.frame = frame;
		initComponents();
		changeButtonForm();
	}

	private void changeButtonForm() {
		buttonCancelar.putClientProperty("JButton.buttonType", "roundRect");
		buttonVerificarUsuario.putClientProperty("JButton.buttonType", "roundRect");
	}

	private void validarUsuarioPassword(ActionEvent e) {
		if (inputUsuario.getText().trim().isEmpty()) {
			new ToastMessage(this, "Debe ingresar un usuario", 2000);
		} else {
			Optional<Usuario> selectedUser = usuarios.stream().filter(user -> user.nickname.equals(inputUsuario.getText())).findFirst();
			if (selectedUser.isPresent()) {
				LoginFrame.loginPanel.setUsuario(inputUsuario.getText());
				((CardLayout) frame.cardPanel.getLayout()).show(frame.cardPanel, LoginFrame.cards.LOGIN.nombre);
			} else {
				LoginFrame.registrarPanel.setUsuario(inputUsuario.getText());
				((CardLayout) frame.cardPanel.getLayout()).show(frame.cardPanel, LoginFrame.cards.REGISTRAR.nombre);
			}
		}
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		JPanel vSpacer1 = new JPanel(null);
		JLabel label1 = new JLabel();
		inputUsuario = new JPlaceholderTextField();
		JPanel panel1 = new JPanel();
		buttonVerificarUsuario = new JButton();
		buttonCancelar = new JButton();
		JPanel vSpacer2 = new JPanel(null);

		//======== this ========
		setLayout(new MigLayout("hidemode 3",
		                        // columns
		                        "[grow,fill]",
		                        // rows
		                        "[grow,sizegroup 1]" + "[]" + "[]" + "[]" + "[grow,sizegroup 1]"));
		add(vSpacer1, "cell 0 0");

		//---- label1 ----
		label1.setText("Ingreso");
		label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 3f));
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		add(label1, "cell 0 1");

		//---- inputUsuario ----
		inputUsuario.setToolTipText("Usuario");
		inputUsuario.setPlaceholder("Ingrese el usuario");
		add(inputUsuario, "cell 0 2");

		//======== panel1 ========
		{
			panel1.setLayout(new MigLayout("hidemode 3",
			                               // columns
			                               "[grow,fill]",
			                               // rows
			                               "[center]" + "[]"));

			//---- buttonVerificarUsuario ----
			buttonVerificarUsuario.setText("VERIFICAR USUARIO");
			buttonVerificarUsuario.addActionListener(this::validarUsuarioPassword);
			panel1.add(buttonVerificarUsuario, "cell 0 0");

			//---- buttonCancelar ----
			buttonCancelar.setText("Cancelar");
			panel1.add(buttonCancelar, "cell 0 1");
		}
		add(panel1, "cell 0 3");
		add(vSpacer2, "cell 0 4");
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	public void setUsuario(String username) {
		this.inputUsuario.setText(username);
	}
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
