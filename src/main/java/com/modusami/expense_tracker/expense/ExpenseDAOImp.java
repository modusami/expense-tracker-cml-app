package com.modusami.expense_tracker.expense;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

/**
 * Represents an Expense Data Access Object Implementation
 * @author Michael-Andre Odusami
 * @version 2024-05
 */

@Repository
@Primary
public class ExpenseDAOImp implements ExpenseDAO {
	
	// add entity manager
	private EntityManager entityManager;
	
	// add constructor
	public ExpenseDAOImp(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	@Transactional
	public void save(Expense expense) {
		entityManager.persist(expense);
	}

	@Override
	public Expense findById(Integer id) {
		Expense expense = entityManager.find(Expense.class, id);
		return expense;
	}

	@Override
	public List<Expense> findAll() {
		// create query 
		TypedQuery<Expense> theQuery = entityManager.createQuery("FROM Expense", Expense.class);
		
		// return the query list
		return theQuery.getResultList();
	}

	@Override
	public List<Expense> findByCategory(ExpenseCategory theExpenseCategory) {
		TypedQuery<Expense> theQuery = entityManager.createQuery("FROM Expense WHERE category=:theCategory", Expense.class);
		theQuery.setParameter("theCategory", theExpenseCategory);
		return theQuery.getResultList();
	}

	@Override
	public List<Expense> findByPaymentMethod(ExpensePaymentMethod thePaymentMethod) {
		TypedQuery<Expense> theQuery = entityManager.createQuery("FROM Expense WHERE paymentMethod=:thePMethod", Expense.class);
		theQuery.setParameter("thePMethod", thePaymentMethod);
		return theQuery.getResultList();
	}

	@Override
	@Transactional
	public void update(Expense expense) {
		entityManager.merge(expense);
	}

	@Override
	@Transactional
	public void deleteByid(Integer id) {
		Expense theExpense = findById(id);
		entityManager.remove(theExpense);
	}

}
