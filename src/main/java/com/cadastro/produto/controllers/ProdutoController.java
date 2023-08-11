package com.cadastro.produto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cadastro.produto.models.Produto;
import com.cadastro.produto.repository.ProdutoRepository;



@Controller
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository mtr;

	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping(value="/cadastrarProduto", method=RequestMethod.GET)
	public String cadastrarProduto() {
		return "content/cadastrar-produto";
	}
	
	@RequestMapping(value="/cadastrarProduto", method=RequestMethod.POST)
	public String cadastrarProduto(Produto produto) {
		mtr.save(produto);
		return "redirect:/cadastrarProduto";
	}
	
	@RequestMapping("/listarProdutos")
	public ModelAndView listarProdutos() {
		
		ModelAndView mt = new ModelAndView("content/listar-produtos");
		
		Iterable<Produto> produto = mtr.findAll();
		mt.addObject("produto", produto);
		
		return mt;
		
	}
	
	@RequestMapping(value="/alterarProduto/{codigoProduto}", method=RequestMethod.GET)
	public ModelAndView formAlterarProduto(@PathVariable("codigoProduto") long codigoProduto) {
		
		Produto produto = mtr.findByCodigoProduto(codigoProduto);
		
		ModelAndView mt = new ModelAndView("content/atualizar-produto");
		
		mt.addObject("produto", produto);
		
		return mt;
		
	}
	
	@RequestMapping(value="/alterarProduto/{codigoProduto}", method=RequestMethod.POST)
	public String alterarProduto(@Validated Produto produto, BindingResult result, RedirectAttributes attributes) {
		
		mtr.save(produto);
		return "redirect:/listarProdutos";
		
	}
	
	@RequestMapping("/confirmarExclusaoProduto/{codigoProduto}")
	public ModelAndView confirmarExclusaoProduto(@PathVariable("codigoProduto") long codigoProduto) {
		
		Produto produto = mtr.findByCodigoProduto(codigoProduto);
		
		ModelAndView mt = new ModelAndView("content/excluir-produto");
		
		mt.addObject("produto", produto);
		
		return mt;
		
	}
	
	@RequestMapping("/excluirProduto")
	public String excluirProduto(long codigoProduto) {
		
		Produto produto = mtr.findByCodigoProduto(codigoProduto);
		mtr.delete(produto);
		
		return "redirect:/listarProdutos";
		
	}
	
}
