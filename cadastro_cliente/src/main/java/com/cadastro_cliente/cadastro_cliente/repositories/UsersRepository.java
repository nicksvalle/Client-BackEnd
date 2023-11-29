package com.cadastro_cliente.cadastro_cliente.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cadastro_cliente.cadastro_cliente.entities.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findByEmail(String email);
}
