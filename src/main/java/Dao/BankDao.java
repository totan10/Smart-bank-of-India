package Dao;
import Dto.BankAccount;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import Dto.BankAccount;

public class BankDao {
	EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory(null);
	EntityManager entityManager=entityManagerFactory.createEntityManager();
	EntityTransaction entityTransaction=entityManager.getTransaction();
	
	public void save_account(BankAccount bankAccount) {
		entityTransaction.begin();
		entityManager.persist(bankAccount);
		entityTransaction.commit();
		
	}

	public List<BankAccount> fetchAll() {
		List<BankAccount> list=entityManager.createQuery("select a from BankAccount a").getResultList();
		return list;
	}

	public BankAccount fetch_account_details(long accno) {
		BankAccount bankAccount=entityManager.find(BankAccount.class,accno);
		return bankAccount;
	}

	public void update_the_datails(BankAccount bankAccount) {
		entityTransaction.begin();
		entityManager.merge(bankAccount);
		entityTransaction.commit();
		
	}

}
