package ui;

import service.GerenciadorEstoque;

import javax.swing.*;
import java.awt.*;

public class TelaPrincipal extends JFrame {

    private GerenciadorEstoque estoque;

    private PainelEstoque painelEstoque;
    private PainelPedidos painelPedidos;

    public TelaPrincipal(GerenciadorEstoque estoque) {

        this.estoque = estoque;

        setTitle("Java Café");
        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(1, 2, 10, 10));

        painelEstoque = new PainelEstoque(estoque);
        painelPedidos = new PainelPedidos(estoque, painelEstoque);

        add(painelEstoque);
        add(painelPedidos);
    }
}
