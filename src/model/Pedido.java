package model;

import java.util.ArrayList;
import java.util.List;

// Representa um pedido feito por um cliente
public class Pedido {

    private List<ItemPedido> itens; // lista de itens do pedido

    // construtor
    public Pedido() {
        this.itens = new ArrayList<>();
    }

    // adiciona um item ao pedido
    public void adicionarItem(ItemPedido item) {
        itens.add(item);
    }

    // retorna a lista de itens
    public List<ItemPedido> getItens() {
        return itens;
    }

    // calcula o total do pedido
    public double getTotal() {
        double total = 0;

        for (ItemPedido item : itens) {
            total += item.getTotal();
        }

        return total;
    }

    @Override
    public String toString() {
        return "Pedido - Total: " + getTotal();
    }
}
