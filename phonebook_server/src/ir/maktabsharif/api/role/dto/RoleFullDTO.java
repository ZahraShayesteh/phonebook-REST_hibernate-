package ir.maktabsharif.api.role.dto;

import java.util.HashSet;
import java.util.Set;

import ir.maktabsharif.model.entity.Feature;
import ir.maktabsharif.model.entity.Role;

public class RoleFullDTO {
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

	public RoleFullDTO() {
	}

	public RoleFullDTO(String name, Set<Feature> features) {
		this.name = name;
		this.features = features;
	}

	public RoleFullDTO convertToDto(Role role) {
		if(role!=null){
			this.name = role.getName();
			this.features = role.getFeatures();
			return this;
		}
		return null;
	}

	public Role convertToObject() {
		Role role = new Role();
		role.setName(this.getName());
		role.setFeatures(this.getFeatures());
		return role;
	}

	public static Role guestRole() {

		Set<Feature> features = new HashSet<Feature>();
		features.add(new Feature("seeContacts"));

		return new Role("guest", features);
	}

	public static Role simpleUserRole() {

		Set<Feature> features = new HashSet<Feature>();

		features.add(new Feature("seeContacts"));
		features.add(new Feature("createContacts"));

		return new Role("simpleUser", features);
	}

	public static Role simpleAdminRole() {

		Set<Feature> features = new HashSet<Feature>();

		features.add(new Feature("seeContacts"));
		features.add(new Feature("createContacts"));
		features.add(new Feature("editContacts"));
		features.add(new Feature("seeLog"));

		return new Role("simpleAdmin", features);
	}

	public static Role mainAdminRole() {

		Set<Feature> features = new HashSet<Feature>();

		features.add(new Feature("seeContacts"));
		features.add(new Feature("createContacts"));
		features.add(new Feature("editContacts"));
		features.add(new Feature("seeLog"));
		features.add(new Feature("editUsers"));
		features.add(new Feature("editRoles"));

		return new Role("mainAdmin", features);
	}
}
