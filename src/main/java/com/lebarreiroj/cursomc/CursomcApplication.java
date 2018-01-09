package com.lebarreiroj.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lebarreiroj.cursomc.domain.Categoria;
import com.lebarreiroj.cursomc.domain.Cidade;
import com.lebarreiroj.cursomc.domain.Cliente;
import com.lebarreiroj.cursomc.domain.Endereco;
import com.lebarreiroj.cursomc.domain.Estado;
import com.lebarreiroj.cursomc.domain.ItemPedido;
import com.lebarreiroj.cursomc.domain.Pagamento;
import com.lebarreiroj.cursomc.domain.PagamentoComBoleto;
import com.lebarreiroj.cursomc.domain.PagamentoComCartao;
import com.lebarreiroj.cursomc.domain.Pedido;
import com.lebarreiroj.cursomc.domain.Produto;
import com.lebarreiroj.cursomc.domain.enums.EstadoPagamento;
import com.lebarreiroj.cursomc.domain.enums.TipoCliente;
import com.lebarreiroj.cursomc.repositories.CategoriaRepository;
import com.lebarreiroj.cursomc.repositories.CidadeRepository;
import com.lebarreiroj.cursomc.repositories.ClienteRepository;
import com.lebarreiroj.cursomc.repositories.EnderecoRepository;
import com.lebarreiroj.cursomc.repositories.EstadoRepository;
import com.lebarreiroj.cursomc.repositories.ItemPedidoRepository;
import com.lebarreiroj.cursomc.repositories.PagamentoRepository;
import com.lebarreiroj.cursomc.repositories.PedidoRepository;
import com.lebarreiroj.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		
		
		
	}
}
