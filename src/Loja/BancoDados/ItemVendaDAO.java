/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Loja.BancoDados;

import Loja.Registro.ItemCompra;
import java.sql.Connection;
import java.sql.PreparedStatement;


public class ItemVendaDAO {
    public static void pedidoDeVenda(ItemCompra item) throws Exception {

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
    
    public static void finaliza(ItemCompra item2) throws Exception {

        String sql = "INSERT INTO item_venda(id_produto, id_venda, qtd_produto, valor_total) VALUES(?,?,?,?);";

        Connection conexao = ConnectionUtils.getConnection();

        try {
            PreparedStatement comando = conexao.prepareStatement(sql);

            comando.setInt(1, item2.id_produto);
            comando.setInt(2, item2.id_venda);
            comando.setInt(2, item2.qtd_produto);
            comando.setFloat(2, item2.valor_total);

            comando.execute();
        } finally {
            conexao.close();
        }
    }
    

}
