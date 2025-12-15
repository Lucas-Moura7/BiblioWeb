package com.mycompany.bibliosistemp2.BiblioWeb.repository;

import com.mycompany.bibliosistemp2.BiblioWeb.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}