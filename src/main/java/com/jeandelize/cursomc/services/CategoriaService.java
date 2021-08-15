package com.jeandelize.cursomc.services;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.jeandelize.cursomc.domain.Categoria;
import com.jeandelize.cursomc.dto.CategoriaDTO;
import com.jeandelize.cursomc.repositories.CategoriaRepository;
import com.jeandelize.cursomc.services.exceptions.DataIntegrityException;
import com.jeandelize.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Optional<Categoria> find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);	
		
	   if ( obj.isEmpty()) {
			throw new ObjectNotFoundException("Objeto não encontrado Id: " + id + 
					" Id, Tipo: " + Categoria.class.getName());
		}
	
		return obj;
	
		
	}
	
	
	public Categoria insert(Categoria obj) {
		return repo.save(obj);
	}
	
	
	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	
	
	public void delete(Integer id) {
		find(id);
		try {
		repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma Categoria que possui Produtos");
		}
	}
	
	public List<Categoria> findAll() {
		return repo.findAll();
	}
	
	
	public Page <Categoria> findPage(int page, int linesPerPage, String orderBy, String direction) {
		     PageRequest pageRequest = PageRequest.of(
             page,
             linesPerPage,
             Sort.Direction.ASC,
             orderBy);
		     
		     return repo.findAll(pageRequest);
	}
	
	
	public Categoria fromDTO(CategoriaDTO objDto) {
		return new Categoria(objDto.getId(),objDto.getNome());
	}
	
}
