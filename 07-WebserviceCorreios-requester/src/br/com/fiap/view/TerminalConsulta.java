package br.com.fiap.view;

import javax.swing.JOptionPane;

import org.tempuri.CalcPrecoPrazoWSStub;
import org.tempuri.CalcPrecoPrazoWSStub.CalcPrazo;
import org.tempuri.CalcPrecoPrazoWSStub.CalcPrazoResponse;


public class TerminalConsulta {

	public static void main(String[] args) {
		
		try {
			CalcPrecoPrazoWSStub stub = new CalcPrecoPrazoWSStub();
			
			CalcPrazo variaveis = new CalcPrazo();
			
			variaveis.setNCdServico("40010"); //Codigo de serviço sedex
			variaveis.setSCepOrigem("054090010"); 
			variaveis.setSCepDestino("01014915");
			
			CalcPrazoResponse resposta = stub.calcPrazo(variaveis);
			
			JOptionPane.showMessageDialog(null, "O prazo de entrega é de " + resposta.getCalcPrazoResult().getServicos().getCServico()[0].getPrazoEntrega() + " dias");
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
}
