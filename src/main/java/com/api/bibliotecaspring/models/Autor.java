package com.api.bibliotecaspring.models;

import javax.persistence.*;
import java.io.Serializable;

//classe modelo para persistencia no banco de dados
@Embeddable
@Table(name = "tb_autores")
public class Autor implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private Long obras;

    //construtores
    public Autor() {
    }
    public Autor(String nome) {
        this.nome = nome;
    }

    //getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getObras() {
        return obras;
    }

    public void setObras(Long obras) {
        this.obras = obras;
    }
}
