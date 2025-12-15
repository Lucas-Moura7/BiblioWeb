package com.mycompany.bibliosistemp2.BiblioWeb.controller;

import com.mycompany.bibliosistemp2.BiblioWeb.model.Emprestimo;
import com.mycompany.bibliosistemp2.BiblioWeb.model.Usuario;
import com.mycompany.bibliosistemp2.BiblioWeb.model.Livro;
import com.mycompany.bibliosistemp2.BiblioWeb.repository.EmprestimoRepository;
import com.mycompany.bibliosistemp2.BiblioWeb.repository.UsuarioRepository;
import com.mycompany.bibliosistemp2.BiblioWeb.repository.LivroRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@Controller
@RequestMapping("/emprestimos")
public class EmprestimoController {
     @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LivroRepository livroRepository;

    // Carrega página com selects e lista de empréstimos
    @GetMapping("/listar")
    public String listarEmprestimos(Model model) {
        model.addAttribute("emprestimos", emprestimoRepository.findAll());
        model.addAttribute("usuarios", usuarioRepository.findAll());
        model.addAttribute("livros", livroRepository.findAll());
        return "emprestimos";
    }

    // Realiza o empréstimo
    @PostMapping("/salvar")
    public String salvarEmprestimo(@RequestParam Long usuarioId,
                               @RequestParam Long livroId,
                               @RequestParam String dataEmprestimo,
                               @RequestParam String dataDevolucao,
                               Emprestimo emprestimo) {

    Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
    Livro livro = livroRepository.findById(livroId).orElse(null);

    if (usuario == null || livro == null) {
        return "redirect:/emprestimos/listar?erro=notfound";
    }

    if (livro.getStatus().equalsIgnoreCase("Indisponível")) {
        return "redirect:/emprestimos/listar?erro=indisponivel";
    }

    livro.setStatus("Indisponível");
    livroRepository.save(livro);

    emprestimo.setUsuario(usuario);
    emprestimo.setLivro(livro);
    emprestimo.setDataEmprestimo(LocalDate.parse(dataEmprestimo));
    emprestimo.setDataDevolucao(LocalDate.parse(dataDevolucao));
    emprestimo.setDevolvido(false);

    emprestimoRepository.save(emprestimo);

    return "redirect:/emprestimos/listar";
}

    // Devolver livro
    @GetMapping("/devolver/{id}")
    public String devolver(@PathVariable Long id) {
        Emprestimo emp = emprestimoRepository.findById(id).orElse(null);

        if (emp != null) {
            emp.setDevolvido(true);

            Livro livro = emp.getLivro();
            livro.setStatus("Disponível");

            livroRepository.save(livro);
            emprestimoRepository.save(emp);
        }

        return "redirect:/emprestimos/listar";
    }

    // Deletar registro de empréstimo
    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        emprestimoRepository.deleteById(id);
        return "redirect:/emprestimos/listar";
    }
}
