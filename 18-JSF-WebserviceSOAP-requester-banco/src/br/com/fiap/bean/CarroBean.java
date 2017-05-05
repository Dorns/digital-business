package br.com.fiap.bean;

import java.rmi.RemoteException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.apache.axis2.AxisFault;

import br.com.fiap.bo.CarroBOStub;
import br.com.fiap.bo.CarroBOStub.Buscar;
import br.com.fiap.bo.CarroBOStub.BuscarResponse;
import br.com.fiap.bo.CarroBOStub.Cadastrar;
import br.com.fiap.bo.CarroBOStub.Carro;

@ManagedBean
public class CarroBean {

	private Carro carro;
	
	private CarroBOStub stub;
	
	@PostConstruct //método de inicialização do Bean
	private void init(){
		carro = new Carro();
		try {
			stub = new CarroBOStub();
		} catch (AxisFault e) {
			e.printStackTrace();
		}
	}
	
	public void buscar(){
		Buscar params = new Buscar();
		params.setCodigo(carro.getCodigo());
		try {
			BuscarResponse response = stub.buscar(params);
			carro = response.get_return();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void cadastrar(){
		FacesMessage msg;
		try {
			Cadastrar params = new Cadastrar();
			params.setCarro(carro);
			stub.cadastrar(params);
			msg = new FacesMessage("Cadastrado!");
		} catch (RemoteException e) {
			e.printStackTrace();
			msg = new FacesMessage("Erro");
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

}