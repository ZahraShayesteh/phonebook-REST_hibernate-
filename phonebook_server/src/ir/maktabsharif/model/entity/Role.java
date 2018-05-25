package ir.maktabsharif.model.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Role {
	@Id
	private String name;
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Feature> features;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Feature> getFeatures() {
		return features;
	}

	public void setFeatures(Set<Feature> features) {
		this.features = features;
	}

	public Role() {
	}

	public Role(String name, Set<Feature> features) {
		this.name = name;
		this.features = features;
	}
}
