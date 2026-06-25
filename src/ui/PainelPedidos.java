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

        // ===== IMAGES =====
        JPanel painelImagens = new JPanel(new FlowLayout());

        try {

            ImageIcon cap = new ImageIcon("images/cappuccino.jpg");
            ImageIcon esp = new ImageIcon("images/espresso.jpg");
            ImageIcon cro = new ImageIcon("images/croissant.jpg");

            Image capImg = cap.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            Image espImg = esp.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            Image croImg = cro.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);

            painelImagens.add(new JLabel(new ImageIcon(capImg)));
            painelImagens.add(new JLabel(new ImageIcon(espImg)));
            painelImagens.add(new JLabel(new ImageIcon(croImg)));

        } catch (Exception e) {
            System.out.println("Impossible de charger les images.");
        }

        add(painelImagens, BorderLayout.SOUTH);

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

        areaPedido = new JTextArea();
        areaPedido.setEditable(false);

        add(new JScrollPane(areaPedido), BorderLayout.CENTER);

        btnAdicionar.addActionListener(e -> adicionarItem());
        btnFinalizar.addActionListener(e -> finalizarPedido());
    }

    private void adicionarItem() {

        try {

            String nome = (String) comboProdutos.getSelectedItem();

            int quantidade = Integer.parseInt(campoQuantidade.getText());

            Produto produto = estoque.buscarProduto(nome);

            if (produto == null) {
                return;
            }

            if (produto.getEstoque() < quantidade) {
                JOptionPane.showMessageDialog(this, "Estoque insuficiente!");
                return;
            }

            produto.reduzirEstoque(quantidade);

            ItemPedido item = new ItemPedido(produto, quantidade);

            pedidoAtual.adicionarItem(item);

            areaPedido.append(
                    item.toString()
                            + " | Stock restant: "
                            + produto.getEstoque()
                            + "\n"
            );

            campoQuantidade.setText("");

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Digite uma quantidade válida!"
            );
        }
    }

    private void finalizarPedido() {

        if (pedidoAtual.getItens().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Pedido vazio!"
            );

            return;
        }

        double total = pedidoAtual.getTotal();

        StringBuilder recibo = new StringBuilder();

        recibo.append("===== RECIBO =====\n\n");

        for (ItemPedido item : pedidoAtual.getItens()) {

            recibo.append(item.toString())
                    .append("\n");
        }

        recibo.append("\nTOTAL: ")
                .append(String.format("%.2f", total));

        JOptionPane.showMessageDialog(
                this,
                recibo.toString()
        );

        vendas.adicionarPedido(pedidoAtual);

        pedidoAtual = new Pedido();

        areaPedido.setText("");
    }
}
