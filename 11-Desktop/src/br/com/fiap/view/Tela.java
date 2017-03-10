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
import br.com.fiap.bo.CarroBOStub.Buscar;
import br.com.fiap.bo.CarroBOStub.BuscarResponse;
import br.com.fiap.bo.CarroBOStub.Cadastrar;
import br.com.fiap.bo.CarroBOStub.Carro;

public class Tela {

	protected Shell shlCadastrarCarro;
	private Text txtCor;
	private Text txtPlaca;
	private Text txtPreco;
	private Text txtCodigo;

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
		btnCadastrar.setBounds(52, 205, 75, 25);
		btnCadastrar.setText("Cadastrar");
		
		Label lblCdigo = new Label(shlCadastrarCarro, SWT.NONE);
		lblCdigo.setBounds(251, 25, 55, 15);
		lblCdigo.setText("C\u00F3digo");
		
		txtCodigo = new Text(shlCadastrarCarro, SWT.BORDER);
		txtCodigo.setBounds(251, 46, 76, 21);
		
		Button btnBuscar = new Button(shlCadastrarCarro, SWT.NONE);
		btnBuscar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					//Instanciar o Stub
					CarroBOStub stub = new CarroBOStub();
				
					//Recuperar o id que o usuario digitou
					int codigo = Integer.parseInt(txtCodigo.getText());
				
					//Instanciar a classe (nome do método)
					Buscar params = new Buscar();	
					params.setCodigo(codigo);
					
					//Chamar o webservice aravés do stub
					BuscarResponse resp = stub.buscar(params);
									
					//Recuperar o valor retornado pelo webservice
					
					Carro car = resp.get_return();
					
					if (resp.get_return() != null){
						//Exibir o resultado
						txtCor.setText(car.getCor());
						txtPlaca.setText(car.getPlaca());
						txtPreco.setText(Float.toString(car.getPreco()));			
					}else{
						txtCor.setText("Not Found");
						txtPlaca.setText("Not Found");
						txtPreco.setText("Not Found");
					}
					

				} catch (Exception e2) {
					e2.printStackTrace();
				}

			}
		});
		btnBuscar.setBounds(252, 82, 75, 25);
		btnBuscar.setText("Buscar");

	}
}
