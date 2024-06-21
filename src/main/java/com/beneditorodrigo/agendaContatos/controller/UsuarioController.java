package com.beneditorodrigo.agendaContatos.controller;

import com.beneditorodrigo.agendaContatos.model.Contato;
import com.beneditorodrigo.agendaContatos.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private ContatoRepository contatoRepository;

    @GetMapping("/contatos/listar")
    @ResponseBody
    public ResponseEntity<List<Contato>> listarContatos(){
        return new ResponseEntity<>(contatoRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/contatos/salvar")
    @ResponseBody
    public ResponseEntity<Contato> salvarContato(@RequestBody Contato contato){
        Contato contatoSalvo = contatoRepository.save(contato);
        return new ResponseEntity<>(contatoSalvo, HttpStatus.CREATED);
    }

    @DeleteMapping("/contatos/excluir")
    @ResponseBody
    public ResponseEntity<String> excluirContato(@RequestParam Long idContato){
        contatoRepository.deleteById(idContato);

        return new ResponseEntity<>("Contato removido com sucesso", HttpStatus.OK);
    }

    @GetMapping("/contatos/buscar")
    @ResponseBody
    public ResponseEntity<Contato> buscarContatoID(@RequestParam Long idContato){
        Contato contato = contatoRepository.findById(idContato).get();

        return new ResponseEntity<>(contato, HttpStatus.OK);
    }

    @PutMapping("/contatos/editar")
    @ResponseBody
    public ResponseEntity<Object> editarContato(@RequestBody Contato contato){
        if (contato.getID() == null){
            return new ResponseEntity<>("Informe o ID para editar contato", HttpStatus.OK);
        }

        contatoRepository.saveAndFlush(contato);
        return new ResponseEntity<>(contato, HttpStatus.OK);
    }

    @GetMapping("/contatos/buscar/nome")
    @ResponseBody
    public ResponseEntity<List<Contato>> buscarContatoNome(@RequestParam String nomeContato){
        List<Contato> contatos = contatoRepository.buscarContatoNome(nomeContato.trim().toUpperCase());

        return new ResponseEntity<>(contatos, HttpStatus.OK);
    }
}
