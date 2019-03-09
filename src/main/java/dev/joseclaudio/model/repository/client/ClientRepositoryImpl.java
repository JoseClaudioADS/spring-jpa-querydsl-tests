package dev.joseclaudio.model.repository.client;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class ClientRepositoryImpl {

	@PersistenceContext
	private EntityManager em;

}
