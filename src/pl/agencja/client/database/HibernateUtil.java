package pl.agencja.client.database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil
{
	public static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myAgency");
	public static EntityManager entityManager = entityManagerFactory.createEntityManager();

	public static void close()
	{
		entityManager.close();
		entityManagerFactory.close();
	}

}