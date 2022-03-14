package com.api.bibliotecaspring.dtos;

import javax.validation.constraints.NotBlank;
import java.util.Set;

public class ObrasDto {
    @NotBlank(message = "Erro: É obrigatório inserir um titulo.")
    private String titulo;
    @NotBlank(message = "Erro: É obrigatório inserir uma editora")
    private String editora;
    @NotBlank(message = "Erro: É obrigatório inserir url da imagem da obra.")
    private String fotoUrl;
    @NotBlank(message = "Erro: É obrigatório inserir um ou mais autor.")
    private Set<String> autor;
    @NotBlank(message = "Erro: É obrigatório inserir o número de pag.")
    private int numeroPag;

    //getters e setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditora() {
        return editora;
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

    public int getNumeroPag() {
        return numeroPag;
    }

    public void setNumeroPag(int numeroPag) {
        this.numeroPag = numeroPag;
    }
}
