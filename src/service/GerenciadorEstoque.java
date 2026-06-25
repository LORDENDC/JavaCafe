package service;

import model.Produto;
import exception.OutOfStockException;
import java.util.ArrayList;
import java.util.List;

// Gerencia o estoque dos produtos
public class GerenciadorEstoque {

    private List<Produto> produtos;

    // construtor
    public GerenciadorEstoque() {
        this.produtos = new ArrayList<>();
    }

    // adiciona um produto ao estoque
    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    // retorna a lista de produtos
    public List<Produto> getProdutos() {
        return produtos;
    }

    // busca um produto pelo nome
    public Produto buscarProduto(String nome) {
        for (Produto p : produtos) {
            if (p.getNome().equalsIgnoreCase(nome)) {
                return p;
            }
        }
        return null;
    }

    // reduz estoque de um produto
    public void venderProduto(String nome, int quantidade) throws OutOfStockException {

        Produto produto = buscarProduto(nome);

        if (produto == null) {
            return;
        }

        if (produto.getEstoque() < quantidade) {
            throw new OutOfStockException("Estoque insuficiente para " + nome);
        }

        produto.reduzirEstoque(quantidade);
    }
}
