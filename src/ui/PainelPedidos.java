package ui;

import model.ItemPedido;
import model.Pedido;
import model.Produto;
import service.GerenciadorEstoque;

import javax.swing.*;
import java.awt.*;

// Painel para criar pedidos (vendas)
public class PainelPedidos extends JPanel {

    private GerenciadorEstoque estoque;
    private Pedido pedidoAtual;

    private JComboBox<String> comboProdutos;
    private JTextField campoQuantidade;
    private JTextArea areaPedido;

    public PainelPedidos(GerenciadorEstoque estoque) {

        this.estoque = estoque;
        this.pedidoAtual = new Pedido();

        setLayout(new BorderLayout());

        // topo
        JPanel topo = new JPanel();

        comboProdutos = new JComboBox<>();

        for (Produto p : estoque.getProdutos()) {
            comboProdutos.addItem(p.getNome());
        }

        campoQuantidade = new JTextField(5);

        JButton btnAdicionar = new JButton("Adicionar");

        topo.add(comboProdutos);
        topo.add(campoQuantidade);
        topo.add(btnAdicionar);

        add(topo, BorderLayout.NORTH);

        // centro
        areaPedido = new JTextArea();
        areaPedido.setEditable(false);

        add(new JScrollPane(areaPedido), BorderLayout.CENTER);

        // ação botão
        btnAdicionar.addActionListener(e -> adicionarItem());
    }

    private void adicionarItem() {

        String nome = (String) comboProdutos.getSelectedItem();
        int quantidade = Integer.parseInt(campoQuantidade.getText());

        Produto produto = estoque.buscarProduto(nome);

        if (produto != null) {

            ItemPedido item = new ItemPedido(produto, quantidade);
            pedidoAtual.adicionarItem(item);

            areaPedido.append(item.toString() + "\n");
        }

        campoQuantidade.setText("");
    }

    public Pedido getPedidoAtual() {
        return pedidoAtual;
    }
}
