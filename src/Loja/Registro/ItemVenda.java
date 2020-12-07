package Loja.Registro;


public class ItemVenda {
    public int id;
    public String nome_produto;
    public String nome_criador;
    public String plataforma;
    public String generos;
    public String descricao;
    public int quantidade_produto;
    public float preco;
    public float valor_total;

    public String getNome_produto() {
        return nome_produto;
    }

    public int getQuantidade_produto() {
        return quantidade_produto;
    }

    public float getPreco() {
        return preco;
    }

    public float getValor_total() {
        return valor_total;
    }

    public int getId() {
        return id;
    }

    public void setNome_produto(String nome_produto) {
        this.nome_produto = nome_produto;
    }

    public void setQuantidade_produto(int quantidade_produto) {
        this.quantidade_produto = quantidade_produto;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public void setValor_total(float valor_total) {
        this.valor_total = valor_total;
    }

    public void setId(int id) {
        this.id = id;
    }

}
