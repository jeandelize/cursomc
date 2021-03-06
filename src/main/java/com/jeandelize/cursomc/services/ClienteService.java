package com.jeandelize.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jeandelize.cursomc.domain.Categoria;
import com.jeandelize.cursomc.domain.Cidade;
import com.jeandelize.cursomc.domain.Cliente;
import com.jeandelize.cursomc.domain.Endereco;
import com.jeandelize.cursomc.domain.enums.TipoCliente;
import com.jeandelize.cursomc.domain.Cliente;
import com.jeandelize.cursomc.dto.ClienteDTO;
import com.jeandelize.cursomc.dto.ClienteNewDTO;
import com.jeandelize.cursomc.repositories.CidadeRepository;
import com.jeandelize.cursomc.repositories.ClienteRepository;
import com.jeandelize.cursomc.repositories.EnderecoRepository;
import com.jeandelize.cursomc.services.exceptions.DataIntegrityException;
import com.jeandelize.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Cliente find(Integer id) {
		
		
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	
		
	}
	
	

	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		return repo.save(obj);
	}
	
	
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateDate(newObj, obj);
		return repo.save(newObj);
	}
	
	

	public void delete(Integer id) {
		find(id);
		try {
		repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir porque ha Entidades relacionadas");
		}
	}
	
	public List<Cliente> findAll() {
		return repo.findAll();
	}
	
	
	public Page <Cliente> findPage(int page, int linesPerPage, String orderBy, String direction) {
		     PageRequest pageRequest = PageRequest.of(
             page,
             linesPerPage,
             Sort.Direction.ASC,
             orderBy);
		     
		     return repo.findAll(pageRequest);
	}
	
	
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}
	
	
	public Cliente fromDTO(ClienteNewDTO objDto) {
		Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()));
		Optional<Cidade> cid = cidadeRepository.findById(objDto.getCidadeId());
	    Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid.orElse(null));
	
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDto.getTelefone1());
		if (objDto.getTelefone2()!=null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone3()!=null) {
			cli.getTelefones().add(objDto.getTelefone3());
		}
		return cli;
	}
	
	
	private void updateDate(Cliente newObj, Cliente obj) {
		
	    newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

	
	

}
