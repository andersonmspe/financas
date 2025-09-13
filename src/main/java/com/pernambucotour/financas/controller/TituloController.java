package com.pernambucotour.financas.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pernambucotour.financas.model.StatusTitulo;
import com.pernambucotour.financas.model.Titulo;
import com.pernambucotour.financas.repository.TitulosRepository;

@Controller
@RequestMapping("/titulos")
public class TituloController {

	@Autowired
	private TitulosRepository titulosRepository;
	
	@RequestMapping("/novo")
	public ModelAndView novo() {
		
		ModelAndView mv = new ModelAndView("CadastroTitulo");
		//mv.addObject("todosStatusTitulo", StatusTitulo.values());
		mv.addObject(new Titulo());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvar(@Validated Titulo titulo, Errors errors) {
		
		ModelAndView mv = new ModelAndView("CadastroTitulo");
		if(errors.hasErrors()) {
			return mv;			
		}
						
		titulosRepository.save(titulo);
				
		mv.addObject("mensagem", "Título salvo com sucesso!");
		todosStatusTitulo();
		return mv;
		
	}
	
	@ModelAttribute("todosStatusTitulo")
	public List<StatusTitulo> todosStatusTitulo() {
		return Arrays.asList(StatusTitulo.values());
	}
	
	@RequestMapping
	public ModelAndView pesquisar() {
		
		List<Titulo> todosTitulos = titulosRepository.findAll();
		ModelAndView mv = new ModelAndView("PesquisaTitulos");
		mv.addObject("titulos", todosTitulos);		
		
		return mv;
	}
	
	
	
}
