package com.api.bibliotecaspring.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

//classe modelo para persistencia no banco de dados
@Entity
@Table(name = "tb_obras")
public class Obras implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(nullable = false, unique = true, name = "titulos")
    private String titulo;
    @Column(nullable = false, name = "numero_paginas")
    private int numeroPag;
    @Column(nullable = false, name = "editora")
    private String editora;
    @Column(nullable = false, name = "foto_url")
    private String fotoUrl;
    @ElementCollection
    @CollectionTable(name = "tb_autores", joinColumns = @JoinColumn(name = "tb_obras"))
    @Column(name = "autores", nullable = false)
    private Set<String> autor = new HashSet<>();

    //construtores
    public Obras() {
    }

    public Obras(String titulo, int numeroPag, String editora, String fotoUrl, Set<String> autor) {
        this.titulo = titulo;
        this.numeroPag = numeroPag;
        this.editora = editora;
        this.fotoUrl = fotoUrl;
        this.autor = autor;
    }
    //getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditora() {
        return editora;
    }

    public int getNumeroPag() {
        return numeroPag;
    }

    public void setNumeroPag(int numeroPag) {
        this.numeroPag = numeroPag;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public Set<String> getAutor() {
        return autor;
    }

    public void setAutor(Set<String> autor) {
        this.autor = autor;
    }

    //equals e hashcode por id
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Obras that = (Obras) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
