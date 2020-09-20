package clases;

import com.google.common.flogger.FluentLogger;
import com.google.common.flogger.StackSize;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static sql.ConexionBaseDeDatos.getConnection;

/**
 * @author Solano Jason
 */
public class Control {

    public static final Connection connection = getConnection();
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    /**
     * El método fillCombo rellena el combo dado con la primera columna de la
     * resulta obtenida del String sql
     *
     * @author Solano Jason
     * @version 1.0
     * @since 2020-09-08
     */
    public static void fillCombo(JComboBox<String> comboBox, String sql) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            comboBox.removeAllItems();
            while (resultSet.next()) {
                comboBox.addItem(resultSet.getString(1));
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
    public static void fillTable(DefaultTableModel tableModel, String sql, int n) {
        limTable(tableModel);
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            String[] data = new String[n];
            while (resultSet.next()) {
                for (int i = 0; i <= n - 1; i++) {
                    data[i] = resultSet.getString(i + 1);
                }
                tableModel.addRow(data);
            }
        } catch (SQLException ex) {
            logger.atSevere().withStackTrace(StackSize.FULL).withCause(ex).log("Hubo un error al intentar llenar la tabla");
        }
    }

    /**
     * Rellena la lista con la consulta sql
     */
    public static void fillList(DefaultListModel listModel, String sql) {
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            listModel.removeAllElements();
            while (rs.next()) {
                listModel.addElement(rs.getString(1));
            }
        } catch (SQLException ex) {
            System.err.print(ex);
        }
    }

    /**
     * @param sql comando SQL a ejecutar
     * @return retorna una cadena con el valor de la primera columna de la
     * primera fila
     */
    public static String returnData(String sql) {
        String data = "";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            data = resultSet.getString(1);
        } catch (SQLException ex) {
            logger.atSevere().withStackTrace(StackSize.FULL).withCause(ex).log("Hubo un error en returnData");
        }
        return data;
    }

    public static boolean checkQuery(String sql) {
        boolean check = false;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultset = statement.executeQuery(sql);
            check = resultset.next();
        } catch (SQLException ex) {
            logger.atSevere().withStackTrace(StackSize.FULL).withCause(ex).log("Hubo un error en checkQuery");
        }
        return check;
    }

    public static int updateTable(String sql) {
        int rowsAffected=0;
        try {
            Statement statement = connection.createStatement();
            rowsAffected = statement.executeUpdate(sql);
        } catch (SQLException ex) {
            logger.atSevere().withStackTrace(StackSize.FULL).withCause(ex).log("Hubo un error en updateTable");
        }
        return rowsAffected;
    }
}
