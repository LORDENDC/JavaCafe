package ui;

import javax.swing.*;
import java.awt.*;

// Tela principal da aplicação
public class TelaPrincipal extends JFrame {

    public TelaPrincipal() {

        setTitle("Java Café");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // layout simples
        setLayout(new BorderLayout());

        // título
        JLabel titulo = new JLabel("Java Café - Sistema POS", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));

        add(titulo, BorderLayout.NORTH);

        // painel central
        JPanel painel = new JPanel();
        painel.add(new JLabel("Sistema em construção..."));

        add(painel, BorderLayout.CENTER);
    }
}
