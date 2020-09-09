/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import Clases.Control;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Pajas
 */
public class Code {

    public static void main(String[] args) {
        Connection con = Control.con;
        String sql = "select nombre from facultad";
        System.out.println(Control.returnData(sql));
    }
}
