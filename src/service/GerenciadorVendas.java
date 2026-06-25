package service;

import model.Pedido;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorVendas {

    private List<Pedido> pedidos;

    public GerenciadorVendas() {
        this.pedidos = new ArrayList<>();
    }

    public void adicionarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public double getTotalVendas() {

        double total = 0;

        for (Pedido p : pedidos) {
            total += p.getTotal();
        }

        return total;
    }
}
