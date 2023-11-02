package com.rodrigooc.orcamentoemfoco.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rodrigooc.orcamentoemfoco.dto.ExpenseDTO;
import com.rodrigooc.orcamentoemfoco.entities.Category;
import com.rodrigooc.orcamentoemfoco.entities.Expense;
import com.rodrigooc.orcamentoemfoco.entities.Provider;
import com.rodrigooc.orcamentoemfoco.entities.enums.Situation;
import com.rodrigooc.orcamentoemfoco.repositories.CategoryRepository;
import com.rodrigooc.orcamentoemfoco.repositories.ExpenseRepository;
import com.rodrigooc.orcamentoemfoco.repositories.ProviderRepository;
import com.rodrigooc.orcamentoemfoco.services.exceptions.DatabaseException;
import com.rodrigooc.orcamentoemfoco.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ExpenseService {

	@Autowired
	private ExpenseRepository repository;

	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ProviderRepository providerRepository;

	@Transactional(readOnly = true)
	public Page<ExpenseDTO> findAllPaged(Pageable pageable) {
		Page<Expense> list = repository.findAll(pageable);
		return list.map(x -> new ExpenseDTO(x));
	}

	@Transactional(readOnly = true)
	public ExpenseDTO findById(Long id) {
		Optional<Expense> obj = repository.findById(id);
		Expense entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new ExpenseDTO(entity);
	}

	@Transactional
	public ExpenseDTO insert(ExpenseDTO dto) {
		Expense entity = new Expense();
		entity.setDescription(dto.getDescription());
		entity.setSituation(Situation.valueOf(dto.getSituation()));
		entity.setAmount(dto.getAmount());
		entity.setPaymentDate(dto.getPaymentDate());

		Category category = categoryRepository.findById(dto.getCategoryId())
				.orElseThrow(() -> new EntityNotFoundException("Category not found"));
		Provider provider = providerRepository.findById(dto.getProviderId())
				.orElseThrow(() -> new EntityNotFoundException("Provider not found"));

		entity.setCategory(category);
		entity.setProvider(provider);
		entity.setObservation(dto.getObservation());

		entity = repository.save(entity);
		return new ExpenseDTO(entity);
	}

	@Transactional
	public ExpenseDTO update(Long id, ExpenseDTO dto) {
		try {
			Optional<Expense> optionalEntity = repository.findById(id);
			if (optionalEntity.isEmpty()) {
				throw new ResourceNotFoundException("Id not found " + id);
			}
			Expense entity = optionalEntity.get();

			entity.setDescription(dto.getDescription());
			entity.setSituation(Situation.valueOf(dto.getSituation()));
			entity.setAmount(dto.getAmount());
			entity.setPaymentDate(dto.getPaymentDate());

			Category category = categoryRepository.findById(dto.getCategoryId())
					.orElseThrow(() -> new EntityNotFoundException("Category not found"));
			Provider provider = providerRepository.findById(dto.getProviderId())
					.orElseThrow(() -> new EntityNotFoundException("Provider not found"));

			entity.setCategory(category);
			entity.setProvider(provider);
			entity.setObservation(dto.getObservation());

			entity = repository.save(entity);
			return new ExpenseDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}

}
