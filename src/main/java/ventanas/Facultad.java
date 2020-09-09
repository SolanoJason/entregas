/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import clases.Control;
import com.formdev.flatlaf.intellijthemes.FlatCyanLightIJTheme;
import com.google.common.flogger.FluentLogger;
import com.google.common.flogger.StackSize;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ItemEvent;
import java.sql.*;
import java.util.Objects;

/**
 * @author Pajas
 */
public class Facultad extends JFrame {
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    /**
     * Creates new form Facultad
     */
    Connection connection = Control.connection;
    Statement statement;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    DefaultTableModel tableModel;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCrearEscuela;
    private javax.swing.JButton btnCrearFacultad;
    private javax.swing.JComboBox<String> comboFacultad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbEscuela;

    public Facultad() {
        initComponents();
        init();
        addCombo();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FlatCyanLightIJTheme.install();
        System.setProperty("flogger.backend_factory", "com.google.common.flogger.backend.log4j2.Log4j2BackendFactory#getInstance");
        java.awt.EventQueue.invokeLater(() -> new Facultad().setVisible(true));
    }

    public void init() {
        logger.atInfo().log("Inicializando la creación de la GUI");
        setTitle("Facultades y escuelas");

        // Esto centra el cuadro
        setLocationRelativeTo(null);

        // Sin esto solo se oculta el JFrame
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        try {
            statement = connection.createStatement();
        } catch (SQLException ex) {
            logger.atSevere().withStackTrace(StackSize.FULL).withCause(ex).log("No se pudo crear el statement");
        }

        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableModel.setColumnIdentifiers(new String[]{"Facultad"});
        tbEscuela.setModel(tableModel);
    }

    public void addCombo() {

        logger.atInfo().log("Añadiendo el ComboBox de las facultades");
        Control.fillCombo(comboFacultad, "select f.nombre from facultad f");

        comboFacultad.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String selectedFacultad = e.getItem().toString();
                fillTable(selectedFacultad);
            }
        });
    }

    public void fillTable(String selectedFacultad) {
        logger.atInfo().log("Empezando a llenar la tabla con %s", selectedFacultad);
        logger.atInfo().log("Obteniendo el Id de la facultad");

        String sqlIdFacultad = String.format("select f.id from facultad f where f.nombre='%s'", selectedFacultad);
        String idFacultad = Control.returnData(sqlIdFacultad);

        logger.atInfo().log("El Id de la facultad es %s", idFacultad);

        String sql = String.format("select e.nombre from escuela e where e.facultad_fk=%s", idFacultad);
        Control.fillTable(tableModel, sql, 1);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        comboFacultad = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbEscuela = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnCrearFacultad = new javax.swing.JButton();
        btnCrearEscuela = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tbEscuela.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {null},
                        {null},
                        {null},
                        {null}
                },
                new String[]{
                        "Title 1"
                }
        ));
        jScrollPane1.setViewportView(tbEscuela);

        jLabel1.setText("Seleccione la facultad:");

        btnCrearFacultad.setText("Crear Facultad");
        btnCrearFacultad.addActionListener(this::btnCrearFacultadActionPerformed);

        btnCrearEscuela.setText("Crear Escuela");
        btnCrearEscuela.addActionListener(this::btnCrearEscuelaActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addGap(18, 18, 18)
                                                .addComponent(comboFacultad, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(btnCrearFacultad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnCrearEscuela, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnCrearFacultad)
                                                .addGap(32, 32, 32)
                                                .addComponent(btnCrearEscuela))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(comboFacultad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel1))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCrearFacultadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearFacultadActionPerformed
        /*Pedir un input al usuario con el nombre de la facultad, el cual no debe de ser vacio ni solo con espacios
        , después añadir la facultad y si ya existe avisale al usuario, de modo de que no exista
        se tiene que rellenar el combo de nuevo*/
        String nuevaFacultad = JOptionPane.showInputDialog("Ingrese el nombre de la nueva facultad");
        nuevaFacultad = nuevaFacultad.trim();
        if (!nuevaFacultad.equals("")) {
            try {
                String sql = "insert into facultad(nombre) value('" + nuevaFacultad + "')";
                if (statement.executeUpdate(sql) != 0) {
                    JOptionPane.showMessageDialog(null, "Ingreso exitoso");
                    comboFacultad.addItem(nuevaFacultad);
                }
            } catch (SQLException ex) {
                logger.atWarning().withStackTrace(StackSize.FULL).withCause(ex).log("Intentando ingresar facultad existente %", nuevaFacultad);
                JOptionPane.showMessageDialog(null, "Esa facultad ya existe");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese un nombre válido");
        }
    }//GEN-LAST:event_btnCrearFacultadActionPerformed

    private void btnCrearEscuelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearEscuelaActionPerformed
        /*Comprobar si una facultad está seleccionada, entonces pedir un input al usuario
        , comprobar si dicho input no es vacío ni solo con espacios.
        , guardar la escuela*/
        if (comboFacultad.getSelectedIndex() != -1) {
            String newEscuela = JOptionPane.showInputDialog("Ingrese el nombre de la escuela");
            // Limpiando los espacios incensarios (al final y al inicio)
            newEscuela = newEscuela.trim();
            if (newEscuela.length() != 0) {
                try {
                    String sqlInsert = "insert into escuela(nombre,escuela.facultad_fk) values(?,?)";
                    String nombreFacultad = Objects.requireNonNull(comboFacultad.getSelectedItem()).toString();
                    String sqlidFacultad = String.format("select id from facultad where nombre='%s'", nombreFacultad);
                    int idFacultad = Integer.parseInt(Control.returnData(sqlidFacultad));
                    preparedStatement = connection.prepareStatement(sqlInsert);
                    preparedStatement.setString(1, newEscuela);
                    preparedStatement.setInt(2, idFacultad);
                    if (preparedStatement.executeUpdate() != 0) {
                        JOptionPane.showMessageDialog(null, "Ingreso Exitoso");
                        fillTable(nombreFacultad);
                    } else {
                        JOptionPane.showMessageDialog(null, "Ingreso fallido");
                    }
                } catch (SQLException ex) {
                    logger.atWarning().withStackTrace(StackSize.FULL).withCause(ex).log("Intentando ingresar escuela existente");
                    JOptionPane.showMessageDialog(null, "Esa escuela ya existe");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese un nombre valido");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe de seleccionar una facultad");
        }
    }//GEN-LAST:event_btnCrearEscuelaActionPerformed
    // End of variables declaration//GEN-END:variables
}
