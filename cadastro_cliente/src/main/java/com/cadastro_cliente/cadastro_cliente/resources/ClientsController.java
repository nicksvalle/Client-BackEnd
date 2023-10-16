package com.cadastro_cliente.cadastro_cliente.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cadastro_cliente.cadastro_cliente.dtos.ClientRequest;
import com.cadastro_cliente.cadastro_cliente.dtos.ClientResponse;
import com.cadastro_cliente.cadastro_cliente.entities.Clients;
import com.cadastro_cliente.cadastro_cliente.mappers.ClientsMapper;
import com.cadastro_cliente.cadastro_cliente.services.ClientsService;


@RestController
@RequestMapping("clientes")
@CrossOrigin
public class ClientsController {
    
    @Autowired
    private ClientsService service;

    @GetMapping
    public ResponseEntity<List<ClientResponse>> getClients(){

        var clients = this.service.getClients();
        return ResponseEntity.ok(ClientsMapper.toDTOList(clients));
    }

    @GetMapping("{id}")
    public ResponseEntity<ClientResponse> getClients(@PathVariable long id){
        var clients = this.service.getClient(id);
        return ResponseEntity.ok(ClientsMapper.toDTO(clients));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteClients(@PathVariable long id){
        this.service.deleteClientsById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<ClientResponse> save(@Validated @RequestBody ClientRequest clients){
        var savedClients = this.service.save(clients);

        URI location = ServletUriComponentsBuilder

                .fromCurrentRequest()

                .path("/{id}")

                .buildAndExpand(savedClients.id())

                .toUri();
                
        return ResponseEntity.created(location).body(savedClients);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@RequestBody Clients clients,
                                       @PathVariable long id
    ){
        this.service.update(id, clients);
        return ResponseEntity.ok().build();
    }
}
