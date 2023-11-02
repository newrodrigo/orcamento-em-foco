package com.rodrigooc.orcamentoemfoco.dto;

import java.time.LocalDate;

import com.rodrigooc.orcamentoemfoco.entities.Expense;
import com.rodrigooc.orcamentoemfoco.entities.enums.Situation;

public class ExpenseDTO {

	private Long id;
	private String description;
	private Integer situation;
	private Double amount;
	private LocalDate paymentDate;
	private Long categoryId;
	private Long providerId;
	private String observation;

	public ExpenseDTO() {
	}

	public ExpenseDTO(Long id, String description, Integer situation, Double amount, LocalDate paymentDate,
			Long categoryId, Long providerId, String observation) {
		this.id = id;
		this.description = description;
		this.situation = situation;
		this.amount = amount;
		this.paymentDate = paymentDate;
		this.categoryId = categoryId;
		this.providerId = providerId;
		this.observation = observation;
	}

	public ExpenseDTO(Expense entity) {
		id = entity.getId();
		description = entity.getDescription();
		situation = entity.getSituation().getCode();
		amount = entity.getAmount();
		paymentDate = entity.getPaymentDate();
		categoryId = entity.getCategory().getId();
		providerId = entity.getProvider().getId();
		observation = entity.getObservation();
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

	public String getSituation() {
		return Situation.valueOf(situation).name(); // Retorna o nome do enum em vez do código
	}

	public void setSituation(Integer situation) {
		this.situation = situation;
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

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getProviderId() {
		return providerId;
	}

	public void setProviderId(Long providerId) {
		this.providerId = providerId;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}
}
