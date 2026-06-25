package model;

// Representa um produto da cafeteria
public class Produto {

    private String nome;   // nome do produto
    private double preco;  // preço do produto
    private int estoque;   // quantidade disponível em estoque

    // construtor
    public Produto(String nome, double preco, int estoque) {
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
    }

    // retorna o nome do produto
    public String getNome() {
        return nome;
    }

    // retorna o preço do produto
    public double getPreco() {
        return preco;
    }

    // retorna o estoque atual
    public int getEstoque() {
        return estoque;
    }

    // altera o estoque
    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    // reduz o estoque após uma venda
    public void reduzirEstoque(int quantidade) {
        this.estoque -= quantidade;
    }

    // representa o produto em texto
    @Override
    public String toString() {
        return nome + " - " + preco + " - estoque: " + estoque;
    }
}
