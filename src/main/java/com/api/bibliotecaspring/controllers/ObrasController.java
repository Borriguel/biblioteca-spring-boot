package com.api.bibliotecaspring.controllers;

import com.api.bibliotecaspring.dtos.ObrasDto;
import com.api.bibliotecaspring.models.Obras;
import com.api.bibliotecaspring.services.ObrasService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

//métodos do CRUD
@RestController
@RequestMapping("/obras")
public class ObrasController {

    //injeção de dependencia da classe de serviços
    final ObrasService obrasService;

    public ObrasController(ObrasService obrasService) {
        this.obrasService = obrasService;
    }

    //métodos http Post, Get, Put e Delete
    @PostMapping //método Post para criação de uma obra
    public ResponseEntity<Object> salvarObras(@RequestBody @Valid ObrasDto obrasDto){
        if(obrasService.existsByTitulo(obrasDto.getTitulo())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Titulo da obra já está em uso.");
        }
        var obras = new Obras();
        BeanUtils.copyProperties(obrasDto, obras);
        return ResponseEntity.status(HttpStatus.CREATED).body(obrasService.salvar(obras));
    }

    @GetMapping //método Get para listar todas as obras paginadas com 5 resultados ordenados pelo titulo da obra
    public ResponseEntity<Page<Obras>> listarTodasObras(@PageableDefault(size = 5, sort = "titulo") Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(obrasService.findAll(pageable));
    }

    @GetMapping("/{id}") //método Get passando um id para retornar uma obra
    public ResponseEntity<Object> listarObrasPorId(@PathVariable(value = "id") Long Id){
        Optional<Obras> obrasOptional = obrasService.acharPorId(Id);
        if(obrasOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Obra não encontrada.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(obrasOptional.get());
    }

    @GetMapping("/titulo/{titulo}") //método Get retornando obras pelo titulo ou parte do titulo
    public ResponseEntity<Object> listarObrasPorTitulo(@PathVariable(value = "titulo") String nome) {
        List<Obras> obrasList = obrasService.acharPorNomeContendo(nome);
        if (obrasList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Obra não encontrada.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(obrasList);
    }

    @DeleteMapping("/{id}") //método Delete para deletar uma obra
    public ResponseEntity<Object> deletarObras(@PathVariable(value = "id") Long id){
        Optional<Obras> obrasOptional = obrasService.acharPorId(id);
        if(obrasOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Obra não encontrada.");
        }
        obrasService.deletar(obrasOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Obra deletada com sucesso.");
    }

    @PutMapping("/{id}") //método Put para alterar uma obra pelo id
    public ResponseEntity<Object> atualizarObras(@PathVariable(value = "id") Long id, @RequestBody @Valid ObrasDto obrasDto){
        Optional<Obras> obrasOptional = obrasService.acharPorId(id);
        if(obrasOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Obra não encontrada.");
        }
        var obras = new Obras();
        BeanUtils.copyProperties(obrasDto, obras);
        obras.setId(obrasOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(obrasService.salvar(obras));
    }
}
