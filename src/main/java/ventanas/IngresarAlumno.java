/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import com.google.common.flogger.FluentLogger;
import com.google.common.flogger.StackSize;
import sql.ConexionPool;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static java.awt.Frame.NORMAL;

/**
 * @author Lenovo
 */
public final class IngresarAlumno extends JDialog {
	private static final FluentLogger logger = FluentLogger.forEnclosingClass();
	private final JFrame padre;
	/**
	 * Variables usadas para llenar el JCombobox
	 */
	String[] nombreEscuela;
	int[] idEscuela;
	/**
	 * booleano para saber si ya se puede proceder con el agregado
	 */
	boolean listo = true;
	/**
	 * Jlabels usados para identificar los campos
	 * Por favor java puede manejar variables con mas de 3 letras, y usar nombres expresivos permite entender el código de los demas
	 * Literalmente no se que son estos
	 */
	JLabel encabezadoLabel = new JLabel("Bienvenido al registro del alumno");
	JLabel requerimientoLabel = new JLabel("Porfavor llene los campos (* campos obligatorios)");
	JLabel nombreLabel = new JLabel("*Nombre:");
	JLabel apellidoLabel = new JLabel("*Apellido:");
	JLabel codigoLabel = new JLabel("*Codigo:");
	JLabel escuelaLabel = new JLabel("*Escuela:");
	JLabel dniLabel = new JLabel("*DNI:");
	JLabel estadoLabel = new JLabel("*Estado:");
	JLabel direccionLabel = new JLabel("Dirección:");

	/**
	 * JTextFields usados para que el usuario pueda registrar
	 */
	JTextField nombre = new JTextField();
	JTextField apellido = new JTextField();
	JTextField codigo = new JTextField();
	JTextField dni = new JTextField();
	JTextField direccion = new JTextField();

	/**
	 * JButtons usados para implementar lo deseado
	 */
	JButton limpiar = new JButton("Limpiar");
	JButton cancelar = new JButton("Cancelar");
	JButton agregar = new JButton("Terminar");

	/**
	 * JCombobox usado para mostrar las posibles escuelas
	 */
	JComboBox<String> escuelas = new JComboBox<>();

	/**
	 * JRadioButtons para las opciones del sisfoh
	 */
	JRadioButton noPobre = new JRadioButton("No pobre");
	JRadioButton pobre = new JRadioButton("Pobre");
	JRadioButton pobreExtremo = new JRadioButton("Pobre extremo");

	/**
	 * JPanel para ayudar en la muestra de los objetos
	 */
	JPanel panelPrincipal = new JPanel();


	/**
	 * Método usado para superponer a otro frame, bloqueándolo
	 */
	public IngresarAlumno(JFrame padre) {
		super(padre, true);
		this.padre = padre;
		setSize(800, 500);
		setLocation(200, 200);
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		addKeyListeners();
		addActionListeners();

		// llamando al método  para llenar el JComboBox
		llenandoEscuelas(escuelas);

		setupLayout();
	}

