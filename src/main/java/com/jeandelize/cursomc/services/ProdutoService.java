package com.jeandelize.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jeandelize.cursomc.domain.Categoria;
import com.jeandelize.cursomc.domain.Produto;
import com.jeandelize.cursomc.repositories.CategoriaRepository;
import com.jeandelize.cursomc.repositories.ProdutoRepository;
import com.jeandelize.cursomc.services.exceptions.ObjectNotFoundException;
@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repo;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Produto find(Integer id) {
		Produto obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id
					+ ", Tipo: " + Produto.class.getName());
		}
		return obj;
	}


	public Page<Produto> search(String nome, List<Integer> ids, int page, int linesPerPage, String orderBy, String direction) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.ASC, orderBy);
		List<Categoria> categorias = categoriaRepository.findAll(ids);
		return repo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);	


	}




}