package com.workshop.entitites;

import javax.persistence.Entity;

import com.workshop.enums.EstadoPagamento;
@Entity
public class PagamentoCartão extends Pagamento {

	private static final long serialVersionUID = 1L;

	private Integer numeroDeParcelas;

	public PagamentoCartão() {

	}

	public PagamentoCartão(Integer id, EstadoPagamento estadopagamento, Pedido pedido, Integer numeroDeParcelas) {
		super(id, estadopagamento, pedido);
		this.numeroDeParcelas = numeroDeParcelas;

	}

	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}

	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}

}
