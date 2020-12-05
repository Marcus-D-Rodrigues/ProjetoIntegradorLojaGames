/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Loja.Registro;

import java.sql.Date;



/**
 *
 * @author Dell i5
 */
public class ItemCliente {

    public int id;
    public String nome;
    public String sobrenome;
    public String rg;
    public String cpf;
    public String cep;
    public String cidade;
    public String bairro;
    public String complemento;
    public String endereco;
    public String numero;
    public String telefone;
    public String celular;
    public String email;
    public Character genero;
    public Character estado_civil;
    public Date data_nascimento;

    public String getNome() {
        return nome;
    }


    public String getCpf() {
        return cpf;
    }

}
