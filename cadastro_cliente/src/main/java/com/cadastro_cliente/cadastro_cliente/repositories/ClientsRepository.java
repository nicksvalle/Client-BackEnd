package com.cadastro_cliente.cadastro_cliente.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cadastro_cliente.cadastro_cliente.entities.Clients;

public interface ClientsRepository extends JpaRepository<Clients, Long>{
    
}
