package br.com.fiap.teste;

import java.util.List;

import br.com.fiap.exception.WebServiceException;
import br.com.fiap.repository.ConsultorRepository;
import br.com.fiap.to.ConsultorTO;

public class Teste {
	public static void main(String[] args) {
		ConsultorRepository rep = new ConsultorRepository();
		
		ConsultorTO consultor = new ConsultorTO();
		consultor.setNome("Felipe");
		consultor.setCpf("43412480819");
		consultor.setTelefone("4623-1947");
		
		try {
			//Cadastra
			rep.cadastrar(consultor);
			System.out.println("Cadastrado!");
			
			//Lista
			List<ConsultorTO> listar = rep.listar();
			for (ConsultorTO c : listar) {
				System.out.println(c.getCodigo() + " " + c.getNome() + " " + c.getCpf() + " " + c.getTelefone());
			}
			
			ConsultorTO consultor2 = listar.get(1);
			consultor2.setNome("Felipe Viel");
			
			//Atualizar
			rep.atualizar(consultor2);
			
			//Buscar
			ConsultorTO consultor3 = rep.buscar(1);
			System.out.println(consultor3.getNome());
			
		} catch (WebServiceException e) {
			e.printStackTrace();
		}
	}
}
