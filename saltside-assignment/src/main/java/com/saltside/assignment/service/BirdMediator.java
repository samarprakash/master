package com.saltside.assignment.service;

import static com.google.common.collect.Collections2.transform;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

import java.time.Instant;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Function;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

//import com.google.common.base.Function;
import com.saltside.assignment.data.model.Bird;
import com.saltside.assignment.data.repository.BirdRepository;
import com.saltside.assignment.dto.BirdResponse;
import com.saltside.assignment.dto.BirdsRequest;
import com.saltside.assignment.dto.Item;
import com.saltside.assignment.exception.BirdNotFoundException;

@Component
public class BirdMediator {
	
	private static final Function<Item, com.saltside.assignment.data.model.Item> REQITEM_TO_DBITEM = input -> {
		final com.saltside.assignment.data.model.Item item = new com.saltside.assignment.data.model.Item();
		item.setItems(input.getItems());
		return item;
	};
	
	private static final Function<com.saltside.assignment.data.model.Item, Item> DBITEM_TO_REQITEM = output -> {
		return new Item(output.getItems());
	};

	private static final Function<BirdsRequest, Bird> REQ_TO_DB = input -> {
		final Bird bird = new Bird();
		bird.setName(input.getName());
		bird.setFamily(input.getFamily());
		bird.setContinents(input.getContinents().stream().map(REQITEM_TO_DBITEM).collect(toList()));
		bird.setAdded(Instant.now().toString());
		bird.setVisible(input.isVisible());
		
		return bird;
	};

	private static final com.google.common.base.Function<Bird, BirdResponse> DB_TO_RES = output -> {
		final BirdResponse bird = new BirdResponse(output.getId(), output.getName(), output.getFamily(),
				output.getContinents().stream().map(DBITEM_TO_REQITEM).collect(toList()), output.getAdded(), output.isVisible());
		return bird;
	};

	@Resource
	private BirdRepository birdRepository;

	public BirdResponse save(final BirdsRequest bird) {
		return DB_TO_RES.apply((birdRepository.save(REQ_TO_DB.apply(bird))));
	}

	public BirdResponse findBird(final String id) {
		final Bird dbResult = birdRepository.findOne(id);
		if(Objects.isNull(dbResult)) {
			throw new BirdNotFoundException(id);
		}
		return DB_TO_RES.apply(dbResult);
	}

	public Collection<BirdResponse> findAllVisibleBirds() {
		Collection<Bird> dbResult = birdRepository.findByVisibleIsTrue();
		if(CollectionUtils.isEmpty(dbResult)) {
			return emptyList();
		}
		return transform(dbResult, DB_TO_RES);
	}

	public int deleteBird(final String id) {
		int result = Math.toIntExact(birdRepository.removeById(id));
		if(result == 0) {
			throw new BirdNotFoundException(id);
		}
		return result;
	}
}
