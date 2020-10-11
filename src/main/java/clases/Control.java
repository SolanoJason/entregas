package clases;

import com.google.common.flogger.FluentLogger;
import com.google.common.flogger.StackSize;
import sql.ConexionPool;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

/**
 * @author Solano Jason
 */
public enum Control {
	;
	private static final FluentLogger logger = FluentLogger.forEnclosingClass();

	/**
	 * El método fillCombo rellena el combo dado con la primera columna de la
	 * resulta obtenida del String sql
	 *
	 * @author Solano Jason El tag @version se usa normalmente en APIs o
	 * librerias, en caso de una aplicacion no aplican normalmente // @version
	 * 1.0
	 * @since 2020-09-08
	 */
	public static void fillCombo(JComboBox<String> comboBox, String sql) {
		try (Connection connection = ConexionPool.getConnection()) {
			try (Statement statement = connection.createStatement()) {
				try (ResultSet resultSet = statement.executeQuery(sql)) {
					comboBox.removeAllItems();
					while (resultSet.next()) {
						comboBox.addItem(resultSet.getString(1));
					}
				}
			}
		} catch (SQLException ex) {
			logger.atSevere().withStackTrace(StackSize.FULL).withCause(ex).log("Hubo un error al intentar llenar el ComboBox");
		}
		comboBox.setSelectedIndex(-1);
	}

	/**
	 * Remueve todas las filas de la tabla
	 */
	public static void limTable(DefaultTableModel tableModel) {
		while (tableModel.getRowCount() > 0) {
			tableModel.removeRow(0);
		}
	}

	/**
	 * Rellena la tabla con la consulta sql
	 */
	public static void fillTableModel(DefaultTableModel tableModel, String sql, int n) {
		limTable(tableModel);
		try (Connection connection = ConexionPool.getConnection()) {
			try (Statement statement = connection.createStatement()) {
				try (ResultSet resultSet = statement.executeQuery(sql)) {
					String[] data = new String[n];
					while (resultSet.next()) {
						for (int i = 0; i <= n - 1; i++) {
							data[i] = resultSet.getString(i + 1);
						}
						tableModel.addRow(data);
					}
				}
			}
		} catch (SQLException ex) {
			logger.atSevere().withStackTrace(StackSize.FULL).withCause(ex).log("Hubo un error al intentar llenar la tabla");
		}
	}

	/**
	 * Rellena la lista con la consulta sql
	 */
	public static void fillList(DefaultListModel<String> listModel, String sql) {
		try (Connection connection = ConexionPool.getConnection()) {
			try (Statement st = connection.createStatement()) {
				try (ResultSet rs = st.executeQuery(sql)) {
					listModel.removeAllElements();
					while (rs.next()) {
						listModel.addElement(rs.getString(1));
					}
				}
			}
		} catch (SQLException ex) {
			logger.atSevere().withStackTrace(StackSize.FULL).withCause(ex).log("Hubo un error al llenar la lista con comando SQL: %s", sql);
		}
	}

	/**
	 * @param sql comando SQL a ejecutar
	 * @return retorna una cadena con el valor de la primera columna de la
	 * primera fila
	 */
	public static String returnData(String sql) {
		String data = "";
		try (Connection connection = ConexionPool.getConnection()) {
			try (Statement statement = connection.createStatement()) {
				try (ResultSet resultSet = statement.executeQuery(sql)) {
					resultSet.next();
					data = resultSet.getString(1);
				}
			}
		} catch (SQLException ex) {
			logger.atSevere().withStackTrace(StackSize.FULL).withCause(ex).log("Hubo un error en returnData");
		}
		return data;
	}

	/**
	 * Retorna TRUE si la consulta retorna al menos una fila
	 * Retorna FALSE si la consulta falla o no retorna nada
	 */
	public static boolean checkQuery(String sql) {
		boolean check = false;
		try (Connection connection = ConexionPool.getConnection()) {
			try (Statement statement = connection.createStatement()) {
				try (ResultSet resultset = statement.executeQuery(sql)) {
					check = resultset.next();
				}
			}
		} catch (SQLException ex) {
			logger.atSevere().withStackTrace(StackSize.FULL).withCause(ex).log("Hubo un error en checkQuery");
		}
		return check;
	}

	/**
	 * Metodo para realizar un DELETE,UPDATE o INSERT con una consulta SQL
	 * Retorna un INTEGER con valor del número de filas afectadas por la consulta
	 */

	public static int update(String sql) {
		int rowsAffected = 0;

		// Un try with resources, el problema de que ocurra una exception en esta function es que la connexion
		// sera creada y no podrÃ¡ ser recogida por el garbage collector, lo cual genera un memory leak
		// El try with resources en caso de una exception va a cerrar automÃ¡ticamente la connexion y no se producirÃ¡
		// el memory leak
		try (Connection connection = ConexionPool.getConnection()) {
			try (Statement statement = connection.createStatement()) {
				rowsAffected = statement.executeUpdate(sql);
			}
		} catch (SQLException ex) {
			logger.atSevere().withStackTrace(StackSize.FULL).withCause(ex).log("Hubo un error en updateTable");
		}
		return rowsAffected;
	}

	public static void fillTable2(JTable tabla, String sql) {

		try (Connection connection = ConexionPool.getConnection()) {
			try (Statement statement = connection.createStatement()) {
				ResultSet resultSet = statement.executeQuery(sql);

				int cantidadFila = llenarEncabezadoTabla(tabla, resultSet);
				DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
				limTable(modelo);
				Object[] fila = new Object[cantidadFila];

				while (resultSet.next()) {
					for (int i = 0; i < cantidadFila; i++) {

						fila[i] = resultSet.getString(i + 1);

					}

					modelo.addRow(fila);

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static int llenarEncabezadoTabla(JTable tabla, ResultSet res) {
		String[] cabecera;
		ResultSetMetaData meta;
		int cant = 0;
		try {
			meta = res.getMetaData();
			cant = meta.getColumnCount();
			cabecera = new String[cant];
			int i = 0;

			//bucle para obtener el nombre de las columnas de los registros
			for (; i < cant; i++) {
				cabecera[i] = meta.getColumnName(i + 1);
			}

			tabla.setModel(new DefaultTableModel(new Object[][]{}, cabecera) {
				@Override
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return false;
				}
			});

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return cant;

	}
}
