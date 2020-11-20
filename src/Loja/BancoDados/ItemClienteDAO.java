/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Loja.BancoDados;


import Loja.Registro.ItemCliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dell i5
 */
public class ItemClienteDAO {

    public static void inserir(ItemCliente item) throws Exception {

        String sql = "INSERT INTO item_ClienteLoja (nome,sobrenome, rg, cpf, cep, ciadade, "
                + "bairro, complemento, endereco, numero, telefone, celular, email\n"
                + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        Connection conexao = ConnectionUtils.getConnection();

        try {
            PreparedStatement comando = conexao.prepareStatement(sql);

            comando.setString(1, item.nome);
            comando.setString(2, item.sobrenome);
            comando.setString(3, item.rg);
            comando.setString(4, item.cpf);
            comando.setString(5, item.cep);
            comando.setString(6, item.cidade);
            comando.setString(7, item.bairro);
            comando.setString(8, item.complemento);
            comando.setString(9, item.endereco);
            comando.setString(10, item.numero);
            comando.setString(11, item.telefone);
            comando.setString(12, item.celular);
            comando.setString(13, item.email);

            comando.execute();
        } finally {
            conexao.close();
        }
    }

    public static List<ItemCliente> listar() throws Exception {
        String sql = "SELECT * FROM item_Cliente";

        Connection conexao = ConnectionUtils.getConnection();

        List<ItemCliente> lista = new ArrayList();

        try {
            PreparedStatement comando = conexao.prepareStatement(sql);

            ResultSet dados = comando.executeQuery();

            while (dados.next()) {
                ItemCliente item = new ItemCliente();

                item.id = dados.getInt("id");
                item.nome = dados.getString("nome");
                item.sobrenome = dados.getString("sobrenome");
                item.rg = dados.getString("rg");
                item.cpf = dados.getString("cpf");
                item.cep = dados.getString("cep");
                item.cidade = dados.getString("cidade");
                item.bairro = dados.getString("bairro");
                item.complemento = dados.getString("complemento");
                item.endereco = dados.getString("endereco");
                item.numero = dados.getString("numero");
                item.telefone = dados.getString("telefone");
                item.celular = dados.getString("celular");
                item.email = dados.getString("email");

                lista.add(item);

            }

        } finally {
            conexao.close();
        }

        return lista;
    }

}
