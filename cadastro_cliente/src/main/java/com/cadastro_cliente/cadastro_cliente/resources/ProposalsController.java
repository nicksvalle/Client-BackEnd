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

import com.cadastro_cliente.cadastro_cliente.dtos.ProposalRequest;
import com.cadastro_cliente.cadastro_cliente.dtos.ProposalResponse;
import com.cadastro_cliente.cadastro_cliente.entities.Proposals;
import com.cadastro_cliente.cadastro_cliente.mappers.ProposalsMapper;
import com.cadastro_cliente.cadastro_cliente.services.ProposalsService;

@RestController
@RequestMapping("proposals")
@CrossOrigin(origins = "http://localhost:4200")
public class ProposalsController {
    
    @Autowired
    private ProposalsService service;

    @GetMapping
    public ResponseEntity<List<ProposalResponse>> getProposals(){

        var proposals = this.service.getProposals();
        return ResponseEntity.ok(ProposalsMapper.toDTOList(proposals));
    }

    @GetMapping("{id}")
    public ResponseEntity<ProposalResponse> getProposals(@PathVariable long id){
        var proposals = this.service.getProposal(id);
        return ResponseEntity.ok(ProposalsMapper.toDTO(proposals));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProposals(@PathVariable long id){
        this.service.deleteProposalsById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<ProposalResponse> save(@Validated @RequestBody ProposalRequest proposals){
        var savedProposals = this.service.save(proposals);

        URI location = ServletUriComponentsBuilder

                .fromCurrentRequest()

                .path("/{id}")

                .buildAndExpand(savedProposals.id())

                .toUri();
                
        return ResponseEntity.created(location).body(savedProposals);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@RequestBody Proposals proposals,
                                       @PathVariable long id
    ){
        this.service.update(id, proposals);
        return ResponseEntity.ok().build();
    }
}