	/**
	 * Función que configura cual es el siguiente text field a pasar cuando se usa presiona tab o enter
	 *
	 * @param origen  JTextField de origen
	 * @param destino JTextField al cual se va a saltar, el objetivo
	 */
	private static void siguienteTextField(JTextField origen, JTextField destino) {
		origen.addKeyListener(new KeyListener() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_TAB || e.getKeyChar() == KeyEvent.VK_ENTER) {
					destino.grabFocus();
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				//Ignorado
			}

			@Override
			public void keyPressed(KeyEvent e) {
				//Ignorado
			}
		});
	}

	/**
	 * usado para usar el procedure registrar_alumno de la base de datos
	 * <p>
	 * En este caso el campo sis debe ser especificado ya que solo tiene unas pocas posibilidades de ingreso
	 *
	 * @param codigo    código del alumno
	 * @param escuela   escuela del alumno
	 * @param dni       DNI
	 * @param sis       Estado en el SIS ('no pobre', 'pobre', 'pobre extremo')
	 * @param direccion Dirección del alumno
	 * @param nombre    Nombre del alumno
	 * @param apellido  Apellido del alumno
	 */
	public static void agregando(String codigo, int escuela, String dni, String sis, String direccion, String nombre, String apellido) {
		try (Connection connection = ConexionPool.getConnection()) {
			String sql = "call registrar_alumno('" + codigo + "','" + escuela + "','" + dni + "','" + sis + "','" + direccion + "','" + nombre + "','" + apellido + "')";
			try (PreparedStatement ps = connection.prepareStatement(sql)) {
				try (ResultSet ignored = ps.executeQuery()) {
					logger.atFiner().log("Alumno agregado");
				}
			}
		} catch (Exception e) {
			logger.atSevere().withStackTrace(StackSize.FULL).withCause(e).log("Hubo un error al agregar un alumno");
		}
	}

	/**
	 * usado para saber cuantas lineas hay en una tabla
	 *
	 * @param tabla nombre de la tabla a leer
	 * @return cantidad de lineas en la tabla
	 */
	public static int contarLineasEnTabla(String tabla) {
		int cont = 0;
		try (Connection connection = ConexionPool.getConnection()) {
			String sql = "SELECT * FROM " + tabla;
			try (PreparedStatement ps = connection.prepareStatement(sql)) {
				try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
						cont++;
					}
				}
			}
		} catch (Exception e) {
			logger.atSevere().withStackTrace(StackSize.FULL).withCause(e).log("Hubo un error al contar las lineas de la tabla %s", tabla);
		}
		return cont;
	}

	/**
	 * usado para saber si se presiono algún numero
	 *
	 * @param evento evento generado al presionar un botón
	 * @return true si fue presionado un numero
	 */
	public static boolean esUnNumeroPresionado(KeyEvent evento) {
		// El switch puede hacer ese trabajo mas efectivamente y mas comprensivo al leerlo
		// También puede usarse el método isANumber() o algo asi era no recuerdo..
		switch (evento.getKeyChar()) {
			case KeyEvent.VK_0:
			case KeyEvent.VK_1:
			case KeyEvent.VK_2:
			case KeyEvent.VK_3:
			case KeyEvent.VK_4:
			case KeyEvent.VK_5:
			case KeyEvent.VK_6:
			case KeyEvent.VK_7:
			case KeyEvent.VK_8:
			case KeyEvent.VK_9:
				return true;
			default:
				return false;
		}
	}

	/**
	 * Usado para comprobar si ya existe el dato en la tabla estudiante
	 *
	 * @param codigo código que identifica al alumno
	 * @return true en caso de que haya un alumno con el código registrado
	 */
	public static boolean elCodigoEsRepetido(String codigo) {
		boolean comp = false;
		try (Connection connection = ConexionPool.getConnection()) {
			String sql = "SELECT codigo FROM estudiante";
			try (PreparedStatement ps = connection.prepareStatement(sql)) {
				try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
						if (codigo.equals(rs.getString(1))) {
							comp = true;
						}
					}
				}
			}
		} catch (Exception e) {
			logger.atSevere().withStackTrace(StackSize.FULL).withCause(e).log("Hubo un error al comprobar si ya existe el alumno");
		}
		return comp;
	}

	private void addKeyListeners() {
		// acción para poder pasar a la casilla apellido
		siguienteTextField(nombre, apellido);

		// acción utilizada para cambiar su edición
		siguienteTextField(apellido, codigo);

		// (released)acción para poder pasar a la casilla dni
		// (pressed)acción para poder restringir solo números y punto
		codigo.addKeyListener(new KeyListener() {
			String aux = "";
			String aux2 = "";

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_TAB || e.getKeyChar() == KeyEvent.VK_ENTER) {
					dniLabel.grabFocus();
				}
				// restricción
				if (!codigo.getText().isEmpty()) {
					aux2 = codigo.getText();
				}
				if (esUnNumeroPresionado(e) || e.getKeyChar() == KeyEvent.VK_PERIOD) {
					aux = codigo.getText();
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				//Ignorado
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (!(((esUnNumeroPresionado(e) || e.getKeyChar() == KeyEvent.VK_TAB) || (e.getKeyChar() == KeyEvent.VK_ENTER || e.getKeyChar() == KeyEvent.VK_PERIOD)) || (e.getKeyChar() == KeyEvent.VK_BACK_SPACE))) {
					JOptionPane.showMessageDialog(null, "Porfavor escriba solo caracteres permitidos(0,1,2,...,9,'.')");
					if (aux.isEmpty()) {
						codigo.setText("");
					} else {
						codigo.setText(aux);
					}
				} else {
					if (!codigo.getText().isEmpty()) {
						aux = codigo.getText();
					}
				}
			}
		});

		// acción utilizada para cambiar su edición
		// (released) acción para poder pasar al Jcombobox
		// (released,pressed) acción para poder restringir solo números
		dni.addKeyListener(new KeyListener() {
			int aux;
			int aux2;

			@Override
			public void keyReleased(KeyEvent e) {
				// pasar al JComboBox
				if (e.getKeyChar() == KeyEvent.VK_TAB || e.getKeyChar() == KeyEvent.VK_ENTER) {
					escuelas.grabFocus();
				}
				// restricción
				if (!dni.getText().isEmpty()) {
					aux2 = Integer.parseInt(dni.getText());
				}
				if (aux2 > 100000000) {
					dni.setText(String.valueOf(aux));
				}
				if (esUnNumeroPresionado(e)) {
					aux = Integer.parseInt(dni.getText());
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				//Ignorado
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (!((esUnNumeroPresionado(e) || e.getKeyChar() == KeyEvent.VK_TAB) || (e.getKeyChar() == KeyEvent.VK_ENTER || e.getKeyChar() == KeyEvent.VK_BACK_SPACE))) {
					JOptionPane.showMessageDialog(null, "Porfavor escriba solo caracteres permitidos(0,1,2,...,9)");
					if (aux == 0) {
						dni.setText("");
					} else {
						dni.setText(String.valueOf(aux));
					}
				} else {
					if (!dni.getText().isEmpty()) {
						aux = Integer.parseInt(dni.getText());
					}
				}

			}
		});

		// Acción para pasar al JButton terminar o presionarlo directamente
		direccion.addKeyListener(new KeyListener() {
			@Override
			public void keyReleased(KeyEvent e) {
				// pasar
				if (e.getKeyChar() == KeyEvent.VK_TAB) {
					agregar.grabFocus();
				}
				// presionar
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					agregarUsuario();
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				//Ignorado
			}

			@Override
			public void keyPressed(KeyEvent e) {
				//Ignorado
			}
		});
	}

	private void addActionListeners() {
		// acción utilizada para cambiar su edición
		nombre.addActionListener(e -> {
			//TODO: esto es correcto que este en blanco?
		});

		// método creado por su servidor para que solo se seleccione un JRadioButton
		// Si se ponen estos botones un un grupo (creo que asi se llama) solo permite seleccionar uno sin necesidad de esto
		// es mas fazil
		noPobre.addActionListener(e -> {
			pobre.setSelected(false);
			pobreExtremo.setSelected(false);
		});

		pobre.addActionListener(e -> {
			noPobre.setSelected(false);
			pobreExtremo.setSelected(false);
		});

		pobreExtremo.addActionListener(e -> {
			pobre.setSelected(false);
			noPobre.setSelected(false);
		});

		// Botón usado para borrar todos los datos puestos
		limpiar.addActionListener(e -> {
			nombre.setText("");
			apellido.setText("");
			codigo.setText("");
			dni.setText("");
			direccion.setText("");
			escuelas.setSelectedIndex(-1);
			noPobre.setSelected(false);
			pobre.setSelected(false);
			pobreExtremo.setSelected(false);
		});

		// Botón usado para llamar al método agregando
		agregar.addActionListener(e -> agregarUsuario());

		// Botón usado para llamar al método dispose
		cancelar.addActionListener(e -> dispose());
	}

	private void setupLayout() {
		panelPrincipal.setLayout(null);

		// se añade todo al panel
		panelPrincipal.add(encabezadoLabel);
		panelPrincipal.add(requerimientoLabel);
		panelPrincipal.add(nombreLabel);
		panelPrincipal.add(nombre);
		panelPrincipal.add(apellidoLabel);
		panelPrincipal.add(apellido);
		panelPrincipal.add(codigoLabel);
		panelPrincipal.add(codigo);
		panelPrincipal.add(dniLabel);
		panelPrincipal.add(dni);
		panelPrincipal.add(escuelaLabel);
		panelPrincipal.add(escuelas);
		panelPrincipal.add(estadoLabel);
		panelPrincipal.add(noPobre);
		panelPrincipal.add(pobre);
		panelPrincipal.add(pobreExtremo);
		panelPrincipal.add(direccionLabel);
		panelPrincipal.add(direccion);
		panelPrincipal.add(limpiar);
		panelPrincipal.add(cancelar);
		panelPrincipal.add(agregar);
		panelPrincipal.setSize(800, 500);

		// a cada objeto se le da sus parámetros
		encabezadoLabel.setSize(300, 25);
		encabezadoLabel.setLocation(300, 10);
		encabezadoLabel.setVisible(true);

		requerimientoLabel.setSize(450, 25);
		requerimientoLabel.setLocation(20, 40);
		requerimientoLabel.setVisible(true);

		nombreLabel.setSize(100, 25);
		nombreLabel.setLocation(20, 80);
		nombreLabel.setVisible(true);

		nombre.setSize(250, 25);
		nombre.setLocation(100, 80);
		nombre.setVisible(true);

		apellidoLabel.setSize(100, 25);
		apellidoLabel.setLocation(420, 80);
		apellidoLabel.setVisible(true);

		apellido.setSize(250, 25);
		apellido.setLocation(500, 80);
		apellido.setVisible(true);

		codigoLabel.setSize(80, 25);
		codigoLabel.setLocation(20, 120);
		codigoLabel.setVisible(true);

		codigo.setSize(100, 25);
		codigo.setLocation(100, 120);
		codigo.setVisible(true);

		dniLabel.setSize(100, 25);
		dniLabel.setLocation(420, 120);
		dniLabel.setVisible(true);

		dni.setSize(100, 25);
		dni.setLocation(500, 120);
		dni.setVisible(true);

		escuelaLabel.setSize(80, 25);
		escuelaLabel.setLocation(20, 160);
		escuelaLabel.setVisible(true);

		escuelas.setSize(200, 25);
		escuelas.setLocation(100, 160);
		escuelas.setVisible(true);

		estadoLabel.setSize(100, 25);
		estadoLabel.setLocation(320, 160);
		estadoLabel.setVisible(true);

		noPobre.setSize(100, 25);
		noPobre.setLocation(400, 160);
		noPobre.setVisible(true);

		pobre.setSize(100, 25);
		pobre.setLocation(500, 160);
		pobre.setVisible(true);

		pobreExtremo.setSize(200, 25);
		pobreExtremo.setLocation(600, 160);
		pobreExtremo.setVisible(true);

		direccionLabel.setSize(100, 25);
		direccionLabel.setLocation(20, 200);
		direccionLabel.setVisible(true);

		direccion.setSize(450, 25);
		direccion.setLocation(100, 200);
		direccion.setVisible(true);

		limpiar.setSize(100, 25);
		limpiar.setLocation(350, 350);
		limpiar.setVisible(true);

		agregar.setSize(100, 25);
		agregar.setLocation(500, 350);
		agregar.setVisible(true);

		cancelar.setSize(100, 25);
		cancelar.setLocation(650, 350);
		cancelar.setVisible(true);

		add(panelPrincipal);
	}

	/**
	 * usado para llenar un combo con las escuelas de la base de datos
	 *
	 * @param comboBox combo box a ser llenado
	 */
	public void llenandoEscuelas(JComboBox<String> comboBox) {
		int cont = contarLineasEnTabla("escuela");
		nombreEscuela = new String[cont];
		idEscuela = new int[cont];
		try (Connection connection = ConexionPool.getConnection()) {
			String sql = "SELECT * FROM escuela";
			try (PreparedStatement ps = connection.prepareStatement(sql)) {
				try (ResultSet rs = ps.executeQuery()) {
					int cont2 = 0;
					while (rs.next()) {
						nombreEscuela[cont2] = rs.getString(2);
						idEscuela[cont2] = rs.getInt(1);
						cont2++;
					}
					comboBox.setModel(new javax.swing.DefaultComboBoxModel<>(nombreEscuela));
					comboBox.setSelectedIndex(-1);
				}
			}
		} catch (Exception e) {
			//No ignorar las excepciones, ingresarlas al log, en caso de un error se puede saber que paso mal
			logger.atSevere().withStackTrace(StackSize.FULL).withCause(e).log("Hubo un error al llenar las escuelas");
		}
	}

	/**
	 * usado para corroborar que todas las casillas estén completas y mandar al procedure
	 */
	public void agregarUsuario() {
		if (todoCorrecto()) {
			if (JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea registrar a este usuario?", "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE) == JOptionPane.YES_OPTION) {
				String codigoIngresado = codigo.getText();
				int idEscuelaIngresado = idEscuela[escuelas.getSelectedIndex()];
				String dniIngresado = dni.getText();
				String sisfoh = "";
				// Para este selección un enum seria mas efectivo
				if (noPobre.isSelected()) {
					sisfoh = "no pobre";
				} else if (pobre.isSelected()) {
					sisfoh = "pobre";
				} else if (pobreExtremo.isSelected()) {
					sisfoh = "pobre extremo";
				}
				String direcc = direccion.getText();
				// Por la forma en la que la base de datos funciona si se pasa null se va a ingresar dirección desconocida (se usó un default)
				if (direcc.isEmpty()) {
					direcc = null;
				}

				String nombreIngresado = nombre.getText();
				String apellidoIngresado = apellido.getText();

				agregando(codigoIngresado, idEscuelaIngresado, dniIngresado, sisfoh, direcc, nombreIngresado, apellidoIngresado);
			}
		} else {
			logger.atSevere().log("No se ingreso al usuario %s", nombre.getText());
		}

	}

	/**
	 * Usado para comprobar y notificar que falta llenar una casilla
	 * <p>
	 * Es mejor retornar un boolean a usar una variable del objeto, es mas entendible, ademas que limita los efectos secundarios
	 */
	public boolean todoCorrecto() {
		boolean fallas = false;
		// comprobando nombre
		if (nombre.getText().isEmpty()) {
			logger.atWarning().log("Falta ingresar el nombre");
			fallas = true;
		}

		// comprobando apellido
		if (apellido.getText().isEmpty()) {
			logger.atWarning().log("Falta ingresar el apellido");
			fallas = true;
		}

		// comprobando código
		if (codigo.getText().isEmpty()) {
			logger.atWarning().log("Falta ingresar el codigo");
			fallas = true;
		}

		// comprobando si existe el alumno previamente
		if (elCodigoEsRepetido(codigo.getText())) {
			logger.atWarning().log("El alumno ya ha sido ingresado");
			fallas = true;
		}

		// comprobando el DNI
		if (dni.getText().isEmpty()) {
			logger.atWarning().log("Falta ingresar el DNI");
			fallas = true;
		} else {
			// Acá un métodos mas efectivo es solo ver la cantidad de caracteres
			int size = dni.getText().trim().length();
			if (size != 8) {
				logger.atWarning().log("El DNI ingresado no tiene 8 numerós");
				fallas = true;
			}
		}
		// Una misma persona puede tener dos códigos, cada ingreso genera un nuevo código, si vuelve a postular se vuelve a generar
		// otro código

		// Comprobando escuelas
		if (escuelas.getSelectedIndex() == -1) {
			logger.atWarning().log("Falta seleccionar una escuela");
			fallas = true;
		}

		// comprobando estado
		if (!((noPobre.isSelected() || pobre.isSelected()) || pobreExtremo.isSelected())) {
			logger.atWarning().log("Falta seleccionar una clasificación del SIS");
			listo = false;
		}

		// La función esta invertida, no se me ocurre un buen nombre que sea lo contrario, HELP plz
		return !fallas;
	}

	@Override
	public void dispose() {
		padre.setExtendedState(NORMAL);
		super.dispose();
	}

}
