
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

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Generated(value={"com.saltside.assignment.dto.BirdsRequest"})
@JsonPropertyOrder(alphabetic = true)
@EqualsAndHashCode
@ToString
@Getter
public class BirdsRequest {

	@NotNull(message = "name cannot be null")
    private final String name;
    
    
	@NotNull(message = "family cannot be null")
    private final String family;
    
	@Size(min = 1, message = "continents size must be greater than 1")
    @Valid
    @NotNull(message = "continents cannot be null")
    private final Set<Item> continents;
    
    
    @Nullable
    private final String added;
    
    @Nullable
    private final boolean visible;

    @JsonCreator
    public BirdsRequest(
    		@JsonProperty("name") final String name, 
    		@JsonProperty("family") final String family, 
    		@JsonProperty("continents") final Collection<Item> continents, 
    		@JsonProperty("added") @Nullable final String added, 
    		@JsonProperty("visible") @Nullable final boolean visible) {
        this.name = name;
        this.family = family;
        this.continents = isNull(continents) ? of() : copyOf(continents);
        this.added = added;
        this.visible = visible;
    }

    
}
