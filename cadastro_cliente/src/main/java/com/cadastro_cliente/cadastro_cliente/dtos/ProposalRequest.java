package com.cadastro_cliente.cadastro_cliente.dtos;

import jakarta.validation.constraints.NotBlank;

public record ProposalRequest(
    
    @NotBlank(message = "Nome da Empresa não pode ser em branco")
    String empresa,
    
    @NotBlank(message = "Tipo de Certificação não pode ser em branco")
    String type,

    @NotBlank(message = "Data de envio não pode ser em branco")
    String date,

    @NotBlank(message = "Valor não pode ser em branco")
    String valor,

    @NotBlank(message = "Status não pode ser em branco")
    String status,

    @NotBlank(message = "Vendedor não pode ser em branco")
    String vendedor,

    @NotBlank(message = "Comentario não pode ser em branco")
    String comentario
) {
    
}
