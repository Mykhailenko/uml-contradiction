package com.uml.contradiction.model.object;

public class LinkEnd {
	private OObject object;
	private String role;

	public LinkEnd(OObject object) {
		super();
		this.object = object;
	}

	public LinkEnd(OObject object, String role) {
		super();
		this.object = object;
		this.role = role;
	}

	public OObject getObject() {
		return object;
	}

	public void setObject(OObject object) {
		this.object = object;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "LinkEnd [objectName=" + object.getName() + ", role=" + role
				+ "]";
	}

}
