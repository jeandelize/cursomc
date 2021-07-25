package com.jeandelize.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeandelize.cursomc.domain.Cliente;
import com.jeandelize.cursomc.repositories.ClienteRepository;
import com.jeandelize.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public Optional<Cliente> find(Integer id) {
		
		Optional<Cliente> obj = repo.findById(id);	
		
		
       if ( obj.isEmpty()) {
			throw new ObjectNotFoundException("Objeto não encontrado Id: " + id + 
					" Id, Tipo: " + Cliente.class.getName());
		}
	
		return obj;
	
		
	}

}
