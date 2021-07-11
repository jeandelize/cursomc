package com.jeandelize.cursomc.domain.enums;

public enum EstadoPagamento {

	
	PENDENTE(0,"Pedente"),
	QUITADO(1, "Quitado"),
	CANCELADO(1, "Cancelado");
	
	private int cod;
	private String descricao;
	
	
	private EstadoPagamento(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		 return cod;
		
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	public static EstadoPagamento toEnum(Integer cod) {
		
		if ( cod == null) {
			return null;
		}
		
		for (EstadoPagamento x : EstadoPagamento.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id Inválido " + cod );
		
	}
	
}
