package com.cadastro_cliente.cadastro_cliente.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table (name ="TBL_CLIENTE")
public class Clients implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 14, nullable = false, name = "cliente_cnpj")
    private String cnpj;

    @Column(length = 50, nullable = false, name = "cliente_empresa")
    private String empresa;

    @Column(length = 200, nullable = false, name = "cliente_contato")
    private String contato;

    @Column(length = 1024, nullable = false, name = "cliente_email")
    private String email;

    @Column(length = 1024, nullable = false, name = "cliente_nacionalidade")
    private String nacionalidade;

    @Column(length = 1024, nullable = false, name = "cliente_telefone")
    private String telefone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String string) {
        this.cnpj = string;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Clients other = (Clients) obj;
        if (id != other.id)
            return false;
        return true;
    }
}