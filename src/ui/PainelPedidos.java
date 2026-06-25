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

        setPreferredSize(new Dimension(450, 500));

        setLayout(new BorderLayout(5, 5));

        // ===== TOP =====
        JPanel topo = new JPanel();

        comboProdutos = new JComboBox<>();

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

        // ===== TEXT AREA =====
        areaPedido = new JTextArea();
        areaPedido.setEditable(false);

        add(new JScrollPane(areaPedido), BorderLayout.CENTER);

        // ===== IMAGES (FIXED) =====
        JPanel imgPanel = new JPanel(new FlowLayout());

        imgPanel.add(createImage("images/expresso.jpg"));
        imgPanel.add(createImage("images/cappuccino.jpg"));
        imgPanel.add(createImage("images/croissant.jpg"));

        add(imgPanel, BorderLayout.SOUTH);

        // ===== ACTIONS =====
        btnAdicionar.addActionListener(e -> adicionarItem());
        btnFinalizar.addActionListener(e -> finalizarPedido());
    }

    // ===== IMAGE LOADER SAFE =====
    private JLabel createImage(String path) {

        ImageIcon icon = new ImageIcon(path);

        Image img = icon.getImage()
                .getScaledInstance(120, 120, Image.SCALE_SMOOTH);

        return new JLabel(new ImageIcon(img));
    }

    // ===== ADD ITEM =====
    private void adicionarItem() {

        String nome = (String) comboProdutos.getSelectedItem();
        int quantidade;

        try {
            quantidade = Integer.parseInt(campoQuantidade.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Quantidade inválida!");
            return;
        }

        Produto produto = estoque.buscarProduto(nome);

        if (produto == null) return;

        if (produto.getEstoque() < quantidade) {
            JOptionPane.showMessageDialog(this, "Estoque insuficiente!");
            return;
        }

        produto.reduzirEstoque(quantidade);

        ItemPedido item = new ItemPedido(produto, quantidade);

        pedidoAtual.adicionarItem(item);

        areaPedido.append(
                item.toString()
                        + " | Stock restante: "
                        + produto.getEstoque()
                        + "\n"
        );

        campoQuantidade.setText("");
    }

    // ===== FINALIZE =====
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
