package com.fusillade.repository.JPARepositoryImpl;

import java.util.List;

import javax.persistence.EntityManager;

import com.fusillade.domain.entity.Address;
import com.fusillade.repository.AddressRepository;

public class JPAAddressRepository implements AddressRepository {
	private EntityManager em;

	public JPAAddressRepository() {
	}

	public JPAAddressRepository(EntityManager em) {
		this.em = em;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	public Address findById(int id) {
		return em.find(Address.class, id);
	}

	@Override
	public void create(Address address) {
		em.getTransaction().begin();
		em.persist(address);
		em.getTransaction().commit();
	}

	@Override
	public void update(Address address) {
		em.getTransaction().begin();
		em.merge(address);
		em.getTransaction().commit();

	}

	@Override
	public void delete(int id) {
		Address address = findById(id);
		em.getTransaction().begin();
		em.remove(address);
		em.getTransaction().commit();

	}

	@Override
	public List<Address> getAll() {
		return em.createQuery("select a from Address a", Address.class).getResultList();
	}
}
