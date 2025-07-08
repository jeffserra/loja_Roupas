DELIMITER $$

CREATE TRIGGER aplicar_promocao_apos_item_inserido
AFTER INSERT ON Itens_Pedido
FOR EACH ROW
BEGIN
    DECLARE valor_total DECIMAL(10,2);
    DECLARE promocao_id INT;

    SELECT ID_Promocao INTO promocao_id
    FROM Promocoes
    WHERE Nome_Promocao = 'Desconto acima de R$200'
    LIMIT 1;

    SELECT SUM(ip.Quantidade * p.Preco)
    INTO valor_total
    FROM Itens_Pedido ip
    JOIN Produtos p ON ip.ID_Produto = p.ID_Produto
    WHERE ip.ID_Pedido = NEW.ID_Pedido;

    IF valor_total > 200 THEN
        INSERT IGNORE INTO Promocoes_Produtos (ID_Promocao, ID_Produto)
        VALUES (promocao_id, NEW.ID_Produto);
    END IF;
END $$

DELIMITER ;
