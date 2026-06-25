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

            JLabel img = createImage(fileName);

            JLabel text = new JLabel(
                    p.getNome()
                            + " | €" + p.getPreco()
                            + " | Stock: " + p.getEstoque()
            );

            item.add(img);
            item.add(text);

            panel.add(item);
        }

        panel.revalidate();
        panel.repaint();
    }

    // 🔥 CLASSLOADER FIX (PROPRE + PRO)
    private JLabel createImage(String fileName) {

        java.net.URL url =
                getClass().getClassLoader().getResource("images/" + fileName);

        if (url == null) {
            return new JLabel("[no image]");
        }

        ImageIcon icon = new ImageIcon(url);

        Image img = icon.getImage()
                .getScaledInstance(150, 100, Image.SCALE_SMOOTH);

        return new JLabel(new ImageIcon(img));
    }
}
