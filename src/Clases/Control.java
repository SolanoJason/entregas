package Clases;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Pajas
 */
public class Control {

    public static Connection con = getConnection();
    public static Statement st;
    public static PreparedStatement ps;
    public static ResultSet rs;

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/unasam?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "root", "0112358132134");
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Conexion fallida");
        }
        return con;
    }

    /**
     * El método fillCombo rellena el combo dado con la primera columna de la
     * resulta obtenida del String sql
     *
     * @author Solano Jason
     * @version 1.0
     * @since 2020-09-08
     */
    public static void fillCombo(JComboBox combo, String sql) {
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            combo.removeAllItems();
            while (rs.next()) {
                combo.addItem(rs.getString(1));
            }
        } catch (SQLException ex) {
            System.err.print(ex);
        }
        combo.setSelectedIndex(-1);
    }

    /**
     * Remueve todas las filas de la tabla
     */
    public static void limTable(DefaultTableModel md) {
        while (md.getRowCount() > 0) {
            md.removeRow(0);
        }
    }

    /**
     * Rellena la tabla con la consulta sql
     */
    public static void fillTable(DefaultTableModel md, String sql, int n) {
        limTable(md);
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            String data[] = new String[n];
            while (rs.next()) {
                for (int i = 0; i <= n - 1; i++) {
                    data[i] = rs.getString(i + 1);
                }
                md.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Retorna una cadena con el valor de la primera columna de la primera fila
     * de la consulta sql
     *
     * @param sql
     * @return
     */
    public static String returnData(String sql) {
        String data = "";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            rs.next();
            data = rs.getString(1);
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return data;
    }

    /**
     * El método isFieldEmpty recibe como argumento un array de JTextField y
     * valida si todos están vacíos, de ser así retorna true y muestra un
     * mensaje con todos campos que faltan rellenar. Es necesario usar el metodo
     * setName para nombrar a los JTextField
     *
     * @author Solano Jason
     * @version 1.0
     * @since 2020-09-08
     */
    public static boolean isFieldEmpty(JTextField... field) {
        boolean retornar = false;
        String mensaje = "";
        for (int i = 0; i <= field.length - 1; i++) {
            if (field[i].getText().trim().length() == 0) {
                retornar = true;
                if (mensaje.equals("")) {
                    mensaje += field[i].getName();
                } else {
                    mensaje += ", " + field[i].getName();
                }
            }
        }
        if (retornar) {
            JOptionPane.showMessageDialog(null, mensaje + " faltan rellenar");
        }
        return retornar;
    }
}
