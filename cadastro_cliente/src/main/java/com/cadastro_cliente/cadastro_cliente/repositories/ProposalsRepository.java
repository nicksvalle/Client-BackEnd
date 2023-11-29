package com.cadastro_cliente.cadastro_cliente.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cadastro_cliente.cadastro_cliente.entities.Proposals;

public interface ProposalsRepository extends JpaRepository<Proposals, Long>{
    
}
