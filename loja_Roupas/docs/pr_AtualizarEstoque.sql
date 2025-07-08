DELIMITER //

CREATE PROCEDURE AtualizarEstoque (IN idPedido INT)
BEGIN
  DECLARE done INT DEFAULT 0;
  DECLARE p_idProduto INT;
  DECLARE p_quantidade INT;

  DECLARE cursor_itens CURSOR FOR 
    SELECT ID_Produto, Quantidade FROM Itens_Pedido WHERE ID_Pedido = idPedido;

  DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;

  OPEN cursor_itens;

  repetir: LOOP
    FETCH cursor_itens INTO p_idProduto, p_quantidade;
    IF done THEN 
      LEAVE repetir;
    END IF;

    UPDATE Estoque
    SET Quantidade_Em_Estoque = Quantidade_Em_Estoque - p_quantidade
    WHERE ID_Produto = p_idProduto;
  END LOOP;

  CLOSE cursor_itens;
END //

DELIMITER ;
