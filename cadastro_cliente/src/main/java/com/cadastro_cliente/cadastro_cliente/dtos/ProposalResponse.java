package com.cadastro_cliente.cadastro_cliente.dtos;
import java.time.LocalDate;


public record ProposalResponse(
    Long id,
    String empresa,
    String type,
    LocalDate date,
    String valor,
    String status,
    String vendedor,
    String comentario
) {
    
}
