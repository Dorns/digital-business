package br.com.fiap.bean;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.fiap.bo.ConsultorBO;
import br.com.fiap.entity.Consultor;
import br.com.fiap.exception.DBException;

@ManagedBean
public class ConsultorBean {

	private Consultor consultor;

	private ConsultorBO bo;

	@PostConstruct // Inicializar os objetos
	private void iniciar() {
		consultor = new Consultor();
		bo = new ConsultorBO();
	}

	public String cadastrar() {
		FacesMessage msg;
		try {
			if (consultor.getCodigo() == 0) {
				bo.cadastrar(consultor);
				msg = new FacesMessage("Cadastrado com sucesso!");
			} else {
				bo.atualizar(consultor);
				msg = new FacesMessage("Alterado com sucesso!");
			}
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
		} catch (DBException e) {
			e.printStackTrace();
			msg = new FacesMessage("Erro!");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			return "consultor";
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
		// Manter a mensagem após o redirect (nova requisição)
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		return "lista-consultor?faces-redirect=true";
	}

	public Consultor getConsultor() {
		return consultor;
	}

	public void setConsultor(Consultor consultor) {
		this.consultor = consultor;
	}

}