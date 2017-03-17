package br.com.fiap.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.fiap.bo.ConsultorBO;
import br.com.fiap.entity.Consultor;

public class ConsultorResource {
	private ConsultorBO bo = new ConsultorBO();
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)	
	public Consultor buscar(@PathParam("id") int codigo){
		Consultor con = bo.buscar(codigo);
		return con;
	}
}