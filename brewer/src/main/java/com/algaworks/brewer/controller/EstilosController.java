package com.algaworks.brewer.controller;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.algaworks.brewer.model.Estilo;

@Controller
public class EstilosController {

	@RequestMapping("/estilos/novo") // Metodo default: GET
	public String novo(Estilo cidade) {
		return "estilo/CadastroEstilo";
	}

	@RequestMapping(value = "/estilos/novo", method = RequestMethod.POST)
	public String cadastrar(@Valid Estilo cidade, BindingResult result, Model model, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(cidade);
		}

		attributes.addFlashAttribute("mensagem", "Estilo salvo com sucesso"); // Ao fazer o REDIRECT atributo mensagem
																				// // ficará disponível paro o Thymeleaf
		return "redirect:/estilos/novo"; // Utiliza HTTP Redirect
	}

}
