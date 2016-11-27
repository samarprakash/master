
package com.saltside.assignment.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
@ToString
@Getter
public class Item {

    @NotNull(message = "items cannot be null")
    private final String items;
    
    

    @JsonCreator
    public Item(
    		@JsonProperty("items") final String items) {
        this.items = items;
    }

    
}
