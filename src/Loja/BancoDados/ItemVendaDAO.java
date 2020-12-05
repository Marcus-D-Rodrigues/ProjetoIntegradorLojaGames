/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Loja.BancoDados;

import Loja.Registro.ItemVenda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nilso
 */
public class ItemVendaDAO {
    public static void pedidoDeVenda(ItemVenda item) throws Exception {

        String sql = "INSERT INTO venda(id_cliente, data_venda) VALUES( ?, ?);";

        Connection conexao = ConnectionUtils.getConnection();

        try {
            PreparedStatement comando = conexao.prepareStatement(sql);

            comando.setInt(1, item.id_cliente);
            comando.setDate(2, item.data_venda);

            comando.execute();
        } finally {
            conexao.close();
        }
    }
    

}
