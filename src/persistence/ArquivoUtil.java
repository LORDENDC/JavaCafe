package persistence;

import model.Produto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Classe responsável por salvar e carregar arquivos CSV
public class ArquivoUtil {

    // salva lista de produtos em arquivo CSV
    public static void salvarProdutos(List<Produto> produtos, String caminho) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminho))) {

            for (Produto p : produtos) {
                writer.write(p.getNome() + "," + p.getPreco() + "," + p.getEstoque());
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Erro ao salvar produtos: " + e.getMessage());
        }
    }

    // carrega produtos de um arquivo CSV
    public static List<Produto> carregarProdutos(String caminho) {

        List<Produto> produtos = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(caminho))) {

            String linha;

            while ((linha = reader.readLine()) != null) {

                String[] partes = linha.split(",");

                String nome = partes[0];
                double preco = Double.parseDouble(partes[1]);
                int estoque = Integer.parseInt(partes[2]);

                produtos.add(new Produto(nome, preco, estoque));
            }

        } catch (IOException e) {
            System.out.println("Erro ao carregar produtos: " + e.getMessage());
        }

        return produtos;
    }
}
