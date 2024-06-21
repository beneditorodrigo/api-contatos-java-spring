package com.beneditorodrigo.agendaContatos.repository;

import com.beneditorodrigo.agendaContatos.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContatoRepository extends JpaRepository<Contato, Long> {

    @Query(value = "" +
            " select " +
            "   entity " +
            " from " +
            "   Contato as entity " +
            " where " +
            "   upper(trim(entity.nome)) like %?1%")
    List<Contato> buscarContatoNome(String nomeContato);
}
