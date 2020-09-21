/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import clases.Control;
import clases.Validar;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.util.Objects;

/**
 *
 * @author Pajas
 */
@SuppressWarnings({"RedundantSuppression", "FieldCanBeLocal"})
public class Escuela extends javax.swing.JFrame {

    /**
     * Creates new form Facultad
     */
    DefaultTableModel md;
    String modo = "";
    int celda = -1;

    public Escuela() {
        initComponents();
        init();
        addTable();
        addComboFacultad();
    }

    public void init() {
        setLocationRelativeTo(null);
        setTitle("Datos de la escuela");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        txBuscar.setEnabled(false);
        btnCancelar.setEnabled(false);
    }

    public void addTable() {
        md = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (row == celda) {
                    return true;
                }
                return false;
            }
        };
        md.setColumnIdentifiers(new String[]{"Escuelas"});
        tbEscuelas.setModel(md);
        tbEscuelas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public void addComboFacultad() {
        String sqlCombo = "select nombre from facultad";
        Control.fillCombo(comboFacultad, sqlCombo);
        comboFacultad.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String selectedFacultad = e.getItem().toString();
                String sqlTable = String.format("select e.nombre from escuela e inner join facultad f on"
                        + " f.id=e.facultad_id where f.nombre = '%s'", selectedFacultad);
                System.out.println(sqlTable);
                Control.fillTable(md, sqlTable, 1);
                txBuscar.setEnabled(true);
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        comboFacultad = new javax.swing.JComboBox<>();
        btnCrear = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txBuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbEscuelas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Datos de la escuela:");

        jLabel3.setText("Facultad:");

        comboFacultad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboFacultadActionPerformed(evt);
            }
        });

        btnCrear.setText("Crear");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        jLabel4.setText("Buscar:");

        txBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txBuscarActionPerformed(evt);
            }
        });
        txBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txBuscarKeyTyped(evt);
            }
        });

        tbEscuelas.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbEscuelas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(232, 232, 232)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(comboFacultad, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(27, 27, 27)
                                .addComponent(txBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(btnCrear)
                                .addGap(18, 18, 18)
                                .addComponent(btnEditar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnCancelar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnEliminar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSalir)))))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(comboFacultad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCrear)
                    .addComponent(btnEditar)
                    .addComponent(btnCancelar)
                    .addComponent(btnEliminar)
                    .addComponent(btnSalir))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboFacultadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboFacultadActionPerformed
        // TODO Añadir esta accion
    }//GEN-LAST:event_comboFacultadActionPerformed

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        if (comboFacultad.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Primero seleccione una facultad");
        } else {
            /*Verificar si el txEscuela no est� vac�o ni formado por espacios*/
            String escuela = JOptionPane.showInputDialog("Ingrese la escuela");
            if (escuela != null) {
                escuela = escuela.trim();
                if (escuela.length() == 0) {
                    JOptionPane.showMessageDialog(null, "Rellene la escuela correctamente");
                } else {
                    String sqlCheck = String.format("select * from escuela where nombre='%s'", escuela);
                    boolean check = Control.checkQuery(sqlCheck);
                    System.out.println(sqlCheck + "  " + check);
                    if (check) {
                        JOptionPane.showMessageDialog(null, "La escuela ya existe en est� u otra facultad");
                    } else {
                        String facultad = comboFacultad.getSelectedItem().toString();
                        String sqlCrear = String.format("call registrar_escuela('%s','%s')", escuela, facultad);
                        Control.update(sqlCrear);
                        String sqlFacultad = String.format("select e.nombre from escuela e inner join facultad f on e.facultad_id=f.id "
                                + "where f.nombre='%s'", facultad);
                        Control.fillTable(md, sqlFacultad, 1);
                    }
                }
            }
        }
    }//GEN-LAST:event_btnCrearActionPerformed

    private void txBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txBuscarActionPerformed
        // TODO Añadir esta accion
    }//GEN-LAST:event_txBuscarActionPerformed

    private void txBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txBuscarKeyTyped
        if (!(evt.getKeyChar() >= 65 && evt.getKeyChar() <= 122 || evt.getKeyChar() == 32 || evt.getKeyChar() == KeyEvent.VK_BACK_SPACE)) {
            evt.consume();
        } else {
            int pos = txBuscar.getCaretPosition();
            System.out.println(pos);
            String nombre = (txBuscar.getText().substring(0, pos) + evt.getKeyChar() + txBuscar.getText().substring(pos)).trim();
            String facultad = Objects.requireNonNull(comboFacultad.getSelectedItem()).toString();
            String sqlFacultad = "select e.nombre from escuela e inner join facultad f on e.facultad_id=f.id "
                    + "where f.nombre='" + facultad + "' and e.nombre like '" + nombre + "%';";
            Control.fillTable(md, sqlFacultad, 1);
        }
    }//GEN-LAST:event_txBuscarKeyTyped

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:
        /*Validar si una facultad est� seleccionada*/
        String oldEscuela = "";
        if (btnEditar.getText().equals("Editar")) {
            if (comboFacultad.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(null, "Primero seleccione una facultad");
                /*Validar si una escuela de la tabla est� seleccionada*/
            } else if (tbEscuelas.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Primero seleccione una escuela");
            } else {
                /*Modo edicion*/
                btnCrear.setEnabled(false);
                btnCancelar.setEnabled(true);
                btnEliminar.setEnabled(false);
                btnEditar.setText("Aceptar");
                celda = tbEscuelas.getSelectedRow();
                tbEscuelas.editCellAt(celda, 0);
            }
        } else if (btnEditar.getText().equals("Aceptar")) {
            oldEscuela = tbEscuelas.getValueAt(celda, 0).toString();
            tbEscuelas.editCellAt(-1, 0);
            String escuela = tbEscuelas.getValueAt(celda, 0).toString().trim();
            /*Verificar si la escuela no es vacia*/
            if (escuela.length() == 0) {
                JOptionPane.showMessageDialog(null, "Valor no valido");
                /*Verificar si la escuela ya existe*/
            } else {
                String sqlgetId = String.format("select getIdEscuela('%s')", escuela);
                String idEscuela = Control.returnData(sqlgetId);
                if (!idEscuela.equals("0")) {
                    JOptionPane.showMessageDialog(null, "Esa escuela ya existe");
                    tbEscuelas.editCellAt(celda, 0);
                } else {
                    System.out.println("oldescuela:" + oldEscuela);
                    sqlgetId = String.format("select getIdEscuela('%s')", oldEscuela);
                    idEscuela = Control.returnData(sqlgetId);
                    String sqlUpdateEscuela = String.format("update escuela set nombre='%s' where id = '%s'", escuela, idEscuela);
                    Control.update(sqlUpdateEscuela);
                    btnCrear.setEnabled(true);
                    btnCancelar.setEnabled(false);
                    btnEliminar.setEnabled(true);
                    btnEditar.setText("Editar");
                    celda = -1;
                }
            }
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        Object oldEscuela = tbEscuelas.getValueAt(celda, 0);
        tbEscuelas.editCellAt(-1, 0);
        tbEscuelas.setValueAt(oldEscuela, celda, 0);
        btnCrear.setEnabled(true);
        btnCancelar.setEnabled(false);
        btnEliminar.setEnabled(true);
        btnEditar.setText("Editar");
        celda = -1;
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        //Verificar si una facultad esta seleccionada
        if (comboFacultad.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "La facultad no est� seleccionada");
        } else if (tbEscuelas.getSelectionModel().isSelectionEmpty()) {
            //Verificar si una escuela esta seleccionada
            JOptionPane.showMessageDialog(null, "Selecciona una escuela");
        } else {
            //Obtener el nombre de la escuela seleccionada
            String nombreEscuela = tbEscuelas.getValueAt(tbEscuelas.getSelectedRow(), 0).toString();
            //Preguntar si se quiere eliminar dicha escuela
            int answer = JOptionPane.showConfirmDialog(null, String.format("�Seguro que desea eliminar la escuela %s?", nombreEscuela));
            //SI: Se elimina
            if (answer == 0) {
                //Verifica si la escuela tiene alumnos
                String sqlescuelahasAlumnos = String.format("select * from escuela inner join estudiante"
                        + " on escuela.id = estudiante.escuela_id"
                        + " where escuela.nombre = '%s'", nombreEscuela);
                System.out.println(sqlescuelahasAlumnos);
                if (Control.checkQuery(sqlescuelahasAlumnos)) {
                    //SI: Muestra un mensaje que la escuela tiene alumnos
                    JOptionPane.showMessageDialog(null, "La escuela tiene estudiantes, primero elimine a los estudiantes");
                } else {
                    //NO: Se elimina la escuela
                    String sqlDeleteEscuela = String.format("delete from escuela where nombre = '%s'", nombreEscuela);
                    int rowsAffected = Control.update(sqlDeleteEscuela);
                    if (rowsAffected == 1) {
                        String facultad = comboFacultad.getSelectedItem().toString();
                        String sqlFacultad = String.format("select e.nombre from escuela e inner join facultad f on e.facultad_id=f.id "
                                + "where f.nombre='%s'", facultad);
                        Control.fillTable(md, sqlFacultad, 1);
                        JOptionPane.showMessageDialog(null, "Eliminacion exitosa");
                    } else if (rowsAffected == 0) {
                        JOptionPane.showMessageDialog(null, "Eliminacion fallida!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Fatal Error!");
                    }
                }
            }
            //NO, CANCELAR Y CERRAR: No se modifica nada
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        if (JOptionPane.showConfirmDialog(btnSalir, "¿desea abandonar programa?") == 0) {
            this.dispose();
        }
    }//GEN-LAST:event_btnSalirActionPerformed

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
            java.util.logging.Logger.getLogger(Escuela.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Escuela.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Escuela.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Escuela.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Escuela().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox<String> comboFacultad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbEscuelas;
    private javax.swing.JTextField txBuscar;
    // End of variables declaration//GEN-END:variables
}
