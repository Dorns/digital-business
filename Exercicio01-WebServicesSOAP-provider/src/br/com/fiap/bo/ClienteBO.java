package br.com.fiap.bo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.fiap.dao.ClienteDAO;
import br.com.fiap.dao.impl.ClienteDAOImpl;
import br.com.fiap.entity.Cliente;
import br.com.fiap.exception.DBException;
import br.com.fiap.factory.EntityManagerFactorySingleton;

public class ClienteBO {
	private EntityManagerFactory fabrica = EntityManagerFactorySingleton.getInstance();
	
	public void cadastrar(Cliente cli){
		EntityManager em = fabrica.createEntityManager();
		ClienteDAO dao = new ClienteDAOImpl(em);
		dao.cadastrar(cli);
		try {
			dao.salvar();
		} catch (DBException e) {
			e.printStackTrace();
		}finally{
			em.close();	
		}
	}
	
	public Cliente buscar(int codigo){
		EntityManager em = fabrica.createEntityManager();
		ClienteDAO dao = new ClienteDAOImpl(em);
		Cliente cli = dao.pesquisar(codigo);
		em.close();
		return cli;
	}
	
	public List<Cliente> listar(int codigo){
		EntityManager em = fabrica.createEntityManager();
		ClienteDAO dao = new ClienteDAOImpl(em);
		List<Cliente> cli = dao.listar();
		em.close();
		return cli;
	}

}
