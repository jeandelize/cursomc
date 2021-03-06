package com.jeandelize.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.jeandelize.cursomc.domain.Cliente;
import com.jeandelize.cursomc.services.validation.ClienteUpdate;

@ClienteUpdate
public class ClienteDTO  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private Integer id;
	
	@NotEmpty(message="Preenchimento Obrigatório")
	@Size(min=5, max=120, message="Tamanho deve ser entre 5 e 80 caracteres")
	private String nome;
	
	@NotEmpty(message="Preenchimento Obrigatório")
	@Email(message="Email inválido")
	private String email;
	
	
	public ClienteDTO() {
	}
	
	
	public ClienteDTO(Cliente obj) {
	     this.id = obj.getId();
	     this.nome = obj.getNome();
	     this.email = obj.getEmail();
		
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
 
}
