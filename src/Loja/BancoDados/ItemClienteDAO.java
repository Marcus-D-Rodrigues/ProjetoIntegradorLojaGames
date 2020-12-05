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

        String sql = "INSERT INTO cliente(nome, sobrenome, rg, cpf, cep, cidade, bairro, complemento, "
                + "endereco, numero, telefone, celular, email, genero, estado_civil, data_nascimento) "
                + "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

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
            comando.setString(14, item.genero.toString());
            comando.setString(15, item.estado_civil.toString());
            comando.setDate(16, item.data_nascimento);

            comando.execute();
        } finally {
            conexao.close();
        }
    }

    public static List<ItemCliente> listar() throws Exception {
        String sql = "SELECT * FROM cliente";

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
                item.data_nascimento = dados.getDate("data_nascimento");
                
                String genero = dados.getString("genero");

                if (genero == null || "".equals(genero)) {
                    item.genero = null;
                } else {
                    item.genero = dados.getString("genero").charAt(0);
                }

                String estadoCivil = dados.getString("estado_civil");

                if (estadoCivil == null || "".equals(estadoCivil )) {
                    item.estado_civil = null;
                } else {
                    item.estado_civil = dados.getString("estado_civil").charAt(0);
                }

                lista.add(item);

            }

        } finally {
            conexao.close();
        }

        return lista;
    }

    public static void editar(ItemCliente item) throws Exception {

        String sql = "UPDATE cliente SET nome = ?,sobrenome = ?, rg = ?, cpf = ?, cep = ?, cidade = ?, "
                + "bairro = ?, complemento = ?, endereco = ?, numero = ?, telefone = ?, celular = ?, email = ?,"
                + "genero = ?, estado_civil = ?, data_nascimento = ?"
                + " WHERE id = ?";

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
            comando.setString(14, item.genero.toString());
            comando.setString(15, item.estado_civil.toString());
            comando.setDate(16, item.data_nascimento);
            comando.setInt(17, item.id);

            comando.execute();
        } finally {
            conexao.close();
        }
    }

    public static void excluir(int id) throws Exception {

        String sql = "DELETE FROM cliente WHERE id = ?";

        Connection conexao = ConnectionUtils.getConnection();

        try {
            PreparedStatement comando = conexao.prepareStatement(sql);

            comando.setInt(1, id);

            comando.execute();
        } finally {
            conexao.close();
        }
    }

    public static List<ItemCliente> pesquisar(String cpf) throws Exception {
        String sql = "SELECT * FROM cliente WHERE cpf LIKE ?";

        Connection conexao = ConnectionUtils.getConnection();

        List<ItemCliente> lista = new ArrayList();

        try {
            PreparedStatement comando = conexao.prepareStatement(sql);

            comando.setString(1, "%" + cpf + "%");

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
                item.data_nascimento = dados.getDate("data_nascimento");
                
                String genero = dados.getString("genero");

                if (genero == null || "".equals(genero)) {
                    item.genero = null;
                } else {
                    item.genero = dados.getString("genero").charAt(0);
                }

                String estadoCivil = dados.getString("estado_civil");

                if (estadoCivil == null || "".equals(estadoCivil )) {
                    item.estado_civil = null;
                } else {
                    item.estado_civil = dados.getString("estado_civil").charAt(0);
                }

                lista.add(item);

            }

        } finally {
            conexao.close();
        }

        return lista;
    }
    
    public static List<ItemCliente> Validar(String cpf) throws Exception {
        String sql = "SELECT * FROM cliente WHERE cpf LIKE ?";

        Connection conexao = ConnectionUtils.getConnection();

        List<ItemCliente> lista = new ArrayList();

        try {
            PreparedStatement comando = conexao.prepareStatement(sql);

            comando.setString(1, "%" + cpf + "%");

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
                item.data_nascimento = dados.getDate("data_nascimento");
                
                String genero = dados.getString("genero");

                if (genero == null || "".equals(genero)) {
                    item.genero = null;
                } else {
                    item.genero = dados.getString("genero").charAt(0);
                }

                String estadoCivil = dados.getString("estado_civil");

                if (estadoCivil == null || "".equals(estadoCivil )) {
                    item.estado_civil = null;
                } else {
                    item.estado_civil = dados.getString("estado_civil").charAt(0);
                }

                lista.add(item);

            }

        } finally {
            conexao.close();
        }

        return lista;
    }

}
