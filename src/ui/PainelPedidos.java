package ui;

import model.ItemPedido;
import model.Pedido;
import model.Produto;
import service.GerenciadorEstoque;
import service.GerenciadorVendas;

import javax.swing.*;
import java.awt.*;

public class PainelPedidos extends JPanel {

    private GerenciadorEstoque estoque;
    private GerenciadorVendas vendas;

    private Pedido pedidoAtual;

    private JComboBox<String> comboProdutos;
    private JTextField campoQuantidade;
    private JTextArea areaPedido;

    public PainelPedidos(GerenciadorEstoque estoque) {

        this.estoque = estoque;
        this.vendas = new GerenciadorVendas();
        this.pedidoAtual = new Pedido();

        setLayout(new BorderLayout());

        JPanel topo = new JPanel();

        comboProdutos = new JComboBox<>();

        // SAFE LOAD (évite null crash)
        if (estoque != null && estoque.getProdutos() != null) {
            for (Produto p : estoque.getProdutos()) {
                comboProdutos.addItem(p.getNome());
            }
        }

        campoQuantidade = new JTextField(5);

        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnFinalizar = new JButton("Finalizar Pedido");

        topo.add(comboProdutos);
        topo.add(campoQuantidade);
        topo.add(btnAdicionar);
        topo.add(btnFinalizar);

        add(topo, BorderLayout.NORTH);

        areaPedido = new JTextArea();
        areaPedido.setEditable(false);

        add(new JScrollPane(areaPedido), BorderLayout.CENTER);

        btnAdicionar.addActionListener(e -> adicionarItem());
        btnFinalizar.addActionListener(e -> finalizarPedido());
    }

    private void adicionarItem() {

        String nome = (String) comboProdutos.getSelectedItem();
        int quantidade = Integer.parseInt(campoQuantidade.getText());

        Produto produto = estoque.buscarProduto(nome);

        if (produto == null) return;

        if (produto.getEstoque() < quantidade) {
            JOptionPane.showMessageDialog(this, "Estoque insuficiente!");
            return;
        }

        produto.reduzirEstoque(quantidade);

        ItemPedido item = new ItemPedido(produto, quantidade);
        pedidoAtual.adicionarItem(item);

        areaPedido.append(item.toString() + "\n");

        campoQuantidade.setText("");
    }

    private void finalizarPedido() {

        if (pedidoAtual.getItens().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pedido vazio!");
            return;
        }

        double total = pedidoAtual.getTotal();

        StringBuilder recibo = new StringBuilder();
        recibo.append("===== RECIBO =====\n\n");

        for (ItemPedido item : pedidoAtual.getItens()) {
            recibo.append(item.toString()).append("\n");
        }

        recibo.append("\nTOTAL: ").append(total);

        JOptionPane.showMessageDialog(this, recibo.toString());

        vendas.adicionarPedido(pedidoAtual);

        pedidoAtual = new Pedido();
        areaPedido.setText("");
    }
}
