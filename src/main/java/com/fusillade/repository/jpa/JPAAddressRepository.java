package com.fusillade.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.fusillade.domain.entity.Address;
import com.fusillade.repository.AddressRepository;

@Repository
public class JPAAddressRepository implements AddressRepository {

	@PersistenceContext
	protected EntityManager em;
	
	@Override
	public Address findById(int id) {
		return em.find(Address.class, id);
	}

	@Override
	public Address save(Address address) {
		return em.merge(address);
	}

	@Override
	public void delete(int id) {
		Address address = findById(id);
		em.remove(address);
	}

	@Override
	public List<Address> getAll() {
		return em.createQuery("select a from Address a", Address.class).getResultList();
	}
}
