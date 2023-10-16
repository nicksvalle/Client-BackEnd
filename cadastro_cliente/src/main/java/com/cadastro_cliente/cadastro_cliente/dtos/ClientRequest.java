package com.cadastro_cliente.cadastro_cliente.dtos;

import jakarta.validation.constraints.NotBlank;

public record ClientRequest(
    @NotBlank(message = "CNPJ não pode ser em branco")
    String cnpj,
    
    @NotBlank(message = "Nome da Empresa não pode ser em branco")
    String empresa,
    
    @NotBlank(message = "Contato não pode ser em branco")
    String contato,

    @NotBlank(message = "Email não pode ser em branco")
    String email,

    @NotBlank(message = "Nacionalidade não pode ser em branco")
    String nacionalidade,

    @NotBlank(message = "Telefone não pode ser em branco")
    String telefone
) {
    
}
