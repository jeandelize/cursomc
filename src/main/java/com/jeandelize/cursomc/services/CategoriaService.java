package com.jeandelize.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeandelize.cursomc.domain.Categoria;
import com.jeandelize.cursomc.repositories.CategoriaRepository;
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
		find(obj.getId());
		return repo.save(obj);
	}
	
	
	public Categoria update(Categoria obj) {
		return repo.save(obj);
	}
	
	
}
