package com.api.bibliotecaspring.repositories;

import com.api.bibliotecaspring.models.Obras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//métodos de transação com o banco de dados
@Repository
public interface ObrasRepository extends JpaRepository<Obras, Long> {

    boolean existsByTitulo(String titulo);
    List<Obras> findByTituloContainingIgnoreCase(String nome);
}
