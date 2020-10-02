/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Pajas
 */
public class Report {

    private static Connection con = Control.connection;

    /**
     * Muestra un Reporte estatico
     * Además devuelve un objeto JasperViewer para poder configurar como se va a mostrar
     * el reporte al gusto
     */
    public static JasperViewer showReport(String path) {
        try {
            JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
            JasperPrint jprint = JasperFillManager.fillReport(reporte, null, con);
            JasperViewer view = new JasperViewer(jprint, false);
            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            view.setVisible(true);
            return view;
        } catch (JRException ex) {
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    /**
     * Muestra un Reporte con 1 parametro
     * Además devuelve un objeto JasperViewer para poder configurar como se va a mostrar
     * el reporte al gusto
     */
    public static JasperViewer showReport(String path, String parameter, String value) {
        try {
            JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
            Map parameters = new HashMap(1);
            parameters.put(parameter, value);
            JasperPrint jprint = JasperFillManager.fillReport(reporte, parameters, con);
            JasperViewer view = new JasperViewer(jprint, false);
            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            view.setVisible(true);
            return view;
        } catch (JRException ex) {
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    /**
     * Muestra un Reporte con 1 o más parametros
     * Además devuelve un objeto JasperViewer para poder configurar como se va a mostrar
     * el reporte al gusto
     */
    public static JasperViewer showReport(String path, HashMap parameters) {
        try {
            JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
            JasperPrint jprint = JasperFillManager.fillReport(reporte, parameters, con);
            JasperViewer view = new JasperViewer(jprint, false);
            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            view.setVisible(true);
            return view;
        } catch (JRException ex) {
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
