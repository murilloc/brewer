package com.algaworks.brewer.controller;



import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.algaworks.brewer.model.Cerveja;

@Controller
public class CervejasController {

	@RequestMapping("/cervejas/novo") // Metodo default: GET
	public String novo() {
		return "cerveja/CadastroCerveja";
	}

	@RequestMapping(value = "/cervejas/novo", method = RequestMethod.POST)
	public String cadastrar(@Valid Cerveja cerveja, BindingResult result, Model model, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			model.addAttribute("mensagem","Erro de validação"); // Ao fazer o FORWARD atributo mensagem ficará disponível paro o Thymeleaf	
			return "cerveja/CadastroCerveja"; // Utiliza HTTP Forward	
		}
				
		attributes.addFlashAttribute("mensagem","Cerveja salva com sucesso"); // Ao fazer o REDIRECT atributo mensagem ficará disponível paro o Thymeleaf	
		return "redirect:/cervejas/novo"; // Utiliza HTTP Redirect
	}

}