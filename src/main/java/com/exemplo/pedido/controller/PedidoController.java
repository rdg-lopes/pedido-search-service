package com.exemplo.pedido.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exemplo.pedido.service.ItemPedidoModel;
import com.exemplo.pedido.service.PedidoModel;
import com.exemplo.pedido.service.SearchPedidoService;

@RestController
@RequestMapping("/v1/Pedido")
public class PedidoController {

	@Autowired
	private SearchPedidoService searchPedidoService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PedidoOutDTO> search(@RequestParam(name = "page", required = false, defaultValue = "0") int page, 
							      @RequestParam(name = "size", required = false, defaultValue = "10") int size){
		List<PedidoModel> listModel = this.searchPedidoService.search(page, size);
		return new PedidoMapper().mapToListPedidoOutDto(listModel);
	}
	
	@GetMapping(path = "/{nrPedido}/itens",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ItemPedidoDTO> searchItens(@PathVariable("nrPedido") int nrPedido){
		List<ItemPedidoModel> listModel = this.searchPedidoService.searchItens(nrPedido);
		return new PedidoMapper().mapToListItemDTO(listModel);
	}
	
}
