package com.cadastro_cliente.cadastro_cliente.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cadastro_cliente.cadastro_cliente.dtos.ClientRequest;
import com.cadastro_cliente.cadastro_cliente.dtos.ClientResponse;
import com.cadastro_cliente.cadastro_cliente.entities.Clients;
import com.cadastro_cliente.cadastro_cliente.mappers.ClientsMapper;
import com.cadastro_cliente.cadastro_cliente.repositories.ClientsRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClientsService {
    
    @Autowired
    private ClientsRepository repository;

    public List<Clients> getClients(){
        return this.repository.findAll();
    }

    public Clients getClient(long id){
        return this.repository.findById(id).orElseThrow(
                                                () -> new EntityNotFoundException("Client not found")

                                                
                                            );
    }

    public void deleteClientsById(long id) {
        if(this.repository.existsById(id)){
            this.repository.deleteById(id);
        }
        else{
            throw new EntityNotFoundException("Client not found");
        }
    }

    public ClientResponse save(ClientRequest product){
        var entity = this.repository.save(ClientsMapper.toEntity(product));
        return ClientsMapper.toDTO(entity);
    }

    public void update(long id, Clients clients) {
        try {
            Clients updateClients = this.repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Client not found")
            );
    
            updateClients.setCnpj(clients.getCnpj());
            updateClients.setEmpresa(clients.getEmpresa());
            updateClients.setContato(clients.getContato());
            updateClients.setEmail(clients.getEmail());
            updateClients.setNacionalidade(clients.getNacionalidade());
            updateClients.setTelefone(clients.getTelefone());
    
            this.repository.save(updateClients);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Client not found");
        }
    }

}
