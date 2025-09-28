package Codigo.Backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import Codigo.Backend.Services.automovelService;
import Codigo.Backend.models.Automovel;

@Controller
@RequestMapping("/agente")
public class automovelAgente {
    
    @Autowired
    private automovelService automovelService;

    @GetMapping
    public String painelAgente(Model model) {
        model.addAttribute("automoveis", automovelService.listarTodosAutomoveis());
        return "dashboardAgente";
    }

    @GetMapping("/automoveis")
    public String listarAutomoveis(Model model) {
        model.addAttribute("automoveis", automovelService.listarTodosAutomoveis());
        return "dashboardAgente";
    }

    @GetMapping("/automoveis/novo")
    public String exibirFormularioCriacao(Model model) {
        model.addAttribute("automovel", new Automovel());
        model.addAttribute("acao", "Adicionar");
        return "agente/formulario-automovel";
    }

    @PostMapping("/automoveis/criar")
    public String criarAutomovel(@ModelAttribute Automovel automovel) {
        try {
            automovelService.criarAutomovel(automovel);
            return "redirect:/dashboardAgente?success=Automóvel+adicionado+com+sucesso";
        } catch (IllegalArgumentException e) {
            return "redirect:/dashboardAgente?error=" + e.getMessage().replace(" ", "+");
        } catch (Exception e) {
            return "redirect:/dashboardAgente?error=Erro+ao+adicionar+automóvel";
        }
    }

    @GetMapping("/automoveis/editar/{id}")
    public String exibirFormularioEdicao(@PathVariable Long id, Model model) {
        Automovel automovel = automovelService.obterAutomovelPorId(id);
        model.addAttribute("automovel", automovel);
        return "agente/formulario-automovel";
    }

    @PostMapping("/automoveis/atualizar/{id}")
    public String atualizarAutomovel(@PathVariable Long id, @ModelAttribute Automovel automovel) {
        try {
            automovelService.atualizarAutomovel(id, automovel);
            return "redirect:/dashboardAgente?success=Automóvel+atualizado+com+sucesso";
        } catch (IllegalArgumentException e) {
            return "redirect:/dashboardAgente?error=" + e.getMessage().replace(" ", "+");
        } catch (Exception e) {
            return "redirect:/dashboardAgente?error=Erro+ao+atualizar+automóvel";
        }
    }

    @GetMapping("/automoveis/deletar/{id}")
    public String deletarAutomovel(@PathVariable Long id) {
        automovelService.deletarAutomovel(id);
        return "redirect:/agente/automoveis";
    }
}