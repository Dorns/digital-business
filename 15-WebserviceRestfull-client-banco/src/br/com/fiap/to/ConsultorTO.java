package br.com.fiap.to;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ConsultorTO {
	private int codigo;

	private String nome;

	private String cpf;

	private String telefone;

	public ConsultorTO(int codigo, String nome, String cpf, String telefone) {
		super();
		this.codigo = codigo; 
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
	}

	public ConsultorTO() {
		super();
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
}
