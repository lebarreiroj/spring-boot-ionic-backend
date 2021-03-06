package com.lebarreiroj.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lebarreiroj.cursomc.domain.Cidade;
import com.lebarreiroj.cursomc.domain.Cliente;
import com.lebarreiroj.cursomc.domain.Endereco;
import com.lebarreiroj.cursomc.domain.enums.TipoCliente;
import com.lebarreiroj.cursomc.dto.ClienteDTO;
import com.lebarreiroj.cursomc.dto.ClienteNewDTO;
import com.lebarreiroj.cursomc.repositories.CidadeRepository;
import com.lebarreiroj.cursomc.repositories.ClienteRepository;
import com.lebarreiroj.cursomc.repositories.EnderecoRepository;
import com.lebarreiroj.cursomc.resources.exceptions.DataIntegrityException;
import com.lebarreiroj.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private ClienteRepository repo;
	
	@Autowired 
	private CidadeRepository cidadeRepository;
	
	@Autowired 
	private EnderecoRepository enderecoRepository;	
	
	public Cliente find(Integer id) {
		Cliente obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrato! id: " + id + ", Tipo: " + Cliente.class.getName());
			
		}
		return obj;
	}
	
	public Cliente insert (Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.save(obj.getEnderecos());
		return obj;
				
	}
	
	public Cliente update (Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
				
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.delete(id);
			
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um Cliente porque há pedidos relacionadas.");
																																																																															 
		}
		
	}
	
	public List<Cliente> findAll() {
		return repo.findAll();
		
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null, null);
	}
	
	public Cliente fromDTO(ClienteNewDTO objDto) {
		Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()), pe.encode(objDto.getSenha()));
		Cidade cid = cidadeRepository.findOne(objDto.getCidadeId());
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDto.getTelefone1());
		if (objDto.getTelefone2()!=null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone3()!=null) {
			cli.getTelefones().add(objDto.getTelefone3());
		}
		return cli;
		
	}
	
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
		
	}
	
}
