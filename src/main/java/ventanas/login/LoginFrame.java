/*
 * Created by JFormDesigner on Sat Oct 10 22:41:31 COT 2020
 */

package ventanas.login;

import com.formdev.flatlaf.extras.FlatInspector;
import com.formdev.flatlaf.intellijthemes.FlatCobalt2IJTheme;
import com.google.common.flogger.FluentLogger;
import custom_beans.ModernJFrame;
import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.icons.google_material_design_icons.GoogleMaterialDesignIcons;
import jiconfont.swing.IconFontSwing;
import net.miginfocom.swing.MigLayout;
import sql.ConexionPool;
import sql.Usuario;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author Arley
 */
public final class LoginFrame extends ModernJFrame {
	private static final FluentLogger logger = FluentLogger.forEnclosingClass();
	protected static ArrayList<Usuario> usuarios = new ArrayList<>();
	private static LoginFrame INSTANCE;
	static IngresarPanel ingresarPanel = new IngresarPanel(LoginFrame.usuarios.toArray(new Usuario[0]), LoginFrame.getInstance());
	static LoginPanel loginPanel = new LoginPanel(LoginFrame.usuarios.toArray(new Usuario[0]), LoginFrame.getInstance());
	static RegistrarPanel registrarPanel = new RegistrarPanel(LoginFrame.usuarios.toArray(new Usuario[0]), LoginFrame.getInstance());
	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	JPanel cardPanel;

	private LoginFrame() {
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		initComponents();
		useModernStyle();
		llenarCards();
		llenarUsuarios();
	}

	public static LoginFrame getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new LoginFrame();
		}
		return INSTANCE;
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
		FlatCobalt2IJTheme.install();
		FlatInspector.install("ctrl shift alt X");
		IconFontSwing.register(FontAwesome.getIconFont());
		IconFontSwing.register(GoogleMaterialDesignIcons.getIconFont());
	}
	// JFormDesigner - End of variables declaration  //GEN-END:variables

	/**
	 * Main que abre el menu y expone las opciones en forma de un 3x3
	 *
	 * @param args Los valores CLI (nunca implementados)
	 */
	public static void main(String[] args) {
		setTheme();
		new LoginFrame().setVisible(true);
	}

	protected static void llenarUsuarios() {
		usuarios = new ArrayList<>();
		try (Connection connection = ConexionPool.getConnection()) {
			try (Statement statement = connection.createStatement()) {
				try (ResultSet resultSet = statement.executeQuery("SELECT u.id,u.nickname,u.contra,u.salt,u.pista FROM mydb.usuario u")) {
					while (resultSet.next()) {
						usuarios.add(new Usuario(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5)));
					}
				}
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		logger.atInfo().log("Se encontró %s usuarios", usuarios.size());
	}

	private void llenarCards() {
		cardPanel.add(new IngresarPanel(LoginFrame.usuarios.toArray(new Usuario[0]), this), cards.INGRESAR.nombre);
		cardPanel.add(new LoginPanel(LoginFrame.usuarios.toArray(new Usuario[0]), this), cards.LOGIN.nombre);
		cardPanel.add(new RegistrarPanel(LoginFrame.usuarios.toArray(new Usuario[0]), this), cards.REGISTRAR.nombre);
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		cardPanel = new JPanel();

		//======== this ========
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(420, 260));
		setUndecorated(true);
		Container contentPane = getContentPane();
		contentPane.setLayout(new MigLayout("fill,hidemode 3",
		                                    // columns
		                                    "[grow,fill]",
		                                    // rows
		                                    "[grow]"));

		//======== cardPanel ========
		{
			cardPanel.setLayout(new CardLayout());
		}
		contentPane.add(cardPanel, "cell 0 0");
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	public enum cards {
		LOGIN("Login"), REGISTRAR("Registart"), INGRESAR("Ingresar");

		public String nombre;

		cards(String nombre) {
			this.nombre = nombre;
		}
	}
}