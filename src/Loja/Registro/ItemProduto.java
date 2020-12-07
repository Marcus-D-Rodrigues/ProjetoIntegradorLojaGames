package Loja.Registro;

public class ItemProduto {

    public int id;
    public String nome_produto;
    public String nome_criador;
    public String plataforma;
    public String generos;
    public String descricao;
    public int quantidade_produto;
    public float preco;

    public String getNome_produto() {
        return nome_produto;
    }

    public int getQuantidade_produto() {
        return quantidade_produto;
    }
    
    public float getPreco() {
        return preco;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public int getId() {
        return id;
    }
}
