import model.Produto;
import service.GerenciadorEstoque;
import ui.TelaPrincipal;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        GerenciadorEstoque estoque = new GerenciadorEstoque();

        estoque.adicionarProduto(new Produto("Cafe Expresso", 3.5, 50));
        estoque.adicionarProduto(new Produto("Cappuccino", 5.0, 40));
        estoque.adicionarProduto(new Produto("Croissant", 4.5, 30));

        SwingUtilities.invokeLater(() -> {
            TelaPrincipal tela = new TelaPrincipal(estoque);
            tela.setVisible(true);
        });
    }
}
