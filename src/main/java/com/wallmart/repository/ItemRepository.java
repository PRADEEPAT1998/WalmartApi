package com.wallmart.repository;



import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wallmart.model.Item;


@Repository
public interface ItemRepository extends CrudRepository<Item , Long>{

	public Optional<Item> findByitemId(Long id);
	
	public List<Item> findByItemId(Long ids);
	
	public Page<Item> findAll(Pageable pg);


	
}
