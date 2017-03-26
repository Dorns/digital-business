package br.com.fiap.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.fiap.bo.ClienteBOStub;
import br.com.fiap.bo.ClienteBOStub.Cliente;
import br.com.fiap.bo.ClienteBOStub.Listar;
import br.com.fiap.bo.ClienteBOStub.ListarResponse;

public class ListarView {

	public static void main(String[] args) {
		try {
			ClienteBOStub stub = new ClienteBOStub();

			List<Cliente> clientes = new ArrayList<Cliente>();

			Listar variaveis = new Listar();
			variaveis.setCodigo(1);

			ListarResponse resposta = stub.listar(variaveis);

			clientes = Arrays.asList(resposta.get_return());

			for (Cliente cli : clientes) {
				System.out.println("********");
				System.out.println("Codigo: " + cli.getCodigo());
				System.out.println("Nome: " + cli.getNome());
				System.out.println("Email: " + cli.getEmail());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
