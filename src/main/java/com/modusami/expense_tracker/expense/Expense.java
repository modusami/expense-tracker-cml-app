package com.modusami.expense_tracker.expense;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Represents an Expense
 * @author Michael-Andre Odusami
 * @version 2024-05
 */
@Entity
@Table(name = "expense")
public class Expense {
	
	// define attributes
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "title")
	private String title;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "category")
	private ExpenseCategory category;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "payment_method")
	private ExpensePaymentMethod paymentMethod;
	
	@Column(name = "amount")
	private Double amount;
	
	@Column(name = "date")
	private LocalDate date;
	
	@Column(name = "createdAt")
	private LocalDateTime createdAt;

	
	
	// define constructors
	public Expense() {
		// TODO look up why a default constructor is needed after calling find by id
	}
	
	public Expense(String title, ExpenseCategory category, ExpensePaymentMethod paymentMethod,
			Double amount) {
		super();
		this.title = title;
		this.category = category != null ? category : ExpenseCategory.OTHER;
		this.paymentMethod = paymentMethod != null ? paymentMethod : ExpensePaymentMethod.OTHER;
		this.amount = amount != null ? amount : 0.0;
		this.date = LocalDate.now();
		this.createdAt = LocalDateTime.now(); // TODO : search on saving a localDateTime to MySQL | what format it has to be in
	}



	public Integer getId() {
		return id;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public ExpenseCategory getCategory() {
		return category;
	}



	public void setCategory(ExpenseCategory category) {
		this.category = category;
	}



	public ExpensePaymentMethod getPaymentMethod() {
		return paymentMethod;
	}



	public void setPaymentMethod(ExpensePaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}



	public Double getAmount() {
		return amount;
	}



	public void setAmount(Double amount) {
		this.amount = amount;
	}



	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	
	public LocalDate getDate() {
		return date;
	}



	@Override
	public String toString() {
		return "Expense [id=" + id + ", title=" + title + ", category=" + category + ", paymentMethod=" + paymentMethod
				+ ", amount=" + amount + ", createdAt=" + createdAt + "]";
	}



	@Override
	public int hashCode() {
		return Objects.hash(amount, category, createdAt, id, paymentMethod, title);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Expense other = (Expense) obj;
		return Objects.equals(amount, other.amount) && category == other.category
				&& Objects.equals(createdAt, other.createdAt) && Objects.equals(id, other.id)
				&& paymentMethod == other.paymentMethod && Objects.equals(title, other.title);
	}
	
}
