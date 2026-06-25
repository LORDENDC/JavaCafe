import model.Produto;
import service.GerenciadorEstoque;
import ui.TelaPrincipal;

import javax.swing.*;

// Classe principal da aplicação
public class Main {

    public static void main(String[] args) {

        // cria estoque e adiciona produtos iniciais
        GerenciadorEstoque estoque = new GerenciadorEstoque();

        estoque.adicionarProduto(new Produto("Cafe Expresso", 3.5, 50));
        estoque.adicionarProduto(new Produto("Cappuccino", 5.0, 40));
        estoque.adicionarProduto(new Produto("Croissant", 4.5, 30));

        // abre interface gráfica
        SwingUtilities.invokeLater(() -> {
            TelaPrincipal tela = new TelaPrincipal();
            tela.setVisible(true);
        });
    }
}
