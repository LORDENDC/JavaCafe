# JavaCafe
# ☕ Java Café - Sistema POS

Sistema de ponto de venda (POS) desenvolvido em Java com interface Swing.

---

## Descrição

O Java Café é uma aplicação desktop que simula o funcionamento de uma cafeteria.
Permite gerenciar produtos, controlar estoque e registrar vendas de forma simples.

---

## Funcionalidades

- Cadastro de produtos
- Controle de estoque
- Criação de pedidos (vendas)
- Cálculo automático do total
- Atualização de estoque em tempo real
- Persistência de dados em arquivos CSV

---

##  Estrutura do projeto
...
JavaCafe/
│
├── src/
│   ├── model/
│   │   ├── Produto.java
│   │   ├── ItemPedido.java
│   │   └── Pedido.java
│   │
│   ├── service/
│   │   ├── GerenciadorEstoque.java
│   │   └── GerenciadorVendas.java
│   │
│   ├── exception/
│   │   └── OutOfStockException.java
│   │
│   ├── persistence/
│   │   └── ArquivoUtil.java
│   │
│   ├── ui/
│   │   ├── TelaPrincipal.java
│   │   ├── PainelEstoque.java
│   │   └── PainelPedidos.java
│   │
│   └── Main.java
│
├── data/
│   ├── produtos.csv
│   └── vendas.csv
│
├── README.md
└── .gitignore

...
---

## 📂 Dados

O sistema usa arquivos CSV para salvar dados:

- `data/produtos.csv` → lista de produtos
- `data/vendas.csv` → histórico de vendas

---

## 🛠️ Tecnologias

- Java
- Swing (GUI)
- File I/O (CSV)
- Programação Orientada a Objetos (OOP)

---

## ▶️ Como executar

1. Abrir o projeto em uma IDE (IntelliJ / Eclipse / VS Code)
2. Executar a classe:
3. Main.java
4. 3. A interface gráfica será aberta automaticamente

---

## 🧠 Conceitos aplicados

- Encapsulamento
- Classes e objetos
- Composição
- Tratamento de exceções
- Persistência de dados
- Separação em camadas (MVC simples)

---

## 📈 Possíveis melhorias

- Login de usuários
- Relatórios de vendas
- Interface mais moderna (JavaFX)
- Gráficos de desempenho
- Sistema de descontos

---

## Didrick Chancel EGNINA NDOMBI - 14822368

Projeto desenvolvido para prática de Java e OOP.
