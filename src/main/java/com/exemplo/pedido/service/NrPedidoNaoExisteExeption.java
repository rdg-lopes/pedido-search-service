package com.exemplo.pedido.service;

public class NrPedidoNaoExisteExeption extends ApplicationException {

	private static final long serialVersionUID = 1L;

	private Integer nrPedido;
	
	public NrPedidoNaoExisteExeption(Integer nrPedido) {
		this.nrPedido = nrPedido;
	}

	@Override
	public String getMessage() {
		return "Não foi encontrato um pedido com o número "+this.nrPedido;
	}
}
