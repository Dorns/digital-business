package br.com.fiap.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import br.com.fiap.bo.ConsultorBO;
import br.com.fiap.entity.Consultor;
import br.com.fiap.exception.IdNotFoundException;

@Path("/consultor")
public class ConsultorResource {
	private ConsultorBO bo = new ConsultorBO();
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)	
	public Consultor buscar(@PathParam("id") int codigo){
		Consultor con = bo.buscar(codigo);
		return con;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Consultor> listar(){
		return bo.listar();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrar(Consultor con, @Context UriInfo uriInfo){
		bo.cadastrar(con);
		//Construir a URL para acessar o produto cadastrado
		UriBuilder url = UriBuilder.fromPath(uriInfo.getPath());
		url.path(String.valueOf(con.getCodigo()));
		//Status HTTP 201 (Created)
		return Response.created(url.build()).build();
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizar(Consultor con, @PathParam("id") int codigo){
		con.setCodigo(codigo);
		bo.alterar(con);
		return Response.ok().build();
	}
	
	@DELETE
	@Path("/{id}")
	public void remover(@PathParam("id") int codigo) throws IdNotFoundException{
		bo.remover(codigo);
	}		
}