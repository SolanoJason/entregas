/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Pajas
 */
public class Validar {
    /**
     * El método isFieldEmpty recibe como argumento un array de JTextField y
     * valida si todos están vacíos, de ser así retorna true y muestra un
     * mensaje con todos campos que faltan rellenar. Es necesario usar el metodo
     * setName para nombrar a los JTextField
     *
     * @author Solano Jason
     * @version 1.0
     * @since 2020-09-08
     */
    public static boolean isFieldEmpty(JTextField... field) {
        boolean retornar = false;
        String mensaje = "";
        for (int i = 0; i <= field.length - 1; i++) {
            if (field[i].getText().trim().length() == 0) {
                retornar = true;
                if (mensaje.equals("")) {
                    mensaje += field[i].getName();
                } else {
                    mensaje += ", " + field[i].getName();
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
            public void keyTyped(KeyEvent e) {
                if (!(e.getKeyChar() >= 65 && e.getKeyChar() <= 122 || e.getKeyChar() == 32)) {
                    e.consume();
                }
            }
        });
    }
}
