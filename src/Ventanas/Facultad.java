/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import Clases.Control;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Pajas
 */
public class Facultad extends javax.swing.JFrame {

    /**
     * Creates new form Facultad
     */
    Connection con = Control.con;
    Statement st;
    PreparedStatement ps;
    ResultSet rs;
    DefaultTableModel md;

    public Facultad() {
        initComponents();
        init();
        addCombo();
    }

    public void init() {
        setTitle("Facultades y escuelas");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        try {
            st = con.createStatement();
        } catch (SQLException ex) {
            System.err.print(ex);
        }
        md = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        md.setColumnIdentifiers(new String[]{"Facultad"});
        tbEscuela.setModel(md);
    }

    public void addCombo() {
        Control.fillCombo(comboFacultad, "select nombre from facultad");
        comboFacultad.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selectedFacultad = e.getItem().toString();
                    fillTable(selectedFacultad);
                }
            }
        });
    }

    public void fillTable(String selectedFacultad) {
        String sqlidFacultad = "select id from facultad where nombre='" + selectedFacultad + "'";
        String idFacultad = Control.returnData(sqlidFacultad);
        String sql = "select nombre from escuela where facultad_id=" + idFacultad;
        Control.fillTable(md, sql, 1);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
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
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Title 1"
            }
        ));
        jScrollPane1.setViewportView(tbEscuela);

        jLabel1.setText("Seleccione la facultad:");

        btnCrearFacultad.setText("Crear Facultad");
        btnCrearFacultad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearFacultadActionPerformed(evt);
            }
        });

        btnCrearEscuela.setText("Crear Escuela");
        btnCrearEscuela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearEscuelaActionPerformed(evt);
            }
        });

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
        // TODO add your handling code here:
        /*Pedir un input al usuario con el nombre de la facultad, el cual no debe de ser vacio ni solo con espacios
        , después añadir la facultad y si ya existe avisale al usuario, de modo de que no exista
        se tiene que rellenar el combo de nuevo*/
        String nuevaFacultad = JOptionPane.showInputDialog("Ingrese el nombre de la nueva facultad");
        nuevaFacultad = nuevaFacultad.trim();
        if (!nuevaFacultad.equals("")) {
            try {
                String sql = "insert into facultad(nombre) value('" + nuevaFacultad + "')";
                if (st.executeUpdate(sql) != 0) {
                    JOptionPane.showMessageDialog(null, "Ingreso exitoso");
                    comboFacultad.addItem(nuevaFacultad);
                }
            } catch (SQLException ex) {
                System.err.println(ex);
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
            newEscuela = newEscuela.trim();
            if (newEscuela.length() != 0) {
                try {
                    String sqlInsert = "insert into escuela(nombre,facultad_id) values(?,?)";
                    String nombreFacultad = comboFacultad.getSelectedItem().toString();
                    String sqlidFacultad = "select id from facultad where nombre='" + nombreFacultad + "'";
                    int id_facultad = Integer.parseInt(Control.returnData(sqlidFacultad));
                    ps = con.prepareStatement(sqlInsert);
                    ps.setString(1, newEscuela);
                    ps.setInt(2, id_facultad);
                    if (ps.executeUpdate() != 0) {
                        JOptionPane.showMessageDialog(null, "Ingreso Exitoso");
                        fillTable(nombreFacultad);
                    } else {
                        JOptionPane.showMessageDialog(null, "Ingreso fallido");
                    }
                } catch (SQLException ex) {
                    System.err.println(ex);
                    JOptionPane.showMessageDialog(null, "Esa escuela ya existe");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese un nombre valido");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Debe de seleccionar una facultad");
        }
    }//GEN-LAST:event_btnCrearEscuelaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Facultad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Facultad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Facultad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Facultad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Facultad().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCrearEscuela;
    private javax.swing.JButton btnCrearFacultad;
    private javax.swing.JComboBox<String> comboFacultad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbEscuela;
    // End of variables declaration//GEN-END:variables
}