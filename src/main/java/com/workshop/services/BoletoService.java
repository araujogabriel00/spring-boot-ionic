package com.workshop.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.workshop.entitites.PagamentoBoleto;

@Service
public class BoletoService {

	public void preencherPagamentoComBoleto(PagamentoBoleto pgto, Date instante) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instante);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pgto.setDataVencimento(cal.getTime());

	}

}
