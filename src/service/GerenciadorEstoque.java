package service;

import model.Produto;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorEstoque {

    private List<Produto> produtos = new ArrayList<>();

    public void adicionarProduto(Produto p) {
        produtos.add(p);
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public Produto buscarProduto(String nome) {
        for (Produto p : produtos) {
            if (p.getNome().equals(nome)) {
                return p;
            }
        }
        return null;
    }
}
