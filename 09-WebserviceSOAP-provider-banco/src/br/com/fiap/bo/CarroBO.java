package br.com.fiap.bo;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.fiap.dao.CarroDAO;
import br.com.fiap.dao.impl.CarroDAOImpl;
import br.com.fiap.entity.Carro;
import br.com.fiap.exception.DBException;
import br.com.fiap.factory.EntityManagerFactorySingleton;

public class CarroBO {
	private EntityManagerFactory fabrica = EntityManagerFactorySingleton.getInstance();
	
	public void cadastrar (Carro carro){ 
		EntityManager em = fabrica.createEntityManager();
		CarroDAO dao = new CarroDAOImpl(em);
		dao.cadastrar(carro);
		try {
			dao.salvar();
		} catch (DBException e) {
			e.printStackTrace();
		}finally{
			em.close();	
		}
	}
	
	public Carro buscar(int codigo){
		EntityManager em = fabrica.createEntityManager();
		CarroDAO dao = new CarroDAOImpl(em);
		Carro car = dao.pesquisar(codigo);
		em.close();
		return car;
	}
}
