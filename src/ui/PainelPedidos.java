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

    private PainelEstoque painelEstoque;

    private JComboBox<String> comboProdutos;
    private JTextField campoQuantidade;
    private JTextArea areaPedido;

    public PainelPedidos(GerenciadorEstoque estoque, PainelEstoque painelEstoque) {

        this.estoque = estoque;
        this.painelEstoque = painelEstoque;

        this.vendas = new GerenciadorVendas();
        this.pedidoAtual = new Pedido();

        setLayout(new BorderLayout(10, 10));

        // TOP
        JPanel topo = new JPanel();

        comboProdutos = new JComboBox<>();

        for (Produto p : estoque.getProdutos()) {
            comboProdutos.addItem(p.getNome());
        }

        campoQuantidade = new JTextField(5);

        JButton btnAdd = new JButton("Adicionar");
        JButton btnFinal = new JButton("Finalizar");

        topo.add(comboProdutos);
        topo.add(campoQuantidade);
        topo.add(btnAdd);
        topo.add(btnFinal);

        add(topo, BorderLayout.NORTH);

        // CENTER
        areaPedido = new JTextArea();
        areaPedido.setEditable(false);

        add(new JScrollPane(areaPedido), BorderLayout.CENTER);

        // IMAGES (FIXED)
        JPanel imgPanel = new JPanel(new FlowLayout());

        imgPanel.add(createImage("expresso.jpg"));
        imgPanel.add(createImage("cappuccino.jpg"));
        imgPanel.add(createImage("croissant.jpg"));

        add(imgPanel, BorderLayout.SOUTH);

        // ACTIONS
        btnAdd.addActionListener(e -> adicionar());
        btnFinal.addActionListener(e -> finalizar());
    }

    private JLabel createImage(String fileName) {

        java.net.URL imgURL =
                getClass().getClassLoader().getResource("images/" + fileName);

        if (imgURL == null) {
            return new JLabel("[no image]");
        }

        ImageIcon icon = new ImageIcon(imgURL);

        Image img = icon.getImage()
                .getScaledInstance(120, 120, Image.SCALE_SMOOTH);

        return new JLabel(new ImageIcon(img));
    }

    private void adicionar() {

        String nome = (String) comboProdutos.getSelectedItem();

        int qtd;

        try {
            qtd = Integer.parseInt(campoQuantidade.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Quantidade invalida");
            return;
        }

        Produto p = estoque.buscarProduto(nome);

        if (p == null) return;

        if (p.getEstoque() < qtd) {
            JOptionPane.showMessageDialog(this, "Stock insuficiente");
            return;
        }

        p.reduzirEstoque(qtd);

        pedidoAtual.adicionarItem(new ItemPedido(p, qtd));

        areaPedido.append(
                p.getNome()
                        + " x" + qtd
                        + " | Stock: " + p.getEstoque()
                        + "\n"
        );

        // UPDATE LEFT PANEL (IMPORTANT)
        painelEstoque.atualizarLista();

        campoQuantidade.setText("");
    }

    private void finalizar() {

        if (pedidoAtual.getItens().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pedido vazio");
            return;
        }

        double total = pedidoAtual.getTotal();

        StringBuilder r = new StringBuilder();
        r.append("===== RECIBO =====\n\n");

        for (ItemPedido i : pedidoAtual.getItens()) {
            r.append(i.toString()).append("\n");
        }

        r.append("\nTOTAL: ").append(total);

        JOptionPane.showMessageDialog(this, r.toString());

        vendas.adicionarPedido(pedidoAtual);

        pedidoAtual = new Pedido();
        areaPedido.setText("");
    }
}
