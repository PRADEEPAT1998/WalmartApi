package com.wallmart.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wallmart.model.ImageEntities;

@Repository
public interface ImageEntitiesRepository extends CrudRepository<ImageEntities,Long >{

	//Optional<ImageEntities> findby
}