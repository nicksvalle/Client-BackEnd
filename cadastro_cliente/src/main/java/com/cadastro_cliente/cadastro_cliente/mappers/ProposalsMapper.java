package com.cadastro_cliente.cadastro_cliente.mappers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;


import com.cadastro_cliente.cadastro_cliente.dtos.ProposalRequest;
import com.cadastro_cliente.cadastro_cliente.dtos.ProposalResponse;
import com.cadastro_cliente.cadastro_cliente.entities.Proposals;

public class ProposalsMapper {
    
    public static Proposals toEntity(ProposalRequest request) {
        Proposals proposals = new Proposals();
        proposals.setEmpresa(request.empresa());
        proposals.setType(request.type());

        String dateString = request.date();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);

        proposals.setDate(localDate);
        proposals.setValor(request.valor());
        proposals.setStatus(request.status());
        proposals.setVendedor(request.vendedor());
        proposals.setComentario(request.comentario());
        return proposals;
    }

    public static ProposalResponse toDTO(Proposals proposals){
        return new ProposalResponse(proposals.getId(),
                                  proposals.getEmpresa(),
                                  proposals.getType(),
                                  proposals.getDate(),
                                  proposals.getValor(),
                                  proposals.getStatus(),
                                  proposals.getVendedor(),
                                  proposals.getComentario());
    }

    public static List<ProposalResponse> toDTOList(List<Proposals> proposals){
        return proposals.stream()
               .map(ProposalsMapper::toDTO)
               .collect(Collectors.toList());
    }
}

