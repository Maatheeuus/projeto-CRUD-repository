package com.cadastro.produto.repository;

import org.springframework.data.repository.CrudRepository;

import com.cadastro.produto.models.Produto;


public interface ProdutoRepository extends CrudRepository<Produto, String> {
	
	Produto findByCodigoProduto(long CodigoProduto);
	Produto deleteByCodigoProduto(long CodigoProduto);

}
