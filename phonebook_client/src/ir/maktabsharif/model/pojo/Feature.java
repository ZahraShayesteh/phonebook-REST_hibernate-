package ir.maktabsharif.model.pojo;

public class Feature {
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
	
	@Override
	public String toString(){
		return "feature: "+getFeature();
	}
}
