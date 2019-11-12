package uo.ri.cws.domain;

import java.util.UUID;

/**
 * Base Entity for mapping. Mapped Supperclass
 * <p>
 * * @author UO257192
 */
public abstract class BaseEntity {

	private String id = UUID.randomUUID().toString();
	private Long version;

	/**
	 * Base Entity Constructor
	 */
	public BaseEntity() {
		super();
	}

	/**
	 * @return entity ID
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return entity version
	 */
	public Long getVersion() {
		return version;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseEntity other = (BaseEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}