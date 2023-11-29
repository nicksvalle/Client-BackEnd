package com.cadastro_cliente.cadastro_cliente.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cadastro_cliente.cadastro_cliente.dtos.ProposalRequest;
import com.cadastro_cliente.cadastro_cliente.dtos.ProposalResponse;
import com.cadastro_cliente.cadastro_cliente.entities.Proposals;
import com.cadastro_cliente.cadastro_cliente.mappers.ProposalsMapper;
import com.cadastro_cliente.cadastro_cliente.repositories.ProposalsRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProposalsService {
    
    @Autowired
    private ProposalsRepository repository;

    public List<Proposals> getProposals(){
        return this.repository.findAll();
    }

    public Proposals getProposal(long id){
        return this.repository.findById(id).orElseThrow(
                                                () -> new EntityNotFoundException("Proposal not found")

                                                
                                            );
    }

    public void deleteProposalsById(long id) {
        if(this.repository.existsById(id)){
            this.repository.deleteById(id);
        }
        else{
            throw new EntityNotFoundException("Proposal not found");
        }
    }

    public ProposalResponse save(ProposalRequest product){
        var entity = this.repository.save(ProposalsMapper.toEntity(product));
        return ProposalsMapper.toDTO(entity);
    }

    public void update(long id, Proposals proposals) {
        try {
            Proposals updateProposals = this.repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Proposal not found")
            );
    
            updateProposals.setEmpresa(proposals.getEmpresa());
            updateProposals.setType(proposals.getType());
            updateProposals.setDate(proposals.getDate());
            updateProposals.setValor(proposals.getValor());
            updateProposals.setStatus(proposals.getStatus());
            updateProposals.setVendedor(proposals.getVendedor());
            updateProposals.setVendedor(proposals.getVendedor());
            updateProposals.setComentario(proposals.getComentario());

    
            this.repository.save(updateProposals);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Proposal not found");
        }
    }

}
