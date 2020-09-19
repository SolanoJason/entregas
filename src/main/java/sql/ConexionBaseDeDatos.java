package sql;

import com.google.common.flogger.FluentLogger;
import com.google.common.flogger.StackSize;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBaseDeDatos {
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();
    private static Connection con = null;

    static {
        String url = "jdbc:mysql://maria.db.arllk.com:43306/mydb";
        String user = "root";
        String pass = "l33tsupah4x0r";
        try {
            con = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            logger.atSevere().withStackTrace(StackSize.FULL).withCause(e).log("Hubo un error al conectarse con la base de datos");
        }
    }

    private ConexionBaseDeDatos() {
        throw new IllegalStateException("Utility class");
    }

    public static Connection getConnection() {
        return con;
    }
}