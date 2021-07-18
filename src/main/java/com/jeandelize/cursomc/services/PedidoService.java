package com.jeandelize.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeandelize.cursomc.domain.Pedido;
import com.jeandelize.cursomc.repositories.PedidoRepository;
import com.jeandelize.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	public Optional<Pedido> buscar(Integer id) {
		
		Optional<Pedido> obj = repo.findById(id);	
		
		
       if ( obj.isEmpty()) {
			throw new ObjectNotFoundException("Objeto não encontrado Id: " + id + 
					" Id, Tipo: " + Pedido.class.getName());
		}
	
		return obj;
	
		
	}

	
}
