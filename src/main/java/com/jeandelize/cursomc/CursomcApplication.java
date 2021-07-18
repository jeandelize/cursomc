package com.jeandelize.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jeandelize.cursomc.domain.Categoria;
import com.jeandelize.cursomc.domain.Cidade;
import com.jeandelize.cursomc.domain.Cliente;
import com.jeandelize.cursomc.domain.Endereco;
import com.jeandelize.cursomc.domain.Estado;
import com.jeandelize.cursomc.domain.ItemPedido;
import com.jeandelize.cursomc.domain.Pagamento;
import com.jeandelize.cursomc.domain.PagamentoComBoleto;
import com.jeandelize.cursomc.domain.PagamentoComCartao;
import com.jeandelize.cursomc.domain.Pedido;
import com.jeandelize.cursomc.domain.Produto;
import com.jeandelize.cursomc.domain.enums.EstadoPagamento;
import com.jeandelize.cursomc.domain.enums.TipoCliente;
import com.jeandelize.cursomc.repositories.CategoriaRepository;
import com.jeandelize.cursomc.repositories.CidadeRepository;
import com.jeandelize.cursomc.repositories.ClienteRepository;
import com.jeandelize.cursomc.repositories.EnderecoRepository;
import com.jeandelize.cursomc.repositories.EstadoRepository;
import com.jeandelize.cursomc.repositories.ItemPedidoRepository;
import com.jeandelize.cursomc.repositories.PagamentoRepository;
import com.jeandelize.cursomc.repositories.PedidoRepository;
import com.jeandelize.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	CategoriaRepository categoriaRepository;
	@Autowired
	ProdutoRepository produtoRepository;
	@Autowired
	EstadoRepository estadoRepository;
	@Autowired
	CidadeRepository cidadeRepository;
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	EnderecoRepository enderecoRepository;
	@Autowired
	PedidoRepository pedidoRepository;
	@Autowired
	PagamentoRepository pagamentoRepository;
	@Autowired 
	ItemPedidoRepository itemPedidoRepository;
	

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria("Informática");
		Categoria cat2 = new Categoria("Escritório");

		Produto p1 = new Produto("Computador", 2000.00);
		Produto p2 = new Produto("Impressora", 800.00);
		Produto p3 = new Produto("Mouse", 80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));

		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null,"Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null,"Uberlandia", est1);
		Cidade c2 = new Cidade(null,"Campinas", est2);
		Cidade c3 = new Cidade(null,"São Paulo", est2);
		
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		c1.setEstado(est1);
		c2.setEstado(est2);
		c3.setEstado(est2);
		
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		
		Cliente cli = new Cliente(null,"Joao da Silva","joao@teste.com.br","12345689833",TipoCliente.PESSOAFISICA);
		
		cli.getTelefones().addAll(Arrays.asList("934564589","984567898"));
		
		Endereco e1 = new Endereco(null,"Rua Flores","300","apto 200","Jardins","05417000", cli,c1);
		Endereco e2 = new Endereco(null,"Rua Matos","400","sala 300","Centro","58735452", cli,c2);
			
		cli.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		
		clienteRepository.saveAll(Arrays.asList(cli));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli, e2);

		Pagamento pagto1 = new PagamentoComCartao(null,EstadoPagamento.QUITADO,ped1,6);
		ped1.setPagamento(pagto1);
		
		
		Pagamento pagto2 = new PagamentoComBoleto(null,EstadoPagamento.PENDENTE,ped2, sdf.parse("20/07/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		
		cli.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1,p1,0.00,1,2000.00);
		ItemPedido ip2 = new ItemPedido(ped1,p3,0.00,2,80.00);
		ItemPedido ip3 = new ItemPedido(ped2,p3,100.00,1,80.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
		
		
	}

}
