package br.com.fiap.dao.impl;

import javax.persistence.EntityManager;

import br.com.fiap.dao.CarroDAO;
import br.com.fiap.entity.Carro;

public class CarroDAOImpl extends GenericDAOImpl<Carro, Integer> implements CarroDAO{

	public CarroDAOImpl(EntityManager em) {
		super(em);
	}

}
