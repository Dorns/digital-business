package br.com.fiap.view;

import java.util.Calendar;
import java.util.GregorianCalendar;

import br.com.fiap.bo.ClienteBOStub;
import br.com.fiap.bo.ClienteBOStub.Cadastrar;
import br.com.fiap.bo.ClienteBOStub.Cliente;

public class CadastroView {

	public static void main(String[] args) {
		try {
			ClienteBOStub stub = new ClienteBOStub();

			// Cria o objeto cliente
			Cliente cliente = new Cliente();
			cliente.setNome("Felipe Grigoli Pereira");
			cliente.setDataNascimento(new GregorianCalendar(1995, Calendar.JANUARY, 10));
			cliente.setEmail("grigoli009@gmail.com");
			
			//Cria o objeto que envia valores para o ws
			Cadastrar params = new Cadastrar();
			params.setCli(cliente);
			
			//Chama o webservice
			stub.cadastrar(params);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
