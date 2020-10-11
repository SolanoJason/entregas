/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import sql.ConexionPool;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 * @author Pajas
 */
public enum Report {
    ;

    /**
     * Muestra un Reporte estatico Además devuelve un objeto JasperViewer para
     * poder configurar como se va a mostrar el reporte al gusto
     */
    public static JasperViewer showReport(String path) {
        try (Connection connection = ConexionPool.getConnection()) {
            JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, connection);
            JasperViewer view = crearJasperView(jasperPrint);
            if (view != null)
                return view;
        } catch (JRException | SQLException ex) {
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Se encarga de la obtención de la vista del Jasper, verificando si no hay paginas antes
     *
     * @param jasperPrint El jasper a ser mostrado
     * @return La vista del Jasper
     */
    private static JasperViewer crearJasperView(JasperPrint jasperPrint) {
        if (jasperPrint.getPages().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El documento no tiene paginas");
        } else {
            JasperViewer view = new JasperViewer(jasperPrint, false);
            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            view.setVisible(true);
            view.setExtendedState(6);
            return view;
        }
        return null;
    }

    /**
     * Crea el jasper view, usando la conexión, el reporte y parámetros
     *
     * @param connection Conexión a la base de datos
     * @param reporte    El reporte base para crear la vista
     * @param parameters Los parámetros que alimentan al reporte
     * @return La vista de Jasper
     * @throws JRException Es botada si hay algún error con la creación del JasperView
     */
    private static JasperViewer crearJasperView(Connection connection, JasperReport reporte, HashMap parameters) throws JRException {
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parameters, connection);
        return crearJasperView(jasperPrint);
    }

    /**
     * Muestra un Reporte con 1 parámetro Además devuelve un objeto
     * JasperViewer para poder configurar como se va a mostrar el reporte al
     * gusto
     */
    public static JasperViewer showReport(String path, String parameter, String value) {
        try (Connection connection = ConexionPool.getConnection()) {
            JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
            HashMap parameters = new HashMap<>(1);
            parameters.put(parameter, value);
            JasperViewer view = crearJasperView(connection, reporte, parameters);
            if (view != null)
                return view;
        } catch (JRException | SQLException ex) {
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Muestra un Reporte con 1 o más parametros Además devuelve un objeto
     * JasperViewer para poder configurar como se va a mostrar el reporte al
     * gusto
     */
    public static JasperViewer showReport(String path, HashMap parameters) {
        try (Connection connection = ConexionPool.getConnection()) {
            JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
            JasperViewer view = crearJasperView(connection, reporte, parameters);
            if (view != null)
                return view;
        } catch (JRException | SQLException ex) {
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
