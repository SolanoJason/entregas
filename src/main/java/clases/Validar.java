/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author Pajas
 */
public enum Validar {
    ;

	/**
	 * El método isFieldEmpty recibe como argumento un array de JTextField y
	 * valida si todos están vacíos, de ser así retorna true y muestra un
	 * mensaje con todos campos que faltan rellenar. Es necesario usar el metodo
	 * setName para nombrar a los JTextField
	 *
	 * @author Solano Jason
	 * @since 2020-09-08
	 */
	public static boolean isFieldEmpty(JTextField... field) {
        boolean retornar = false;
        StringBuilder mensaje = new StringBuilder();
        for (int i = 0; i <= field.length - 1; i++) {
            if (field[i].getText().trim().length() == 0) {
                retornar = true;
                if (mensaje.length() == 0) {
                    mensaje.append(field[i].getName());
                } else {
                    mensaje.append(", ").append(field[i].getName());
                }
            }
        }
        if (retornar) {
            JOptionPane.showMessageDialog(null, mensaje + " faltan rellenar");
        }
        return retornar;
    }

    /**
     * Restringe a un JTextField a que solo se pueda ingresar letras
     */
    public static void textField(JTextField field) {
        field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (!(e.getKeyChar() >= 65 && e.getKeyChar() <= 122 || e.getKeyChar() == 32)) {
                    e.consume();
                }
            }
        });
    }
}
