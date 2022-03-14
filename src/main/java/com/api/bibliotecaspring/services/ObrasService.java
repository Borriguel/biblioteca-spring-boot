package com.api.bibliotecaspring.services;

import com.api.bibliotecaspring.models.Obras;
import com.api.bibliotecaspring.repositories.ObrasRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

//regras de negócio para Obras
@Service
public class ObrasService {
    //injeção de dependencia da interface repository
    final ObrasRepository obrasRepository;

    public ObrasService(ObrasRepository obrasRepository) {
        this.obrasRepository = obrasRepository;
    }

    @Transactional
    public Obras salvar(Obras obras) {
        return obrasRepository.save(obras);
    }

    public boolean existsByTitulo(String titulo) {
        return obrasRepository.existsByTitulo(titulo);
    }

    public Page<Obras> findAll(Pageable pageable) {
        return obrasRepository.findAll(pageable);
    }

    public Optional<Obras> acharPorId(Long id) {
        return obrasRepository.findById(id);
    }

    @Transactional
    public void deletar(Obras obras) {
        obrasRepository.delete(obras);
    }

    public List<Obras> acharPorNomeContendo(String nome){
        return obrasRepository.findByTituloContainingIgnoreCase(nome);
    }
}
