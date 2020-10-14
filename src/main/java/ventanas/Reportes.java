/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import clases.CheckboxListCellRenderer;
import clases.Control;
import clases.Report;
import java.awt.event.ItemEvent;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Pajas
 */
public class Reportes extends javax.swing.JFrame {

    /**
     * Creates new form Reportes
     */
    private final JFrame padre;
    private DefaultListModel modelList = new DefaultListModel();
    String selectedFacultad = "";
    String paramEscuela = "";

    public Reportes(JFrame padre) {
        this.padre = padre;
        initComponents();
        init();
        addListEscuela();
    }

    public void init() {
        setLocationRelativeTo(null);
        setTitle("Reporte de alumnos vulnerables");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        rbtnTodos.setSelected(true);
    }

    public void addListEscuela() {
        listEscuelas.setCellRenderer(new CheckboxListCellRenderer());
        listEscuelas.setModel(modelList);
        String sql = "select nombre from escuela";
        Control.fillList(modelList, sql);
    }

    public static String convertListToParameter(List escuelas) {
        String joined = String.join("','", escuelas);
        joined = "'" + joined + "'";
        return joined;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jButton1 = new javax.swing.JButton();
        rbtnFaltan = new javax.swing.JRadioButton();
        rbtnChip = new javax.swing.JRadioButton();
        rbtnLaptop = new javax.swing.JRadioButton();
        rbtnRecibio = new javax.swing.JRadioButton();
        rbtnTodos = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listEscuelas = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Generar reporte");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbtnFaltan);
        rbtnFaltan.setText("Alumnos que Falta chip y laptop");
        rbtnFaltan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnFaltanActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbtnChip);
        rbtnChip.setText("Alumnos que Falta chip");
        rbtnChip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnChipActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbtnLaptop);
        rbtnLaptop.setText("Alumnos que Falta laptop");
        rbtnLaptop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnLaptopActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbtnRecibio);
        rbtnRecibio.setText("Alumnos que Recibieron chip y laptop");
        rbtnRecibio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnRecibioActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbtnTodos);
        rbtnTodos.setText("Sin condicion, mostrar todos");
        rbtnTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnTodosActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(listEscuelas);

        jLabel2.setText("Seleccione las escuelas a mostrar:");

        jLabel4.setText("Seleccione multiples escuelas con Ctrl y Shift");

        jLabel5.setText("Seleccione la condicion de los alumnos a mostrar:");

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(101, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rbtnRecibio)
                                    .addComponent(rbtnLaptop)
                                    .addComponent(rbtnChip)
                                    .addComponent(rbtnFaltan)
                                    .addComponent(rbtnTodos)))
                            .addComponent(jLabel5))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(299, 299, 299)
                .addComponent(jButton1)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap(38, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addComponent(jSeparator2)))
                        .addGap(8, 8, 8))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbtnTodos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbtnFaltan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbtnChip)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbtnLaptop)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbtnRecibio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        //Validar que una escuela este seleccionada
        if (listEscuelas.isSelectionEmpty()) {
            JOptionPane.showMessageDialog(null, "Seleccione al menos una escuela");
        } else {
            //Obtener la lista de las escuelas seleccionadas
            //Unir las escuelas por medio de comas para enviarlas como parametro
            String escuelasParameter = convertListToParameter(listEscuelas.getSelectedValuesList());
            HashMap parameters = new HashMap();
            if (rbtnTodos.isSelected()) {
                parameters.put("SUBREPORT_DIR", "AlumnList.jasper");
            } else if (rbtnFaltan.isSelected()) {
                parameters.put("SUBREPORT_DIR", "AlumnListFaltanAmbos.jasper");
            } else if (rbtnChip.isSelected()) {
                parameters.put("SUBREPORT_DIR", "AlumnListChip.jasper");
            } else if (rbtnLaptop.isSelected()) {
                parameters.put("SUBREPORT_DIR", "AlumnListLaptop.jasper");
            } else if (rbtnRecibio.isSelected()) {
                parameters.put("SUBREPORT_DIR", "AlumnListRecibioAmbos.jasper");
            }
            parameters.put("escuelas", escuelasParameter);
            Report.showReport("src\\main\\java\\Reportes\\MainReport.jasper", parameters).setTitle("Reporte de las escuelas: "+escuelasParameter);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void rbtnLaptopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnLaptopActionPerformed
    }//GEN-LAST:event_rbtnLaptopActionPerformed

    private void rbtnRecibioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnRecibioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbtnRecibioActionPerformed

    private void rbtnChipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnChipActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbtnChipActionPerformed

    private void rbtnTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnTodosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbtnTodosActionPerformed

    private void rbtnFaltanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnFaltanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbtnFaltanActionPerformed

    /**
     * @param args the command line arguments
     */
    @Override
    public void dispose() {
        padre.setExtendedState(NORMAL);
        padre.setVisible(true);
        super.dispose();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JList<String> listEscuelas;
    private javax.swing.JRadioButton rbtnChip;
    private javax.swing.JRadioButton rbtnFaltan;
    private javax.swing.JRadioButton rbtnLaptop;
    private javax.swing.JRadioButton rbtnRecibio;
    private javax.swing.JRadioButton rbtnTodos;
    // End of variables declaration//GEN-END:variables
}
