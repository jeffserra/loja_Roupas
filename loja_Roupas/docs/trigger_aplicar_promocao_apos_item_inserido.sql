DELIMITER $$

CREATE TRIGGER aplicar_promocao_apos_item_inserido
AFTER INSERT ON Itens_Pedido
FOR EACH ROW
BEGIN
    CALL AplicarPromocoesAutomaticas();
END $$

DELIMITER ;
