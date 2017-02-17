package br.com.fiap.view;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

import br.com.fiap.bo.EstoqueBO;
import br.com.fiap.config.PropertySingleton;
import br.com.fiap.to.ProdutoTO;

/***
 * @author Thiago Yamamoto
 * @version 1.0
 */
public class Console {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		//Data para o laoyout da aplica��o
		Calendar dataHoje = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		//Ler a configura��o do nome da loja
		String loja = PropertySingleton
						.getInstance().getProperty("empresa");
		
		System.out.println(loja + "          " + sdf.format(dataHoje.getTime()));
		System.out.println("******************************");
		System.out.print("Digite o c�digo:");
		//L� o c�digo inserido pelo usu�rio
		int codigo = scanner.nextInt();
		
		EstoqueBO bo = new EstoqueBO();
		ProdutoTO produto = bo.consultarProduto(codigo);
		
		if (produto != null){
			System.out.println(produto.getDescricao());
			System.out.println(produto.getQuantidade());
			
			//Formatar valores monet�rios
			DecimalFormat format = new DecimalFormat("R$ #,###.00");
			System.out.println(format.format(produto.getPreco()));
		}else{
			System.out.println("Produto n�o cadastrado!");
		}
		scanner.close();
	}
	
}
