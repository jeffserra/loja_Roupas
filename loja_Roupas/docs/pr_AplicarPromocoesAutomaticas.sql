DELIMITER $$

CREATE PROCEDURE AplicarPromocoesAutomaticas()
BEGIN
    DECLARE promocao_valor_id INT;

    -- Verifica se a promoção já existe
    SELECT ID_Promocao INTO promocao_valor_id
    FROM Promocoes
    WHERE Nome_Promocao = 'Desconto acima de R$200'
    LIMIT 1;

    -- Cria a promoção se não existir
    IF promocao_valor_id IS NULL THEN
        INSERT INTO Promocoes (Nome_Promocao, Desconto_Percentual, Data_Inicio, Data_Fim)
        VALUES ('Desconto acima de R$200', 20.00, CURDATE(), DATE_ADD(CURDATE(), INTERVAL 30 DAY));
        SET promocao_valor_id = LAST_INSERT_ID();
    END IF;

    -- Aplica a promoção apenas aos itens de pedidos com valor total acima de R$200
    INSERT IGNORE INTO Itens_Pedido_Promocao (ID_Item, ID_Promocao)
    SELECT ip.ID_Item, promocao_valor_id
    FROM (
        SELECT ip2.ID_Pedido
        FROM Itens_Pedido ip2
        JOIN Produtos pr2 ON ip2.ID_Produto = pr2.ID_Produto
        GROUP BY ip2.ID_Pedido
        HAVING SUM(ip2.Quantidade * pr2.Preco) > 200
    ) pedidos_validos
    JOIN Itens_Pedido ip ON pedidos_validos.ID_Pedido = ip.ID_Pedido;

    -- REMOVE a promoção dos itens de pedidos com valor <= 200
    DELETE ipp
    FROM Itens_Pedido_Promocao ipp
    JOIN Itens_Pedido ip ON ipp.ID_Item = ip.ID_Item
    JOIN (
        SELECT ip3.ID_Pedido
        FROM Itens_Pedido ip3
        JOIN Produtos pr3 ON ip3.ID_Produto = pr3.ID_Produto
        GROUP BY ip3.ID_Pedido
        HAVING SUM(ip3.Quantidade * pr3.Preco) <= 200
    ) pedidos_invalidos ON ip.ID_Pedido = pedidos_invalidos.ID_Pedido
    WHERE ipp.ID_Promocao = promocao_valor_id;

END $$
DELIMITER ;
