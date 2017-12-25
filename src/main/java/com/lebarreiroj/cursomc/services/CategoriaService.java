package com.lebarreiroj.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lebarreiroj.cursomc.domain.Categoria;
import com.lebarreiroj.cursomc.repositories.CategoriaRepository;
import com.lebarreiroj.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
		Categoria obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrato! id: " + id + ", Tipo: " + Categoria.class.getName());
			
		}
		return obj;
	}
	
	public Categoria insert (Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
				
	}
	
	public Categoria update (Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
				
	}
}
