-- Criação do banco de dados
CREATE DATABASE LojaRoupas;
USE LojaRoupas;

-- Tabela Clientes
CREATE TABLE Clientes (
    ID_Cliente INT PRIMARY KEY AUTO_INCREMENT,
    Nome_Cliente VARCHAR(255),
    Endereco VARCHAR(255),
    Email VARCHAR(255),
    Numero_Telefone VARCHAR(15)
);

-- Tabela Marcas
CREATE TABLE Marcas (
    ID_Marca INT PRIMARY KEY AUTO_INCREMENT,
    Nome_Marca VARCHAR(100)
);

-- Tabela Produtos
CREATE TABLE Produtos (
    ID_Produto INT PRIMARY KEY AUTO_INCREMENT,
    Nome_Produto VARCHAR(255),
    Descricao TEXT,
    Preco DECIMAL(10, 2),
    Categoria VARCHAR(50),
    Tamanho VARCHAR(10),
    Cor VARCHAR(50),
    Genero VARCHAR(10),
    ID_Marca INT,
    FOREIGN KEY (ID_Marca) REFERENCES Marcas(ID_Marca)
);

-- Tabela Estoque
CREATE TABLE Estoque (
    ID_Estoque INT PRIMARY KEY AUTO_INCREMENT,
    ID_Produto INT,
    Quantidade_Em_Estoque INT,
    FOREIGN KEY (ID_Produto) REFERENCES Produtos(ID_Produto)
);

-- Tabela Pedidos
CREATE TABLE Pedidos (
    ID_Pedido INT PRIMARY KEY AUTO_INCREMENT,
    Data_Pedido DATE,
    ID_Cliente INT,
    Status_Pedido VARCHAR(20),
    FOREIGN KEY (ID_Cliente) REFERENCES Clientes(ID_Cliente)
);

-- Tabela Itens_Pedido
CREATE TABLE Itens_Pedido (
    ID_Item INT AUTO_INCREMENT PRIMARY KEY,
    ID_Pedido INT,
    ID_Produto INT,
    Quantidade INT,
    FOREIGN KEY (ID_Pedido) REFERENCES Pedidos(ID_Pedido),
    FOREIGN KEY (ID_Produto) REFERENCES Produtos(ID_Produto)
);

-- Tabela Registros de Pagamentos
CREATE TABLE Registros_Pagamentos (
    ID_Pagamento INT PRIMARY KEY AUTO_INCREMENT,
    ID_Pedido INT,
    Metodo_Pagamento VARCHAR(50),
    Valor_Pagamento DECIMAL(10, 2),
    Data_Pagamento DATE,
    FOREIGN KEY (ID_Pedido) REFERENCES Pedidos(ID_Pedido)
);

-- Tabela Promocoes
CREATE TABLE Promocoes (
    ID_Promocao INT AUTO_INCREMENT PRIMARY KEY,
    Nome_Promocao VARCHAR(100) NOT NULL,
    Desconto_Percentual DECIMAL(5,2) NOT NULL,
    Data_Inicio DATE NOT NULL,
    Data_Fim DATE NOT NULL
);

-- Tabela associativa Promocoes_Produtos
CREATE TABLE Promocoes_Produtos (
    ID_Promocao INT,
    ID_Produto INT,
    PRIMARY KEY (ID_Promocao, ID_Produto),
    FOREIGN KEY (ID_Promocao) REFERENCES Promocoes(ID_Promocao),
    FOREIGN KEY (ID_Produto) REFERENCES Produtos(ID_Produto)
);
