package com.lebarreiroj.cursomc.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lebarreiroj.cursomc.domain.ItemPedido;
import com.lebarreiroj.cursomc.domain.PagamentoComBoleto;
import com.lebarreiroj.cursomc.domain.Pedido;
import com.lebarreiroj.cursomc.domain.enums.EstadoPagamento;
import com.lebarreiroj.cursomc.repositories.ItemPedidoRepository;
import com.lebarreiroj.cursomc.repositories.PagamentoRepository;
import com.lebarreiroj.cursomc.repositories.PedidoRepository;
import com.lebarreiroj.cursomc.repositories.ProdutoRepository;
import com.lebarreiroj.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;

	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public Pedido find(Integer id) {
		Pedido obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrato! id: " + id + ", Tipo: " + Pedido.class.getName());
			
		}
		return obj;
	}
	
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setPreco(produtoRepository.findOne(ip.getProduto().getId()).getPreco());
			ip.setPedido(obj);
					
		}
		
		itemPedidoRepository.save(obj.getItens());
		return obj;
	}
	
}
