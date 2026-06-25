package ui;

import service.GerenciadorEstoque;

import javax.swing.*;
import java.awt.*;

public class TelaPrincipal extends JFrame {

    private GerenciadorEstoque estoque;

    public TelaPrincipal(GerenciadorEstoque estoque) {

        this.estoque = estoque;

        setTitle("Java Café");
        setSize(900, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(1, 2));

        add(new PainelEstoque(estoque));
        add(new PainelPedidos(estoque));
    }
}
