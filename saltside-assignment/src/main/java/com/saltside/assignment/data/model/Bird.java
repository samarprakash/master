
package com.saltside.assignment.data.model;

import java.util.Collection;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Bird {

	@Id
	private String id;
    private String name;
    private String family;
    private Collection<Item> continents;
    private String added;
    private boolean visible;
    
    public Bird() {
    }


    
}
