package com.cadastro_cliente.cadastro_cliente.mappers;

import java.util.List;
import java.util.stream.Collectors;


import com.cadastro_cliente.cadastro_cliente.dtos.ClientRequest;
import com.cadastro_cliente.cadastro_cliente.dtos.ClientResponse;
import com.cadastro_cliente.cadastro_cliente.entities.Clients;


public class ClientsMapper {
    
    public static Clients toEntity(ClientRequest request) {
        Clients clients = new Clients();
        clients.setCnpj(request.cnpj());
        clients.setEmpresa(request.empresa());
        clients.setContato(request.contato());
        clients.setEmail(request.email());
        clients.setNacionalidade(request.nacionalidade());
        clients.setTelefone(request.telefone());
        return clients;
    }

    public static ClientResponse toDTO(Clients clients){
        return new ClientResponse(clients.getId(),
                                  clients.getCnpj(),
                                  clients.getEmpresa(),
                                  clients.getContato(),
                                  clients.getEmail(),
                                  clients.getNacionalidade(),
                                  clients.getTelefone());
    }

    public static List<ClientResponse> toDTOList(List<Clients> clients){
        return clients.stream()
               .map(ClientsMapper::toDTO)
               .collect(Collectors.toList());
    }
}

