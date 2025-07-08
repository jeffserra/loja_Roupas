DELIMITER $$

CREATE PROCEDURE AplicarPromocoesAutomaticas()
BEGIN
    DECLARE promocao_valor_id INT;

    -- Captura o ID da promoção para pedidos acima de R$200
    SELECT ID_Promocao INTO promocao_valor_id
    FROM Promocoes
    WHERE Nome_Promocao = 'Desconto acima de R$200';

    -- Aplica a promoção a produtos de pedidos com valor total acima de R$200
    INSERT IGNORE INTO Promocoes_Produtos (ID_Promocao, ID_Produto)
	SELECT DISTINCT promocao_valor_id, ip.ID_Produto
	FROM (
		SELECT p.ID_Pedido
		FROM Pedidos p
		JOIN Itens_Pedido ip ON p.ID_Pedido = ip.ID_Pedido
		JOIN Produtos pr ON ip.ID_Produto = pr.ID_Produto
		GROUP BY p.ID_Pedido
		HAVING SUM(ip.Quantidade * pr.Preco) > 200
	) pedidos_validos
	JOIN Itens_Pedido ip ON pedidos_validos.ID_Pedido = ip.ID_Pedido;

END $$

DELIMITER ;