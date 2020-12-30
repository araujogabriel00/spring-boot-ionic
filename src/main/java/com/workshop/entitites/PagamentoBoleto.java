package com.workshop.entitites;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.workshop.enums.EstadoPagamento;

@Entity
@JsonTypeName("PagamentoBoleto")
public class PagamentoBoleto extends Pagamento {

	private static final long serialVersionUID = 1L;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataVencimento;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataPagamento;

	public PagamentoBoleto() {
		
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
