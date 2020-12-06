/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Loja.Registro;

import java.sql.Date;

/**
 *
 * @author nilso
 */
public class ItemCompra {
    public int id;
    public int id_cliente;
    public Date data_venda;
    
    public int id_produto;
    public int id_venda;
    public int qtd_produto;
    public float valor_total;
}
