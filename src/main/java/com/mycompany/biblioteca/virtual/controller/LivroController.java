package com.mycompany.biblioteca.virtual.controller;

import com.mycompany.biblioteca.virtual.model.Livro;
import com.mycompany.biblioteca.virtual.repository.LivroRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    @GetMapping("/cadastro")
    public String exibirCadastro(Model model) {
        model.addAttribute("livro", new Livro());
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String cadastrarLivro(@ModelAttribute Livro livro) {
        livroRepository.save(livro);
        return "redirect:/livros/listagem";
    }

    @GetMapping("/listagem")
    public String listarLivros(Model model) {
        model.addAttribute("livros", livroRepository.findAll());
        return "listagem";
    }

    @GetMapping("/detalhe/{id}")
    public String detalheLivro(@PathVariable Long id, Model model) {
        Livro livro = livroRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Livro não encontrado com ID: " + id));
        model.addAttribute("livro", livro);
        return "detalhe";
    }
    
    @GetMapping("/buscar")
public String buscarLivro(@RequestParam String nome, Model model) {
    // Busca o livro pelo nome
    List<Livro> livrosEncontrados = livroRepository.findByNomeContainingIgnoreCase(nome);

    // Verifica se há livros encontrados
    if (livrosEncontrados.isEmpty()) {
        model.addAttribute("mensagem", "Nenhum livro encontrado com o nome: " + nome);
        return "index"; // Retorna à página inicial com a mensagem
    }

    // Se houver apenas um livro encontrado, redireciona para a página de detalhes
    if (livrosEncontrados.size() == 1) {
        Long id = livrosEncontrados.get(0).getId();
        return "redirect:/livros/detalhe/" + id;
    }

    // Se houver mais de um livro encontrado, redireciona para uma página de seleção
    model.addAttribute("livros", livrosEncontrados);
    return "listaLivrosEncontrados"; // Você pode criar essa página para listar os livros encontrados
}

@GetMapping("/autocomplete")
@ResponseBody
public List<Livro> autocomplete(@RequestParam String nome) {
    return livroRepository.findByNomeContainingIgnoreCase(nome);
}


}

