package com.saltside.assignment.data.repository;

import java.util.Collection;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.saltside.assignment.data.model.Bird;

@Repository
public interface BirdRepository extends MongoRepository<Bird, String> {
	
	@Transactional
	@Query("{ 'visible' : true }")
	Collection<Bird> findByVisibleIsTrue();
	
	@Transactional
	Long removeById(String id);
 
}