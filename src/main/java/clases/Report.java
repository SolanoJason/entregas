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

import java.sql.Connection;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.design.JasperDesign;

/**
 * @author Pajas
 */
public enum Report {
    ;

	private static final Connection con = Control.connection;

    /**
     * Muestra un Reporte estatico Además devuelve un objeto JasperViewer para
     * poder configurar como se va a mostrar el reporte al gusto
     */
    public static JasperViewer showReport(String path) {
        try {   
            JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, con);
            if (jasperPrint.getPages().size() == 0) {
                JOptionPane.showMessageDialog(null, "El documento no tiene paginas");
            } else {
                JasperViewer view = new JasperViewer(jasperPrint, false);
                view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                view.setVisible(true);
                view.setExtendedState(6);
                return view;
            }
        } catch (JRException ex) {
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Muestra un Reporte con 1 parametro Además devuelve un objeto
     * JasperViewer para poder configurar como se va a mostrar el reporte al
     * gusto
     */
    public static JasperViewer showReport(String path, String parameter, String value) {
        try {
            JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
            HashMap parameters = new HashMap<>(1);
            parameters.put(parameter, value);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parameters, con);
            if (jasperPrint.getPages().size() == 0) {
                JOptionPane.showMessageDialog(null, "El documento no tiene paginas");
            } else {
                JasperViewer view = new JasperViewer(jasperPrint, false);
                view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                view.setVisible(true);
                view.setExtendedState(6);
                return view;
            }
        } catch (JRException ex) {
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
        try {
            JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parameters, con);
            if (jasperPrint.getPages().size() == 0) {
                JOptionPane.showMessageDialog(null, "El documento no tiene paginas");
            } else {
                JasperViewer view = new JasperViewer(jasperPrint, false);
                view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                view.setVisible(true);
                view.setExtendedState(6);
                return view;
            }
        } catch (JRException ex) {
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
