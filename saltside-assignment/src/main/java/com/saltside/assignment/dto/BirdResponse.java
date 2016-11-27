
package com.saltside.assignment.dto;

import static com.google.common.collect.ImmutableSortedSet.copyOf;
import static com.google.common.collect.ImmutableSortedSet.of;
import static java.util.Objects.isNull;

import java.util.Collection;
import java.util.Set;

import javax.annotation.Generated;
import javax.annotation.Nullable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * POST /birds [request]
 * <p>
 * Add a new bird to the library
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.saltside.assignment.dto")
@JsonPropertyOrder({
    "name",
    "family",
    "continents",
    "added",
    "visible"
})
@EqualsAndHashCode
@ToString
@Getter
public class BirdResponse {

    @NotNull
    private final String name;
    
    
    @NotNull
    private final String family;
    
    @Size(min = 1)
    @Valid
    @NotNull
    private final Set<Item> continents;
    
    
    @Nullable
    private final String added;
    
    @Nullable
    private final boolean visible;
    
    @Nullable
    private final String id;

    @JsonCreator
    public BirdResponse(
    		@JsonProperty("id") @Nullable final String id, 
    		@JsonProperty("name") final String name, 
    		@JsonProperty("family") final String family, 
    		@JsonProperty("continents") final Collection<Item> continents, 
    		@JsonProperty("added") @Nullable final String added, 
    		@JsonProperty("visible") @Nullable final boolean visible) {
    	this.id = id;
        this.name = name;
        this.family = family;
        this.continents = isNull(continents) ? of() : copyOf(continents);
        this.added = added;
        this.visible = visible;
    }

    
}
