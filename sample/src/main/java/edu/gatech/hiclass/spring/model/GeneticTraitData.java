package edu.gatech.hiclass.spring.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GeneticTraitData extends Measurements {
	private String description;
	private String trait;

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getGenetic() {
		return description;
	}
	public String getTrait() {
		return trait;
	}
	public void setTrait(String trait) {
		this.trait = trait;
	}

	@Override
	public String getKey() {
		return description;
	}
	@Override
	public String toString() {
        return ("Genetic " + getGenetic()
        		+ "  Trait " + getTrait());
	}
}
