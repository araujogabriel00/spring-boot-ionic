package com.workshop.entitites;

import java.util.Date;

import com.workshop.enums.EstadoPagamento;

public class PagamentoBoleto extends Pagamento {

	private static final long serialVersionUID = 1L;

	private Date dataVencimento;
	private Date dataPagamento;

	public PagamentoBoleto() {
		// TODO Auto-generated constructor stub
	}

	public PagamentoBoleto(Integer id, EstadoPagamento estadopagamento, Pedido pedido, Date dataVencimento,
			Date dataPagamento) {
		super(id, estadopagamento, pedido);
		this.dataPagamento = dataPagamento;
		this.dataVencimento = dataVencimento;

	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

}
