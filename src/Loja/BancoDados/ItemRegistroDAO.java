/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Loja.BancoDados;

import Loja.BancoDados.ConnectionUtils;
import Loja.Registro.ItemRegistro;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dell i5
 */
public class ItemRegistroDAO {

    public static List<ItemRegistro> pesquisar(String cpf, Date dataInicial, Date dataFinal) throws Exception {
        String sql = "SELECT c.nome, p.nome_produto, i.qtd_produto, i.valor_total\n"
                + "FROM cliente c\n"
                + "JOIN venda v ON c.id = v.id_cliente \n"
                + "JOIN item_venda i ON v.id = i.id_venda\n"
                + "JOIN produto p ON i.id_produto = p.id\n"
                + "WHERE c.cpf = ? \n"
                + "AND v.data_venda >= ?\n"
                + "AND v.data_venda <= ?";
        
         Connection conexao = ConnectionUtils.getConnection();

        List<ItemRegistro> lista = new ArrayList();

        try {
            PreparedStatement comando = conexao.prepareStatement(sql);

            comando.setString(1, cpf );
            comando.setDate(2, dataInicial);
            comando.setDate(3, dataFinal);
            ResultSet dados = comando.executeQuery();

            while (dados.next()) {
                ItemRegistro item = new ItemRegistro();

                item.cliente = dados.getString("nome");
                item.produtos = dados.getString("nome_produto");
                item.quantidade= dados.getString("qtd_produto");
                item.valor = dados.getDouble("valor_total");
            
             lista.add(item);

            }

        } finally {
            conexao.close();
        }

            return lista;
       
    }
}