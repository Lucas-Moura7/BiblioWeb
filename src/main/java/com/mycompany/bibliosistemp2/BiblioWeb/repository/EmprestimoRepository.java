package com.mycompany.bibliosistemp2.BiblioWeb.repository;

import com.mycompany.bibliosistemp2.BiblioWeb.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
}
