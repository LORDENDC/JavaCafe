package ui;

import model.Produto;
import service.GerenciadorEstoque;

import javax.swing.*;
import java.awt.*;

// Painel responsável por mostrar e gerenciar o estoque
public class PainelEstoque extends JPanel {

    private GerenciadorEstoque estoque;
    private JTextArea areaTexto;

    public PainelEstoque(GerenciadorEstoque estoque) {

        this.estoque = estoque;

        setLayout(new BorderLayout());

        // área de texto para mostrar produtos
        areaTexto = new JTextArea();
        areaTexto.setEditable(false);

        add(new JScrollPane(areaTexto), BorderLayout.CENTER);

        atualizarLista();
    }

    // atualiza a lista de produtos na tela
    public void atualizarLista() {

        areaTexto.setText("");

        for (Produto p : estoque.getProdutos()) {
            areaTexto.append(p.toString() + "\n");
        }
    }
}
