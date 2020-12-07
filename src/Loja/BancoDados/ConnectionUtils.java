/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Loja.BancoDados;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Dell i5
 */
public class ConnectionUtils {
  
    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/lojagame"
                    + "?useTimezone=true&serverTimezone=UTC",
                "root",
                "12345");
    }

}
