package com.mycompany.bibliosistemp2.BiblioWeb.controller;

import com.mycompany.bibliosistemp2.BiblioWeb.model.Livro;
import com.mycompany.bibliosistemp2.BiblioWeb.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/livros")
public class LivroController {
  @Autowired
    private LivroRepository livroRepository;

    @GetMapping("/listar")
    public String listarLivros(Model model) {
        model.addAttribute("livros", livroRepository.findAll());
        return "livros";
    }

    @PostMapping("/salvar")
    public String salvarLivro(Livro livro) {
        livro.setStatus("Disponível"); // padrão ao cadastrar
        livroRepository.save(livro);
        return "redirect:/livros/listar";
    }

    @GetMapping("/editar/{id}")
    public String editarLivro(@PathVariable Long id, Model model) {
        model.addAttribute("livro", livroRepository.findById(id).orElse(null));
        return "livro_editar";
    }

    @PostMapping("/atualizar")
    public String atualizarLivro(Livro livro) {
        livroRepository.save(livro);
        return "redirect:/livros/listar";
    }

    @GetMapping("/deletar/{id}")
    public String deletarLivro(@PathVariable Long id) {
        livroRepository.deleteById(id);
        return "redirect:/livros/listar";
    }
}
