import model.Produto;
import service.GerenciadorEstoque;
import ui.TelaPrincipal;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        // Cria um único estoque global (fonte de dados única)
        GerenciadorEstoque estoque = new GerenciadorEstoque();

        // Produtos iniciais
        estoque.adicionarProduto(new Produto("Cafe Expresso", 3.5, 50));
        estoque.adicionarProduto(new Produto("Cappuccino", 5.0, 40));
        estoque.adicionarProduto(new Produto("Croissant", 4.5, 30));

        // UI deve rodar na Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            new TelaPrincipal(estoque).setVisible(true);
        });
    }
}
