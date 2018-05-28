package ir.maktabsharif.model.pojo;

import java.util.Set;
public class Role {
	private String name;
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

	@Override
	public String toString(){
		return "role name: "+getName()+", features: "+getFeatures().toString();
	}
}
