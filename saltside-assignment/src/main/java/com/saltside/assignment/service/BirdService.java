package com.saltside.assignment.service;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.util.CollectionUtils.isEmpty;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.saltside.assignment.dto.BirdResponse;
import com.saltside.assignment.dto.BirdsRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/")
public class BirdService {
	
	@Resource private BirdMediator birdMediator;
	
	@RequestMapping(value = "/birds", method = POST, consumes = "application/json; charset=UTF-8")
	public ResponseEntity<BirdResponse> postBirds(@RequestBody BirdsRequest birdRequest) {
		log.info("Post bird request for: [{}]", birdRequest);
		
		final BirdResponse response = birdMediator.save(birdRequest);
		
		return new ResponseEntity<>(response, CREATED);
	}
	
	@RequestMapping(value = "/birds/{id}", method = GET, produces = "application/json; charset=UTF-8")
	@ResponseBody()
	public ResponseEntity<BirdResponse> getBirdById(@PathVariable("id") final String id) {
		log.info("Get bird: id=[{}]", id);
		
		final BirdResponse response = birdMediator.findBird(id);
		
		return new ResponseEntity<>(response, OK);
	}
	
	@RequestMapping(value = "/birds", method = GET, consumes = "application/json; charset=UTF-8")
	@ResponseBody()
	public ResponseEntity<Collection<BirdResponse>> getAllBirds() {
		log.info("Get all visible birds");
		final Collection<BirdResponse> response = birdMediator.findAllVisibleBirds();
		
		if(isEmpty(response)) {
			return new ResponseEntity<>(NO_CONTENT);
		}
		return new ResponseEntity<>(response, OK);
	}
	
	@RequestMapping(value = "/birds/{id}", method = DELETE, produces = "application/json; charset=UTF-8")
	@ResponseBody()
	public ResponseEntity<Void> deleteBird(@PathVariable("id") final String id) {
		log.info("Delete bird: id=[{}]", id);
		
		birdMediator.deleteBird(id);
		
		return new ResponseEntity<>(OK);
	}
	
	
	
}