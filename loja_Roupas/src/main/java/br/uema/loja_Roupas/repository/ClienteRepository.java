package br.uema.loja_Roupas.repository;

import br.uema.loja_Roupas.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
