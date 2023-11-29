package com.cadastro_cliente.cadastro_cliente.dtos;

public record ClientResponse(
    Long id,
    String cnpj,
    String empresa,
    String contato,
    String email,
    String escopo,
    String nacionalidade,
    String telefone
) {
    
}
