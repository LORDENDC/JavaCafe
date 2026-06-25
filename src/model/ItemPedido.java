package model;

// Representa um item dentro de um pedido
public class ItemPedido {

    private Produto produto; // produto escolhido
    private int quantidade;  // quantidade comprada

    // construtor
    public ItemPedido(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    // retorna o produto
    public Produto getProduto() {
        return produto;
    }

    // retorna a quantidade
    public int getQuantidade() {
        return quantidade;
    }

    // calcula o total deste item (preço x quantidade)
    public double getTotal() {
        return produto.getPreco() * quantidade;
    }

    @Override
    public String toString() {
        return produto.getNome() + " x" + quantidade + " = " + getTotal();
    }
}
