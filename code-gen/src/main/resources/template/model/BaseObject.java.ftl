package ${packageName}.model;

import org.apache.commons.lang.StringUtils;

public abstract class BaseObject {
	public String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isNew() {
		return StringUtils.isBlank(this.id);
	}

	public abstract boolean equals(Object o);

	public abstract int hashCode();

	public abstract String toString();
}
