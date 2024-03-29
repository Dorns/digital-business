package br.com.fiap.bo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.fiap.dao.ConsultorDAO;
import br.com.fiap.dao.impl.ConsultorDAOImpl;
import br.com.fiap.entity.Consultor;
import br.com.fiap.exception.DBException;
import br.com.fiap.exception.IdNotFoundException;
import br.com.fiap.factory.EntityManagerFactorySingleton;

public class ConsultorBO {
	private EntityManagerFactory fabrica = EntityManagerFactorySingleton.getInstance();

	public void cadastrar(Consultor consultor) {
		EntityManager em = fabrica.createEntityManager();
		ConsultorDAO dao = new ConsultorDAOImpl(em);
		dao.cadastrar(consultor);
		try {
			dao.salvar();
		} catch (DBException e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
	
	public List<Consultor> listar(){
		EntityManager em = fabrica.createEntityManager();
		ConsultorDAO dao = new ConsultorDAOImpl(em);
		List<Consultor> lista = dao.listar();
		em.close();
		return lista;
	}

	public Consultor buscar(int codigo) {
		EntityManager em = fabrica.createEntityManager();
		ConsultorDAO dao = new ConsultorDAOImpl(em);
		Consultor con = dao.pesquisar(codigo);
		em.close();
		return con;
	}

	public void alterar(Consultor consultor){
		EntityManager em = fabrica.createEntityManager();
		ConsultorDAO dao = new ConsultorDAOImpl(em);
		dao.alterar(consultor);
		try {
			dao.salvar();
		} catch (DBException e){
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
	
	public void remover(int codigo) throws IdNotFoundException{
		EntityManager em = fabrica.createEntityManager();
		ConsultorDAO dao = new ConsultorDAOImpl(em);
		dao.remover(codigo);
		try {
			dao.salvar();
		} catch (DBException e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
}
