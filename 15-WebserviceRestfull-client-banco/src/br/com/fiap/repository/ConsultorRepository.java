package br.com.fiap.repository;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import br.com.fiap.exception.WebServiceException;
import br.com.fiap.to.ConsultorTO;

public class ConsultorRepository {

	private Client client = Client.create();
	
	private static final String URL = "http://localhost:8080/13-WebserviceRestfull-server-banco/rest/consultor";
	
	public List<ConsultorTO> listar() throws WebServiceException {
		
		WebResource resource = client.resource(URL);
		
		// Chamada ao Webservice
		// accept -> receber JSON
		ClientResponse response = resource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		
		// Valida o HTTP Status
		if(response.getStatus() != 200) {
			throw new WebServiceException("HTTP Status: " + response.getStatus());
		}
		
		// Recuperar as informações do webservice
		ConsultorTO[] array = response.getEntity(ConsultorTO[].class);
		
		// Converte o array em List
		return Arrays.asList(array);
	}
	
	public ConsultorTO buscar(int id) throws WebServiceException {
		
		WebResource resource = client.resource(URL + id);
		ClientResponse response = resource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		
		if(response.getStatus() != 200) {
			throw new WebServiceException("HTTP Status: " + response.getStatus());
		}
		
		return response.getEntity(ConsultorTO.class);
	}
	
	public void cadastrar(ConsultorTO consultor) throws WebServiceException {
		
		WebResource resource = client.resource(URL);
		ClientResponse response = resource
				.type(MediaType.APPLICATION_JSON)
				.post(ClientResponse.class, consultor);
		
		if(response.getStatus() != 201) {
			throw new WebServiceException("HTTP Status: " 
							+ response.getStatus());
		}
		
	}
	
	public void atualizar(ConsultorTO consultor) throws WebServiceException {
		
		WebResource resource = client.resource(URL 
									+ consultor.getCodigo());
		ClientResponse response = resource.
				type(MediaType.APPLICATION_JSON).
				put(ClientResponse.class, consultor);
		
		if(response.getStatus() != 200) {
			throw new WebServiceException("HTTP Status: "
								+ response.getStatus());
		}
		
	}
	
	public void deletar(int id) throws WebServiceException {
		
		WebResource resource = client.resource(URL + id);
		ClientResponse response = resource
				.delete(ClientResponse.class);
		
		if(response.getStatus() != 204) {
			throw new WebServiceException("HTTP Status: "
								+ response.getStatus());
		}
		
	}
	
}
