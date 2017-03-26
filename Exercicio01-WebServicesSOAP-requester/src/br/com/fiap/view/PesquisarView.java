package br.com.fiap.view;

import br.com.fiap.bo.ClienteBOStub;
import br.com.fiap.bo.ClienteBOStub.Buscar;
import br.com.fiap.bo.ClienteBOStub.BuscarResponse;
import br.com.fiap.bo.ClienteBOStub.Cliente;

public class PesquisarView {

	public static void main(String[] args) {
		try {
			ClienteBOStub stub = new ClienteBOStub();
			
			Buscar variaveis = new Buscar();
			variaveis.setCodigo(1);
			
			BuscarResponse resposta = stub.buscar(variaveis);
			
			Cliente cli =  resposta.get_return();
			
			System.out.println("Codigo: " + cli.getCodigo() + "\n" +
								"Nome: " + cli.getNome() + "\n" + "Email: " + cli.getEmail() );
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
