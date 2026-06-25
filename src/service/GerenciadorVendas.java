package service;

import model.Pedido;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorVendas {

    private List<Pedido> pedidos = new ArrayList<>();

    public void adicionarPedido(Pedido p) {
        pedidos.add(p);
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }
}
