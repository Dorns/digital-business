package br.com.fiap.view;

import org.apache.axis2.AxisFault;

import br.com.fiap.bo.EstoqueBOStub;
import br.com.fiap.bo.EstoqueBOStub.ConsultarProduto;
import br.com.fiap.bo.EstoqueBOStub.ConsultarProdutoResponse;
import br.com.fiap.bo.EstoqueBOStub.ProdutoTO;

public class TerminalConsulta {

	public static void main(String[] args) {
		
		try {
			EstoqueBOStub stub = new EstoqueBOStub();
			ConsultarProduto variaveis = new ConsultarProduto();
			variaveis.setCodigo(401);
			
			ConsultarProdutoResponse respota = stub.consultarProduto(variaveis);
			
			ProdutoTO produto = respota.get_return();
			
			System.out.println("Codigo: " + produto.getCodigo() + "\n" +
			"Descrição: " + produto.getDescricao() + "\n" +
					"Preco: " + produto.getPreco()); 
		
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
}
