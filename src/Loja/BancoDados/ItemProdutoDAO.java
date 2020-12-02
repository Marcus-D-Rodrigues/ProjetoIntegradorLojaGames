/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Loja.BancoDados;

import Loja.Registro.ItemProduto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nilso
 */
public class ItemProdutoDAO {
    public static void inserir(ItemProduto item) throws Exception {

        String sql = "INSERT INTO produto (nome_produto, nome_criador, plataforma, generos, descricao, quantidade_produto, "
                + "preco) VALUES (?, ?, ?, ?, ?, ?, ?);";

        Connection conexao = ConnectionUtils.getConnection();

        try {
            PreparedStatement comando = conexao.prepareStatement(sql);

            comando.setString(1, item.nome_produto);
            comando.setString(2, item.nome_criador);
            comando.setString(3, item.plataforma);
            comando.setString(4, item.generos);
            comando.setString(5, item.descricao);
            comando.setInt(6, item.quantidade_produto);
            comando.setFloat(7, item.preco);

            comando.execute();
        } finally {
            conexao.close();
        }
    }
    
    public static List<ItemProduto> listar() throws Exception {
        String sql = "SELECT * FROM produto;";

        Connection conexao = ConnectionUtils.getConnection();

        List<ItemProduto> lista = new ArrayList();

        try {
            PreparedStatement comando = conexao.prepareStatement(sql);

            ResultSet dados = comando.executeQuery();

            while (dados.next()) {
                ItemProduto item = new ItemProduto();

                item.id = dados.getInt("id");
                item.nome_produto = dados.getString("nome_produto");
                item.nome_criador = dados.getString("nome_criador");
                item.plataforma = dados.getString("plataforma");
                item.generos = dados.getString("generos");
                item.descricao = dados.getString("descricao");
                item.quantidade_produto = dados.getInt("quantidade_produto");
                item.preco = dados.getFloat("preco");

                lista.add(item);
            }

        } finally {
            conexao.close();
        }

        return lista;
    }
    
    public static void editar(ItemProduto item) throws Exception {

        String sql = "UPDATE produto SET nome_produto = ?, nome_criador = ?, plataforma = ?, "
                + " generos = ?, descricao = ?, quantidade_produto = ?, preco = ?"
                + " WHERE id = ?";

        Connection conexao = ConnectionUtils.getConnection();

        try {
            PreparedStatement comando = conexao.prepareStatement(sql);

            comando.setString(1, item.nome_produto);
            comando.setString(2, item.nome_criador);
            comando.setString(3, item.plataforma);
            comando.setString(4, item.generos);
            comando.setString(5, item.descricao);
            comando.setInt(6, item.quantidade_produto);
            comando.setFloat(7, item.preco);
            comando.setInt(8, item.id);

            comando.execute();
        } finally {
            conexao.close();
        }
    }
    
    public static void excluir(int id) throws Exception {

        String sql = "DELETE FROM produto WHERE id = ?";

        Connection conexao = ConnectionUtils.getConnection();

        try {
            PreparedStatement comando = conexao.prepareStatement(sql);

            comando.setInt(1, id);
           

            comando.execute();
        } finally {
            conexao.close();
        }
    }
    
    public static List<ItemProduto> pesquisa(String nome_produto) throws Exception {
        String sql = "SELECT * FROM produto WHERE nome_produto LIKE ?";

        Connection conexao = ConnectionUtils.getConnection();

        List<ItemProduto> lista = new ArrayList();

        try {
            PreparedStatement comando = conexao.prepareStatement(sql);
            
            comando.setString(1, "%" + nome_produto + "%");

            ResultSet dados = comando.executeQuery();

            while (dados.next()) {
                ItemProduto item = new ItemProduto();

                item.id = dados.getInt("id");
                item.nome_produto = dados.getString("nome_produto");
                item.nome_criador = dados.getString("nome_criador");
                item.plataforma = dados.getString("plataforma");
                item.generos = dados.getString("generos");
                item.descricao = dados.getString("descricao");
                item.quantidade_produto = dados.getInt("quantidade_produto");
                item.preco = dados.getFloat("preco");
                
                lista.add(item);

            }

        } finally {
            conexao.close();
        }

        return lista;
    }
}
