package com.lebarreiroj.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lebarreiroj.cursomc.domain.Pedido;
import com.lebarreiroj.cursomc.repositories.PedidoRepository;
import com.lebarreiroj.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	public Pedido buscar(Integer id) {
		Pedido obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrato! id: " + id + ", Tipo: " + Pedido.class.getName());
			
		}
		return obj;
	}
}
