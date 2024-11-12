package iuh.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerFactoryUtils {
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;

	public EntityManagerFactoryUtils() {
		entityManagerFactory = Persistence.createEntityManagerFactory("mobiles-management");
		entityManager = entityManagerFactory.createEntityManager();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
	public void close() {
		entityManagerFactory.close();
		entityManager.close();
	}

}
