/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Loja.BancoDados;

import Loja.Registro.ItemVenda;
import Loja.Registro.Venda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;


public class ItemVendaDAO {
    public static void finalizarCompra(Venda venda, List<ItemVenda> listaDeVenda) throws Exception {

        String sqlVenda = "INSERT INTO venda(id_cliente, data_venda) VALUES( ?, ?);";

        Connection conexao = ConnectionUtils.getConnection();
        

        try {
            PreparedStatement comando = conexao.prepareStatement(sqlVenda, Statement.RETURN_GENERATED_KEYS);
            
            comando.setInt(1, venda.id_cliente);
            comando.setDate(2, venda.data_venda);

            comando.executeUpdate();
            
            ResultSet result = comando.getGeneratedKeys();
               
            if(result.next()){
                int id_venda = result.getInt(1);
                finaliza(id_venda, listaDeVenda);
            }
            
        } finally {
            conexao.close();
        }
    }
    
    public static void finaliza(int id_venda, List<ItemVenda> listaDeVenda) throws Exception {

        String sql = "INSERT INTO item_venda(id_produto, id_venda, qtd_produto, valor_total) VALUES(?,?,?,?);";

        Connection conexao = ConnectionUtils.getConnection();

        try {
            PreparedStatement comando = conexao.prepareStatement(sql);
                
            for(ItemVenda item : listaDeVenda){
                comando.setInt(1, item.getId());
                comando.setInt(2, id_venda);
                comando.setInt(3, item.getQuantidade_produto());
                comando.setFloat(4, item.getPreco());
                comando.execute();
            }
            
        } finally {
            conexao.close();
        }
    }
    

}
