package com.algaworks.brewer.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.algaworks.brewer.model.Usuario;

@Controller
public class UsuarioController {

	@RequestMapping("/usuarios/novo") // Metodo default: GET
	public String novo(Usuario usuario) {
		return "usuario/CadastroUsuario";
	}

	@RequestMapping(value = "/usuarios/novo", method = RequestMethod.POST)
	public String cadastrar(@Valid Usuario usuario, BindingResult result, Model model, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(usuario);
		}

		attributes.addFlashAttribute("mensagem", "Usuario salvo com sucesso"); // Ao fazer o REDIRECT atributo mensagem
																				// // ficará disponível paro o Thymeleaf
		return "redirect:/usuarios/novo"; // Utiliza HTTP Redirect
	}	

}
