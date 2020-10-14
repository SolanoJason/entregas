package ventanas;


import clases.Control;
import clases.Validar;

import javax.swing.*;

/**
 * @author LENOVO
 */
@SuppressWarnings({"FieldCanBeLocal", "RedundantSuppression"})
public final class Facultad extends javax.swing.JFrame {

	private final JFrame padre;

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton btnCancelar;
	private javax.swing.JButton btnCrear1;
	private javax.swing.JButton btnEditar;
	private javax.swing.JButton btnEliminar;
	private javax.swing.JButton btnSalir;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JLabel labelInfo;
	private javax.swing.JTable tbFacultad;
	private javax.swing.JTextField txBuscar;
	private javax.swing.JTextField txFacultad;
	// End of variables declaration//GEN-END:variables


	/**
	 * Creates new form Facultad
	 */
	public Facultad(JFrame padre) {
		this.padre = padre;
		initComponents();
		setLocationRelativeTo(null);
		Validar.textField(txFacultad);
		setTitle("Datos de facultad");
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		llenarTablita();
	}

	private void llenarTablita() {
		if (txBuscar.getText().trim().length() != 0) {
			String sql = String.format("SELECT * FROM facultad WHERE nombre LIKE'%%%s%%'", txBuscar.getText().trim());
			Control.fillTable2(tbFacultad, sql);
			labelInfo.setText("mostrando las coincidencias con: " + txBuscar.getText().trim());
		} else {
			String sql = "SELECT * FROM facultad";
			Control.fillTable2(tbFacultad, sql);
			labelInfo.setText("mostrando todos los registros");
		}
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {
		jPanel2      = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		tbFacultad   = new javax.swing.JTable();
		jLabel3      = new javax.swing.JLabel();
		txBuscar     = new javax.swing.JTextField();
		labelInfo    = new javax.swing.JLabel();
		jPanel4      = new javax.swing.JPanel();
		jLabel2      = new javax.swing.JLabel();
		txFacultad   = new javax.swing.JTextField();
		btnCrear1    = new javax.swing.JButton();
		btnEditar    = new javax.swing.JButton();
		btnCancelar  = new javax.swing.JButton();
		btnEliminar  = new javax.swing.JButton();
		btnSalir     = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("busqueda con palabras claves"));
		jPanel2.setEnabled(false);

		tbFacultad.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][]{
						{null, null, null, null},
						{null, null, null, null},
						{null, null, null, null},
						{null, null, null, null}
				},
				new String[]{
						"Title 1", "Title 2", "Title 3", "Title 4"
				}
		));
		tbFacultad.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mousePressed(java.awt.event.MouseEvent evt) {
				tbFacultadMousePressed();
			}
		});
		tbFacultad.addPropertyChangeListener(this::tbFacultadPropertyChange);
		jScrollPane1.setViewportView(tbFacultad);

		jLabel3.setText("Buscar:");

		txBuscar.addActionListener(this::txBuscarActionPerformed);
		txBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyReleased(java.awt.event.KeyEvent evt) {
				txBuscarKeyReleased();
			}
		});

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(
				jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanel2Layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
										.addGroup(jPanel2Layout.createSequentialGroup()
												.addComponent(jLabel3)
												.addGap(18, 18, 18)
												.addComponent(txBuscar))
										.addComponent(labelInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addContainerGap())
		);
		jPanel2Layout.setVerticalGroup(
				jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanel2Layout.createSequentialGroup()
								.addGap(30, 30, 30)
								.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jLabel3)
										.addComponent(txBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(23, 23, 23)
								.addComponent(labelInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
								.addContainerGap())
		);

		jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("datos de la facultad"));

		jLabel2.setText("Facultad:");

		txFacultad.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyReleased(java.awt.event.KeyEvent evt) {
				txFacultadKeyReleased();
			}
		});

		btnCrear1.setText("Crear");
		btnCrear1.addActionListener(this::btnCrear1ActionPerformed);

		btnEditar.setText("Editar");
		btnEditar.setEnabled(false);
		btnEditar.addActionListener(this::btnEditarActionPerformed);

		btnCancelar.setText("Cancelar");
		btnCancelar.setEnabled(false);
		btnCancelar.addActionListener(this::btnCancelarActionPerformed);

		btnEliminar.setText("Eliminar");
		btnEliminar.setEnabled(false);
		btnEliminar.addActionListener(this::btnEliminarActionPerformed);

		btnSalir.setText("Salir");
		btnSalir.addActionListener(this::btnSalirActionPerformed);

		javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
		jPanel4.setLayout(jPanel4Layout);
		jPanel4Layout.setHorizontalGroup(
				jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
								.addContainerGap(16, Short.MAX_VALUE)
								.addComponent(jLabel2)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addGroup(jPanel4Layout.createSequentialGroup()
												.addComponent(btnCrear1)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(btnEditar)
												.addGap(27, 27, 27)
												.addComponent(btnCancelar)
												.addGap(33, 33, 33)
												.addComponent(btnEliminar)
												.addGap(34, 34, 34)
												.addComponent(btnSalir))
										.addComponent(txFacultad, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(14, 14, 14))
		);
		jPanel4Layout.setVerticalGroup(
				jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jLabel2)
										.addComponent(txFacultad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(btnCrear1)
										.addComponent(btnEditar)
										.addComponent(btnCancelar)
										.addComponent(btnEliminar)
										.addComponent(btnSalir))
								.addContainerGap())
		);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
								.addGap(0, 20, Short.MAX_VALUE)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup()
												.addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addContainerGap())
										.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
												.addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(24, 24, 24))))
		);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addGap(24, 24, 24)
								.addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(29, 29, 29)
								.addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addContainerGap())
		);

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void btnCrear1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrear1ActionPerformed
		String facu = txFacultad.getText().trim();
		if (facu.length() != 0) {
			String sqCheck = String.format("SELECT id FROM facultad WHERE nombre='%s'", facu);
			if (!Control.checkQuery(sqCheck)) {
				String sql = String.format(" CALL registrar_facultad('%s')", facu);
				Control.update(sql);
				JOptionPane.showMessageDialog(btnCrear1, "se ingresó correctamente a la base de datos");
				llenarTablita();
			} else {
				JOptionPane.showMessageDialog(btnCrear1, "la facultad ya se encuentra registrada\nintente de nuevo", "alerta", JOptionPane.WARNING_MESSAGE);

			}

		} else {
			JOptionPane.showMessageDialog(btnCrear1, "rellene el campo facultad por favor", "alerta", JOptionPane.WARNING_MESSAGE);
		}


	}//GEN-LAST:event_btnCrear1ActionPerformed

	private void tbFacultadMousePressed() {//GEN-FIRST:event_tbFacultadMousePressed
		txFacultad.setText(tbFacultad.getValueAt(tbFacultad.getSelectedRow(), 1).toString());
		habilitarEdicion();

	}//GEN-LAST:event_tbFacultadMousePressed

	private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
		if (tbFacultad.getSelectedRowCount() == 1) {
			if (txFacultad.getText().trim().length() != 0) {
				String sqlCheck = String.format("SELECT id FROM facultad WHERE  nombre='%s'", txFacultad.getText().trim());
				if (!Control.checkQuery(sqlCheck)) {
					if (JOptionPane.showConfirmDialog(btnEditar, "desea actualizar la facultad " + tbFacultad.getValueAt(tbFacultad.getSelectedRow(), 1) + " por :\n " + txFacultad.getText().trim()) == 0) {
						String sql = String.format(" CALL editar_facultad('%s','%s')", txFacultad.getText().trim(), tbFacultad.getValueAt(tbFacultad.getSelectedRow(), 1).toString());
						Control.update(sql);
						JOptionPane.showMessageDialog(btnEditar, "se  actualizo correctamente");
						llenarTablita();
					}

				} else {
					JOptionPane.showMessageDialog(btnEditar, "la facultad ya se encuentra registrada\nintente de nuevo", "alerta", JOptionPane.WARNING_MESSAGE);

				}

			} else {
				JOptionPane.showMessageDialog(btnEditar, "rellene el campo facultad por favor", "alerta", JOptionPane.WARNING_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(btnEditar, "seleccione la fila que quiere actualizar", "alerta", JOptionPane.WARNING_MESSAGE);
		}
	}//GEN-LAST:event_btnEditarActionPerformed

	private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
		if (tbFacultad.getSelectedRowCount() != 0) {
			if (JOptionPane.showConfirmDialog(btnEditar, "desea eliminar la facultad :\n" + tbFacultad.getValueAt(tbFacultad.getSelectedRow(), 1)) == 0) {

				String sqlCheck = String.format("SELECT  escuela.id FROM escuela INNER JOIN facultad ON facultad.id =escuela.facultad_id WHERE facultad.id=%s", tbFacultad.getValueAt(tbFacultad.getSelectedRow(), 0).toString());
				if (!Control.checkQuery(sqlCheck)) {
					String sql = String.format("DELETE FROM facultad WHERE id =%s", tbFacultad.getValueAt(tbFacultad.getSelectedRow(), 0).toString());
					Control.update(sql);
					JOptionPane.showMessageDialog(btnEliminar, "se elimino correctamente");
					llenarTablita();
				} else {
					JOptionPane.showMessageDialog(btnEliminar, "la facultad que desea eliminar tiene referencias con las escuelas,\nprimero elimine sus escuelas para eliminar esta facultad ",
							"alerta", JOptionPane.WARNING_MESSAGE);
				}

			}

		} else {
			JOptionPane.showMessageDialog(btnEliminar, "seleccione la fila que quiere eliminar", "alerta", JOptionPane.WARNING_MESSAGE);
		}
	}//GEN-LAST:event_btnEliminarActionPerformed

	private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
		if (JOptionPane.showConfirmDialog(btnSalir, "¿desea abandonar programa?") == 0) {
			this.dispose();
		}


	}//GEN-LAST:event_btnSalirActionPerformed

	private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
		tbFacultad.getSelectionModel().removeSelectionInterval(0, tbFacultad.getRowCount());
		txFacultad.setText("");

		habilitarEdicion();


	}//GEN-LAST:event_btnCancelarActionPerformed

	private void txFacultadKeyReleased() {//GEN-FIRST:event_txFacultadKeyReleased
		// TODO add your handling code here:
	}//GEN-LAST:event_txFacultadKeyReleased

	private void txBuscarKeyReleased() {//GEN-FIRST:event_txBuscarKeyReleased
		llenarTablita();
	}//GEN-LAST:event_txBuscarKeyReleased

	private void tbFacultadPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tbFacultadPropertyChange
		habilitarEdicion();
	}//GEN-LAST:event_tbFacultadPropertyChange

	private void habilitarEdicion() {
		if (tbFacultad.getSelectedRowCount() != 0) {
			btnEditar.setEnabled(true);
			btnCancelar.setEnabled(true);
			btnEliminar.setEnabled(true);
		} else {
			btnEditar.setEnabled(false);
			btnCancelar.setEnabled(false);
			btnEliminar.setEnabled(false);
		}
	}

	private void txBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txBuscarActionPerformed
		// TODO add your handling code here:
	}//GEN-LAST:event_txBuscarActionPerformed

	@Override
	public void dispose() {
		padre.setExtendedState(NORMAL);
                padre.setVisible(true);
		super.dispose();
	}
}
