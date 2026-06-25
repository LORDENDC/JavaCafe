package ui;

import model.Produto;
import service.GerenciadorEstoque;

import javax.swing.*;
import java.awt.*;

public class PainelEstoque extends JPanel {

    private GerenciadorEstoque estoque;
    private JPanel panel;

    public PainelEstoque(GerenciadorEstoque estoque) {

        this.estoque = estoque;

        setLayout(new BorderLayout());

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        add(new JScrollPane(panel), BorderLayout.CENTER);

        atualizarLista();
    }

    public void atualizarLista() {

        panel.removeAll();

        for (Produto p : estoque.getProdutos()) {

            JPanel item = new JPanel(new FlowLayout(FlowLayout.LEFT));

            String fileName = p.getNome()
                    .toLowerCase()
                    .replace(" ", "")
                    + ".jpg";

            ImageIcon icon = new ImageIcon("images/" + fileName);

            Image img = icon.getImage()
                    .getScaledInstance(60, 60, Image.SCALE_SMOOTH);

            JLabel imgLabel = new JLabel(new ImageIcon(img));

            JLabel text = new JLabel(
                    p.getNome()
                            + " | €" + p.getPreco()
                            + " | Stock: " + p.getEstoque()
            );

            item.add(imgLabel);
            item.add(text);

            panel.add(item);
        }

        panel.revalidate();
        panel.repaint();
    }
}
