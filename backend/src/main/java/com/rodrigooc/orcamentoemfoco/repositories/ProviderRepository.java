package com.rodrigooc.orcamentoemfoco.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rodrigooc.orcamentoemfoco.entities.Provider;

public interface ProviderRepository extends JpaRepository<Provider, Long> {

}
