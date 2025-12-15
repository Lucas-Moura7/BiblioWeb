package com.mycompany.bibliosistemp2.BiblioWeb.repository;

import com.mycompany.bibliosistemp2.BiblioWeb.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
}
