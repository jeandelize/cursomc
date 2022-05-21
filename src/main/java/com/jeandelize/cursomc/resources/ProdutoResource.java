package com.jeandelize.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jeandelize.cursomc.domain.Categoria;
import com.jeandelize.cursomc.domain.Cliente;
import com.jeandelize.cursomc.domain.Produto;
import com.jeandelize.cursomc.domain.Cliente;
import com.jeandelize.cursomc.dto.CategoriaDTO;
import com.jeandelize.cursomc.dto.ProdutoDTO;
import com.jeandelize.cursomc.dto.ClienteNewDTO;
import com.jeandelize.cursomc.services.CategoriaService;
import com.jeandelize.cursomc.services.ClienteService;
import com.jeandelize.cursomc.services.ProdutoService;

@RestController
@RequestMapping(value="/clientes")
public class ProdutoResource {
	
		
		@Autowired
		private ProdutoService service;
		
		@RequestMapping(value="/{id}",method=RequestMethod.GET)
		public ResponseEntity<Optional<Produto>> find(@PathVariable Integer id) {
			Optional<Produto> obj = service.find(id);
			return ResponseEntity.ok().body(obj);
			
		}
		
	
		
		@RequestMapping(value="/page",method=RequestMethod.GET)	
		public ResponseEntity<Page<ProdutoDTO>> findPage(
				@RequestParam(value="page", defaultValue="0") int page,
				@RequestParam(value="linesPerPage", defaultValue="24")int linesPerPage,
				@RequestParam(value="orderBy", defaultValue="nome")String orderBy,
				@RequestParam(value="direction", defaultValue="ASC")String direction)  {
			
			Page<Categoria> list = service.findPage(page, linesPerPage, orderBy, direction);
			Page<CategoriaDTO> listDTo = list.map(obj -> new CategoriaDTO(obj));
			return ResponseEntity.ok().body(listDTo);
			
		}
		
		


	}


