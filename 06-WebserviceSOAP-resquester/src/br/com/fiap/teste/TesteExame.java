package br.com.fiap.teste;

import br.com.fiap.bo.CursoBOStub;
import br.com.fiap.bo.CursoBOStub.CalcularExame;
import br.com.fiap.bo.CursoBOStub.CalcularExameResponse;

public class TesteExame {

	public static void main(String[] args) {
		try {
			CursoBOStub stub = new CursoBOStub();

			// Valores que serao enviados para o webservice
			CalcularExame valores = new CalcularExame();
			valores.setExame(7);
			valores.setMedia(5);

			// Chama o webservice
			CalcularExameResponse response = stub.calcularExame(valores);

			System.out.println(response.get_return());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
