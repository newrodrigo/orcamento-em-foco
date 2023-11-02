package com.rodrigooc.orcamentoemfoco.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rodrigooc.orcamentoemfoco.dto.ProviderDTO;
import com.rodrigooc.orcamentoemfoco.entities.Provider;
import com.rodrigooc.orcamentoemfoco.repositories.ProviderRepository;
import com.rodrigooc.orcamentoemfoco.services.exceptions.DatabaseException;
import com.rodrigooc.orcamentoemfoco.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProviderService {

	@Autowired
	private ProviderRepository repository;

	@Transactional(readOnly = true)
	public Page<ProviderDTO> findAllPaged(Pageable pageable) {
		Page<Provider> list = repository.findAll(pageable);
		return list.map(x -> new ProviderDTO(x));
	}

	@Transactional(readOnly = true)
	public ProviderDTO findById(Long id) {
		Optional<Provider> obj = repository.findById(id);
		Provider entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new ProviderDTO(entity);
	}

	@Transactional
	public ProviderDTO insert(ProviderDTO dto) {
		Provider entity = new Provider();
		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());
		entity.setPhone(dto.getPhone());
		entity = repository.save(entity);
		return new ProviderDTO(entity);
	}

	@Transactional
	public ProviderDTO update(Long id, ProviderDTO dto) {
		try {
			Provider entity = repository.getReferenceById(id);
			entity.setName(dto.getName());
			entity.setEmail(dto.getEmail());
			entity.setPhone(dto.getPhone());
			entity = repository.save(entity);
			return new ProviderDTO(entity);
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
