/*
 * Created by JFormDesigner on Mon Oct 12 15:17:10 COT 2020
 */

package ventanas.login;

import clases.Control;
import clases.Password;
import clases.Sql;
import com.google.common.flogger.FluentLogger;
import custom_beans.JPlaceholderTextField;
import custom_beans.ToastMessage;
import net.miginfocom.swing.MigLayout;
import sql.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author Arley
 */
public final class RegistrarPanel extends JPanel {
	private static final FluentLogger logger = FluentLogger.forEnclosingClass();

	private final List<Usuario> usuarios;
	private final LoginFrame padre;
	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JPlaceholderTextField inputDNI;
	private JPlaceholderTextField inputUsuario;
	private JPasswordField inputPassword;
	private JPasswordField inputPasswordConfirm;

	public RegistrarPanel(Usuario[] usuarios, LoginFrame padre) {
		this.usuarios = Arrays.asList(usuarios.clone());
		this.padre = padre;
		initComponents();
		configurarFlujo();
	}

	public void configurarFlujo() {
		Control.siguienteJComponent(inputUsuario, inputPassword);
		Control.siguienteJComponent(inputPassword, inputPasswordConfirm);
	}

	private void registrarUsuario(ActionEvent event) {
		if (inputDNI.getText().trim().isEmpty()) {
			new ToastMessage(this, "Debe ingresar el numero de DNI", 2000);
		} else if (inputUsuario.getText().trim().isEmpty()) {
			new ToastMessage(this, "Debe ingresar un usuario", 2000);
		} else if (inputPassword.getPassword().length == 0) {
			new ToastMessage(this, "Debe ingresar la contraseña", 2000);
		} else if (!Arrays.equals(inputPassword.getPassword(), inputPasswordConfirm.getPassword())) {
			new ToastMessage(this, "Las contraseñas no coinciden", 2000);
		} else if (usuarios.stream().anyMatch(user -> user.nickname.equals(inputUsuario.getText()))) {
			new ToastMessage(this, MessageFormat.format("El usuario {0} ya existe", inputUsuario.getText()), 2000);
		} else {
			String salt = Password.generateSalt();
			Optional<String> maybePassword = Password.hashPassword(inputPassword.getPassword(), salt);
			maybePassword.ifPresent(s -> Sql.insert(String.format("INSERT mydb.usuario(usuario.nickname, usuario.contra, usuario.persona_dni, usuario.salt) VALUES ('%s','%s',%d,'%s')", inputUsuario.getText(), s, Integer.parseInt(inputDNI.getText()), salt)));
			new ToastMessage(this, MessageFormat.format("El usuario {0} ha sido registrado", inputUsuario.getText()), 3000);
			LoginFrame.llenarUsuarios();
			LoginFrame.ingresarPanel.setUsuario(inputUsuario.getText());
			((CardLayout) padre.cardPanel.getLayout()).show(padre.cardPanel, LoginFrame.cards.LOGIN.nombre);
		}
	}

	private void buttonAtrasActionPerformed(ActionEvent e) {
		((CardLayout) padre.cardPanel.getLayout()).show(padre.cardPanel, LoginFrame.cards.INGRESAR.nombre);
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		JPanel vSpacer1 = new JPanel(null);
		JLabel label1 = new JLabel();
		JLabel label2 = new JLabel();
		inputDNI = new JPlaceholderTextField();
		inputUsuario = new JPlaceholderTextField();
		inputPassword = new JPasswordField();
		inputPasswordConfirm = new JPasswordField();
		JPanel panel1 = new JPanel();
		JButton buttonRegistrar = new JButton();
		JButton buttonAtras = new JButton();
		JPanel vSpacer2 = new JPanel(null);

		//======== this ========
		setLayout(new MigLayout("hidemode 3",
		                        // columns
		                        "[grow,fill]",
		                        // rows
		                        "[grow,fill]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[]" + "[grow,fill]"));
		add(vSpacer1, "cell 0 0");

		//---- label1 ----
		label1.setText("BIENVENIDO");
		label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 3f));
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		add(label1, "cell 0 1");

		//---- label2 ----
		label2.setText("<html><body style=\\\"text-align: justify;  text-justify: inter-word;\\\">Hemos notado que hay ninguna cuenta con ese usuario, puede registrar el usuario</body></html>");
		label2.setFont(new Font("Arial", Font.PLAIN, 12));
		add(label2, "cell 0 2,align center center,grow 0 0");

		//---- inputDNI ----
		inputDNI.setPlaceholder("Numero de DNI");
		add(inputDNI, "cell 0 3");

		//---- inputUsuario ----
		inputUsuario.setPlaceholder("Ingrese el usuario");
		add(inputUsuario, "cell 0 4");
		add(inputPassword, "cell 0 5");
		add(inputPasswordConfirm, "cell 0 6");

		//======== panel1 ========
		{
			panel1.setLayout(new MigLayout("hidemode 3",
			                               // columns
			                               "[grow,sizegroup 1,fill]" + "[grow,sizegroup 1,fill]",
			                               // rows
			                               "[center]"));

			//---- buttonRegistrar ----
			buttonRegistrar.setText("Ingresar");
			buttonRegistrar.addActionListener(e -> registrarUsuario(e));
			panel1.add(buttonRegistrar, "cell 0 0");

			//---- buttonAtras ----
			buttonAtras.setText("ATRAS");
			buttonAtras.addActionListener(e -> buttonAtrasActionPerformed(e));
			panel1.add(buttonAtras, "cell 1 0");
		}
		add(panel1, "cell 0 7");
		add(vSpacer2, "cell 0 8");
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}
	// JFormDesigner - End of variables declaration  //GEN-END:variables

	public void setUsuario(String username) {
		this.inputUsuario.setText(username);
	}
}
