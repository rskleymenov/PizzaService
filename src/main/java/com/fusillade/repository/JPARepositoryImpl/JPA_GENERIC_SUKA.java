package com.fusillade.repository.JPARepositoryImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public class JPA_GENERIC_SUKA {
	
	@PersistenceContext
	protected EntityManager em;
}
