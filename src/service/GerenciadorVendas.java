package service;

import model.Pedido;
import model.ItemPedido;
import java.util.ArrayList;
import java.util.List;

// Gerencia as vendas realizadas
public class GerenciadorVendas {

    private List<Pedido> pedidos;

    // construtor
    public GerenciadorVendas() {
        this.pedidos = new ArrayList<>();
    }

    // adiciona um pedido finalizado
    public void adicionarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    // retorna todos os pedidos
    public List<Pedido> getPedidos() {
        return pedidos;
    }

    // calcula o total de vendas
    public double getTotalVendas() {
        double total = 0;

        for (Pedido p : pedidos) {
            total += p.getTotal();
        }

        return total;
    }

    // conta quantos pedidos foram feitos
    public int getNumeroPedidos() {
        return pedidos.size();
    }
}
