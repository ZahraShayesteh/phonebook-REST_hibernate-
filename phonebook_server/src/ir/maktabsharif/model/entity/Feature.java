package ir.maktabsharif.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Feature {
@Id
	private String feature;

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public Feature(String feature) {
		this.feature = feature;
	}

	public Feature() {
	}

}
