/**
 * 
 */
package eu.semagrow.stack.modules.utils.impl;

import org.openrdf.model.URI;

import eu.semagrow.stack.modules.utils.EquivalentURI;


/* (non-Javadoc)
 * @see eu.semagrow.stack.modules.utils.EquivalentURI()
 */
public class EquivalentURIImpl implements EquivalentURI {
	
	private URI equivalent_URI;
	private int proximity;
	private URI schema;
	/**
	 * @param equivalent_URI
	 * @param proximity
	 * @param schema
	 */
	public EquivalentURIImpl(URI equivalent_URI, int proximity, URI schema) {
		super();
		this.equivalent_URI = equivalent_URI;
		this.proximity = proximity;
		this.schema = schema;
	}
	/* (non-Javadoc)
	 * @see eu.semagrow.stack.modules.utils.EquivalentURI#getEquivalent_URI()
	 */
	public URI getEquivalent_URI() {
		return equivalent_URI;
	}
	/* (non-Javadoc)
	 * @see eu.semagrow.stack.modules.utils.EquivalentURI#setEquivalent_URI(org.openrdf.model.URI)
	 */
	public void setEquivalent_URI(URI equivalent_URI) {
		this.equivalent_URI = equivalent_URI;
	}
	/* (non-Javadoc)
	 * @see eu.semagrow.stack.modules.utils.EquivalentURI#getProximity()
	 */
	public int getProximity() {
		return proximity;
	}
	/* (non-Javadoc)
	 * @see eu.semagrow.stack.modules.utils.EquivalentURI#setProximity(int)
	 */
	public void setProximity(int proximity) {
		this.proximity = proximity;
	}
	/* (non-Javadoc)
	 * @see eu.semagrow.stack.modules.utils.EquivalentURI#getSchema()
	 */
	public URI getSchema() {
		return schema;
	}
	/* (non-Javadoc)
	 * @see eu.semagrow.stack.modules.utils.EquivalentURI#setSchema(org.openrdf.model.URI)
	 */
	public void setSchema(URI schema) {
		this.schema = schema;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	/* (non-Javadoc)
	 * @see eu.semagrow.stack.modules.utils.EquivalentURI#toString()
	 */
	@Override
	public String toString() {
		return "EquivalentURI [equivalent_URI=" + equivalent_URI
				+ ", proximity=" + proximity + ", schema=" + schema + "]";
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	/* (non-Javadoc)
	 * @see eu.semagrow.stack.modules.utils.EquivalentURI#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((equivalent_URI == null) ? 0 : equivalent_URI.hashCode());
		result = prime * result + proximity;
		result = prime * result + ((schema == null) ? 0 : schema.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	/* (non-Javadoc)
	 * @see eu.semagrow.stack.modules.utils.EquivalentURI#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EquivalentURIImpl other = (EquivalentURIImpl) obj;
		if (equivalent_URI == null) {
			if (other.equivalent_URI != null)
				return false;
		} else if (!equivalent_URI.equals(other.equivalent_URI))
			return false;
		if (proximity != other.proximity)
			return false;
		if (schema == null) {
			if (other.schema != null)
				return false;
		} else if (!schema.equals(other.schema))
			return false;
		return true;
	}


}
