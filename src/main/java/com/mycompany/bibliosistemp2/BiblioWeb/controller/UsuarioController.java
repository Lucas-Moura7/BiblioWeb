package com.mycompany.bibliosistemp2.BiblioWeb.controller;

import com.mycompany.bibliosistemp2.BiblioWeb.model.Usuario;
import com.mycompany.bibliosistemp2.BiblioWeb.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
@Autowired
    private UsuarioRepository usuarioRepository;

    // Carrega a página de listagem
    @GetMapping("/listar")
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioRepository.findAll());
        return "usuarios"; // página HTML
    }

    // Salva um novo usuário
    @PostMapping("/salvar")
    public String salvarUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
        return "redirect:/usuarios/listar";
    }

    // Tela de edição
    @GetMapping("/editar/{id}")
    public String editarUsuario(@PathVariable Long id, Model model) {
        model.addAttribute("usuario", usuarioRepository.findById(id).orElse(null));
        return "usuario_editar";
    }

    // Salva edição
    @PostMapping("/atualizar")
    public String atualizarUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
        return "redirect:/usuarios/listar";
    }

    // Exclui usuário
    @GetMapping("/deletar/{id}")
    public String deletarUsuario(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
        return "redirect:/usuarios/listar";
    }
}
