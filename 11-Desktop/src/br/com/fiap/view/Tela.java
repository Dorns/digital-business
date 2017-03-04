package br.com.fiap.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import br.com.fiap.bo.CarroBOStub;
import br.com.fiap.bo.CarroBOStub.Cadastrar;
import br.com.fiap.bo.CarroBOStub.Carro;

public class Tela {

	protected Shell shlCadastrarCarro;
	private Text txtCor;
	private Text txtPlaca;
	private Text txtPreco;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Tela window = new Tela();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlCadastrarCarro.open();
		shlCadastrarCarro.layout();
		while (!shlCadastrarCarro.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlCadastrarCarro = new Shell();
		shlCadastrarCarro.setSize(450, 300);
		shlCadastrarCarro.setText("Cadastrar Carro");
		
		txtCor = new Text(shlCadastrarCarro, SWT.BORDER);
		txtCor.setBounds(52, 46, 76, 21);
		
		txtPlaca = new Text(shlCadastrarCarro, SWT.BORDER);
		txtPlaca.setBounds(52, 103, 76, 21);
		
		txtPreco = new Text(shlCadastrarCarro, SWT.BORDER);
		txtPreco.setBounds(52, 161, 76, 21);
		
		Label lblCor = new Label(shlCadastrarCarro, SWT.NONE);
		lblCor.setBounds(52, 25, 55, 15);
		lblCor.setText("Cor");
		
		Label lblPlaca = new Label(shlCadastrarCarro, SWT.NONE);
		lblPlaca.setBounds(52, 82, 55, 15);
		lblPlaca.setText("Placa");
		
		Label lblPreco = new Label(shlCadastrarCarro, SWT.NONE);
		lblPreco.setBounds(52, 140, 55, 15);
		lblPreco.setText("Pre\u00E7o");
		
		Button btnCadastrar = new Button(shlCadastrarCarro, SWT.NONE);
		btnCadastrar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//Recuperar Valores do text
				String cor = txtCor.getText();
				String placa = txtPlaca.getText();
				float preco = Float.parseFloat(txtPreco.getText());
										
				//Criar um carro
				try {
					CarroBOStub stub = new CarroBOStub();
					Carro carro = new Carro();
					carro.setCor(cor);
					carro.setPlaca(placa);
					carro.setPreco(preco);
										
					Cadastrar params = new Cadastrar();
					params.setCarro(carro);
					
					//Chamar o webservice
					stub.cadastrar(params);
				} catch (Exception e1) {					
					e1.printStackTrace();
				}	
				
			}
		});
		btnCadastrar.setBounds(256, 101, 75, 25);
		btnCadastrar.setText("Cadastrar");

	}
}
