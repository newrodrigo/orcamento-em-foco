package com.rodrigooc.orcamentoemfoco.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rodrigooc.orcamentoemfoco.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
