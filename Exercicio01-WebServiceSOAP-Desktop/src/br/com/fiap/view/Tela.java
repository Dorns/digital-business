package br.com.fiap.view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import br.com.fiap.bo.ClienteBOStub;
import br.com.fiap.bo.ClienteBOStub.Buscar;
import br.com.fiap.bo.ClienteBOStub.BuscarResponse;
import br.com.fiap.bo.ClienteBOStub.Cadastrar;
import br.com.fiap.bo.ClienteBOStub.Cliente;
import br.com.fiap.bo.ClienteBOStub.Listar;
import br.com.fiap.bo.ClienteBOStub.ListarResponse;

import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class Tela extends ApplicationWindow {
	private Text txtCdigo;
	private Text txtNome;
	private Text txtEmail;
	private Table tblCliente;

	/**
	 * Create the application window.
	 */
	public Tela() {
		super(null);
		createActions();
		addToolBar(SWT.FLAT | SWT.WRAP);
		addMenuBar();
		addStatusLine();
	}

	/**
	 * Create contents of the application window.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createContents(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);

		Label lblNome = new Label(container, SWT.NONE);
		lblNome.setBounds(10, 46, 55, 15);
		lblNome.setText("Nome");

		Label lblCdigo = new Label(container, SWT.NONE);
		lblCdigo.setBounds(10, 10, 55, 15);
		lblCdigo.setText("C\u00F3digo");

		Label lblDataDeNascimento = new Label(container, SWT.NONE);
		lblDataDeNascimento.setBounds(10, 85, 140, 15);
		lblDataDeNascimento.setText("Data de Nascimento");

		Label lblEmail = new Label(container, SWT.NONE);
		lblEmail.setBounds(10, 120, 55, 15);
		lblEmail.setText("E-mail");

		txtCdigo = new Text(container, SWT.BORDER);
		txtCdigo.setBounds(148, 4, 140, 21);

		txtNome = new Text(container, SWT.BORDER);
		txtNome.setBounds(148, 40, 140, 21);

		txtEmail = new Text(container, SWT.BORDER);
		txtEmail.setBounds(148, 120, 140, 21);

		DateTime dtNascimento = new DateTime(container, SWT.BORDER);
		dtNascimento.setBounds(148, 76, 140, 24);

		Button btnCadastrar = new Button(container, SWT.NONE);
		btnCadastrar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Cliente cli = new Cliente();
				cli.setCodigo(Integer.parseInt(txtCdigo.getText()));
				cli.setNome(txtNome.getText());
				cli.setEmail(txtEmail.getText());
				cli.setDataNascimento(new java.util.GregorianCalendar(dtNascimento.getYear(), dtNascimento.getMonth(),
						dtNascimento.getDay()));

				try {
					ClienteBOStub stub = new ClienteBOStub();
					Cadastrar valores = new Cadastrar();
					valores.setCli(cli);
					stub.cadastrar(valores);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnCadastrar.setBounds(367, 5, 75, 25);
		btnCadastrar.setText("Cadastrar");

		Button btnPesquisar = new Button(container, SWT.NONE);
		btnPesquisar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					ClienteBOStub stub = new ClienteBOStub();

					Buscar buscar = new Buscar();

					buscar.setCodigo(Integer.parseInt(txtCdigo.getText()));

					BuscarResponse resposta = stub.buscar(buscar);

					if (resposta.get_return() != null) {

						txtNome.setText(resposta.get_return().getNome());

						txtEmail.setText(resposta.get_return().getEmail());

						Calendar calendario = resposta.get_return().getDataNascimento();

						dtNascimento.setDay(calendario.get(Calendar.DAY_OF_MONTH));
						dtNascimento.setMonth(calendario.get(Calendar.MONTH));
						dtNascimento.setYear(calendario.get(Calendar.YEAR));
					} else {
						txtCdigo.setText("Não encontrado");

						txtNome.setText("Não encontrado");

						txtEmail.setText("Não encontrado");
					}

				} catch (Exception e1) {

					e1.printStackTrace();
				}
			}
		});
		btnPesquisar.setBounds(367, 58, 75, 25);
		btnPesquisar.setText("Pesquisar");

		tblCliente = new Table(container, SWT.BORDER | SWT.FULL_SELECTION);
		tblCliente.setBounds(10, 189, 714, 193);
		tblCliente.setHeaderVisible(true);
		tblCliente.setLinesVisible(true);

		TableColumn tblclmnCodigo = new TableColumn(tblCliente, SWT.NONE);
		tblclmnCodigo.setWidth(100);
		tblclmnCodigo.setText("Codigo");

		TableColumn tblclmnNome = new TableColumn(tblCliente, SWT.NONE);
		tblclmnNome.setWidth(159);
		tblclmnNome.setText("Nome");

		TableColumn tblclmnDataDeNascimento = new TableColumn(tblCliente, SWT.NONE);
		tblclmnDataDeNascimento.setWidth(253);
		tblclmnDataDeNascimento.setText("Data de Nascimento");

		TableColumn tblclmnEmail = new TableColumn(tblCliente, SWT.NONE);
		tblclmnEmail.setWidth(199);
		tblclmnEmail.setText("Email");

		Button btnListar = new Button(container, SWT.NONE);
		btnListar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

				tblCliente.removeAll(); // Limpa a tabela

				try {
					ClienteBOStub stub = new ClienteBOStub();

					List<Cliente> clientes = new ArrayList<Cliente>();

					Listar variaveis = new Listar();
					variaveis.setCodigo(1);

					ListarResponse resposta = stub.listar(variaveis);

					clientes = Arrays.asList(resposta.get_return());

					for (Cliente cliente : clientes) {
						TableItem item1 = new TableItem(tblCliente, SWT.NONE);
						item1.setText(new String[] { "" + cliente.getCodigo(), cliente.getNome(), cliente.getEmail(),
								sdf.format(cliente.getDataNascimento().getTime()) });
					}

				} catch (Exception e3) {

					e3.printStackTrace();
				}
			}
		});
		btnListar.setBounds(367, 120, 75, 25);
		btnListar.setText("Listar");

		return container;
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Create the status line manager.
	 * 
	 * @return the status line manager
	 */
	@Override
	protected StatusLineManager createStatusLineManager() {
		StatusLineManager statusLineManager = new StatusLineManager();
		return statusLineManager;
	}

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Tela window = new Tela();
			window.setBlockOnOpen(true);
			window.open();
			Display.getCurrent().dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Configure the shell.
	 * 
	 * @param newShell
	 */
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("New Application");
	}

	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(816, 481);
	}
}
