package ventanas.login;

import clases.Control;
import clases.Password;
import custom_beans.JPlaceholderTextField;
import custom_beans.ToastMessage;
import net.miginfocom.swing.MigLayout;
import sql.Usuario;
import ventanas.Menu;

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
public final class LoginPanel extends JPanel {
	final LoginFrame padre;
	private final List<Usuario> usuarios;
	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JPlaceholderTextField inputUsuario;
	private JPasswordField inputPassword;
	private JButton buttonIngresar;
	private JButton buttonCancelar;

	public LoginPanel(Usuario[] usuarios, LoginFrame padre) {
		this.usuarios = Arrays.asList(usuarios);
		this.padre = padre;
		initComponents();
		configurarFlujo();
	}

	public void configurarFlujo() {
		Control.siguienteJComponent(inputUsuario, inputPassword);
	}

	private void validarUsuarioPassword(ActionEvent e) {
		if (inputUsuario.getText().trim().isEmpty()) {
			new ToastMessage(this, "Debe ingresar un usuario", 2000);
		} else if (inputPassword.getPassword().length == 0) {
			new ToastMessage(this, "Debe ingresar la contraseña", 2000);
		} else {
			Optional<Usuario> selectedUser = usuarios.stream().filter(user -> user.nickname.equals(inputUsuario.getText())).findFirst();
			if (selectedUser.isPresent()) {
				if (Password.verificarPassword(inputPassword.getPassword(), selectedUser.get().contra, selectedUser.get().salt)) {
					this.setVisible(false);
					new Menu().setVisible(true);
				} else {
					new ToastMessage(this, "La contraseña ingresada es incorrecta", 2000);
				}
			} else {
				new ToastMessage(this, MessageFormat.format("El usuario {0} No esta registrado", inputUsuario.getText()), 3000);
			}
		}
	}

	private void registrar(ActionEvent e) {
		// TODO add your code here
	}

	public void setUsuario(String username) {
		this.inputUsuario.setText(username);
	}

	private void buttonCancelarActionPerformed(ActionEvent e) {
		((CardLayout) padre.cardPanel.getLayout()).show(padre.cardPanel, LoginFrame.cards.INGRESAR.nombre);
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		JPanel vSpacer1 = new JPanel(null);
		JLabel label1 = new JLabel();
		inputUsuario = new JPlaceholderTextField();
		inputPassword = new JPasswordField();
		JPanel panel1 = new JPanel();
		buttonIngresar = new JButton();
		buttonCancelar = new JButton();
		JPanel vSpacer2 = new JPanel(null);

		//======== this ========
		setLayout(new MigLayout("hidemode 3",
		                        // columns
		                        "[grow,fill]",
		                        // rows
		                        "[grow,sizegroup 1]" + "[]" + "[]" + "[]" + "[]" + "[grow,sizegroup 1]"));
		add(vSpacer1, "cell 0 0");

		//---- label1 ----
		label1.setText("Ingrese a su cuenta");
		label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 3f));
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		add(label1, "cell 0 1");

		//---- inputUsuario ----
		inputUsuario.setPlaceholder("Usuario");
		inputUsuario.setPlaceholder("Contraseña");
		add(inputUsuario, "cell 0 2");
		add(inputPassword, "cell 0 3");

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
			buttonCancelar.setText("Atras");
			buttonCancelar.addActionListener(e -> buttonCancelarActionPerformed(e));
			panel1.add(buttonCancelar, "cell 1 0");
		}
		add(panel1, "cell 0 4");
		add(vSpacer2, "cell 0 5");
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
