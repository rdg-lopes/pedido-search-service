package com.exemplo.pedido.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.exemplo.pedido.repository.ItemPedidoEntity;
import com.exemplo.pedido.repository.PedidoEntity;
import com.exemplo.pedido.repository.PedidoRepository;

@Service
public class SearchPedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	public List<PedidoModel> search(int page, int size){
		Pageable pag = PageRequest.of(page, size);
		Page<PedidoEntity> pageEntity = this.pedidoRepository.findAll(pag);
		return new PedidoMapper().mapToListPedidoModel(pageEntity.getContent());
	}
	
	public List<ItemPedidoModel> searchItens(int nrPedido){
		PedidoEntity pedido = this.pedidoRepository.getById(nrPedido);
		if(Objects.isNull(pedido)) {
			throw new NrPedidoNaoExisteExeption(nrPedido);
		}
		List<ItemPedidoEntity> itensResult = pedido.getItens();
		return new PedidoMapper().mapToListItemModel(itensResult);
	}
	
}
