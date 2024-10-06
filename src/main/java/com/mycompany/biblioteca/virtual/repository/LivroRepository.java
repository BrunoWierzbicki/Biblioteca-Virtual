package com.mycompany.biblioteca.virtual.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mycompany.biblioteca.virtual.model.Livro;
import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findByNomeContainingIgnoreCase(String nome);
}
