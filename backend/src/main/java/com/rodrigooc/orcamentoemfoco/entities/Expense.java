package com.rodrigooc.orcamentoemfoco.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import com.rodrigooc.orcamentoemfoco.entities.enums.Situation;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_expense")
public class Expense implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String description;

	private Integer situation;
	private Double amount;

	private LocalDate paymentDate;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	@ManyToOne
	@JoinColumn(name = "provider_id")
	private Provider provider;

	private String observation;

	public Expense() {
	}

	public Expense(Long id, String description, Situation situation, Double amount, LocalDate paymentDate,
			Category category, Provider provider, String observation) {
		this.id = id;
		this.description = description;
		setSituation(situation);
		this.amount = amount;
		this.paymentDate = paymentDate;
		this.category = category;
		this.provider = provider;
		this.observation = observation;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Situation getSituation() {
		return Situation.valueOf(situation);
	}

	public void setSituation(Situation situation) {
		if (situation != null) {
			this.situation = situation.getCode();
		}
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
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
		return Objects.equals(id, other.id);
	}
}
