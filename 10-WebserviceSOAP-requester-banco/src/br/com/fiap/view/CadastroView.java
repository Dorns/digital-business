package br.com.fiap.view;

import javax.swing.JOptionPane;

import br.com.fiap.bo.CarroBOStub;
import br.com.fiap.bo.CarroBOStub.Cadastrar;
import br.com.fiap.bo.CarroBOStub.Carro;

public class CadastroView {

	public static void main(String[] args) {
		try {
			CarroBOStub stub = new CarroBOStub();
			
			//Cria o objeto carro
			Carro carro = new Carro();
			carro.setCor(JOptionPane.showInputDialog("Cor do carro"));
			carro.setPlaca(JOptionPane.showInputDialog("Placa"));
			carro.setPreco(Float.parseFloat(JOptionPane.showInputDialog("Preço")));
			
			//Cria o objeto que envia valores para o ws
			Cadastrar params = new Cadastrar();
			params.setCarro(carro);

			//Chama o webservice
			stub.cadastrar(params);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
