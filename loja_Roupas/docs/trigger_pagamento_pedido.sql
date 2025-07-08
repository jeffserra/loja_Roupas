DELIMITER //

CREATE TRIGGER trigger_pagamento_pedido
AFTER INSERT ON Registros_Pagamentos
FOR EACH ROW
BEGIN
  -- Atualiza status do pedido
  UPDATE Pedidos
  SET Status_Pedido = 'Concluído'
  WHERE ID_Pedido = NEW.ID_Pedido;

  -- Atualiza o estoque
  CALL AtualizarEstoque(NEW.ID_Pedido);
END //

DELIMITER ;
