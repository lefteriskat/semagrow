/**
 * 
 */
package eu.semagrow.stack.modules.utils.impl;

import org.openrdf.model.URI;

import eu.semagrow.stack.modules.utils.Measurement;
import eu.semagrow.stack.modules.utils.SelectedResource;

import java.util.List;

/* (non-Javadoc)
 * @see eu.semagrow.stack.modules.utils.SelectedResource
 */
public class SelectedResourceImpl implements SelectedResource {
	
	private URI endpoint;
	private int vol;
	private int var;
	private URI subject = null;
	private int subjectProximity = 0;
	private URI predicate = null;
	private int predicateProximity = 0;
	private URI object = null;
	private int objectProximity = 0;
	private List<Measurement> loadInfo = null;
	
	/**
	 * @param endpoint
	 * @param vol
	 * @param var
	 */
	public SelectedResourceImpl(URI endpoint, int vol, int var) {
		super();
		this.endpoint = endpoint;
		this.vol = vol;
		this.var = var;
	}
	
	/* (non-Javadoc)
	 * @see eu.semagrow.stack.modules.utils.SelectedResource#getEndpoint()
	 */
	public URI getEndpoint() {
		return endpoint;
	}
	
	/* (non-Javadoc)
	 * @see eu.semagrow.stack.modules.utils.SelectedResource#getVol()
	 */
	public int getVol() {
		return vol;
	}
	
	/* (non-Javadoc)
	 * @see eu.semagrow.stack.modules.utils.SelectedResource#getVar()
	 */
	public int getVar() {
		return var;
	}
	
	/* (non-Javadoc)
	 * @see eu.semagrow.stack.modules.utils.SelectedResource#getSubject()
	 */
	public URI getSubject() {
		return subject;
	}
	
	/* (non-Javadoc)
	 * @see eu.semagrow.stack.modules.utils.SelectedResource#setSubject(org.openrdf.model.URI)
	 */
	public void setSubject(URI subject) {
		this.subject = subject;
	}
	
	/* (non-Javadoc)
	 * @see eu.semagrow.stack.modules.utils.SelectedResource#getSubjectProximity()
	 */
	public int getSubjectProximity() {
		return subjectProximity;
	}
	
	/* (non-Javadoc)
	 * @see eu.semagrow.stack.modules.utils.SelectedResource#setSubjectProximity(int)
	 */
	public void setSubjectProximity(int subjectProximity) {
		this.subjectProximity = subjectProximity;
	}
	
	/* (non-Javadoc)
	 * @see eu.semagrow.stack.modules.utils.SelectedResource#getPredicate()
	 */
	public URI getPredicate() {
		return predicate;
	}
	
	/* (non-Javadoc)
	 * @see eu.semagrow.stack.modules.utils.SelectedResource#setPredicate(org.openrdf.model.URI)
	 */
	public void setPredicate(URI predicate) {
		this.predicate = predicate;
	}
	
	/* (non-Javadoc)
	 * @see eu.semagrow.stack.modules.utils.SelectedResource#getPredicateProximity()
	 */
	public int getPredicateProximity() {
		return predicateProximity;
	}
	
	/* (non-Javadoc)
	 * @see eu.semagrow.stack.modules.utils.SelectedResource#setPredicateProximity(int)
	 */
	public void setPredicateProximity(int predicateProximity) {
		this.predicateProximity = predicateProximity;
	}
	
	/* (non-Javadoc)
	 * @see eu.semagrow.stack.modules.utils.SelectedResource#getObject()
	 */
	public URI getObject() {
		return object;
	}
	
	/* (non-Javadoc)
	 * @see eu.semagrow.stack.modules.utils.pl.SelectedResource#setObject(org.openrdf.model.URI)
	 */
	public void setObject(URI object) {
		this.object = object;
	}
	
	/* (non-Javadoc)
	 * @see eu.semagrow.stack.modules.utils.SelectedResource#getObjectProximity()
	 */
	public int getObjectProximity() {
		return objectProximity;
	}
	
	/* (non-Javadoc)
	 * @see eu.semagrow.stack.modules.utils.SelectedResource#setObjectProximity(int)
	 */
	public void setObjectProximity(int objectProximity) {
		this.objectProximity = objectProximity;
	}
	

	/* (non-Javadoc)
	 * @see eu.semagrow.stack.modules.utils.SelectedResourcee#getLoadInfo()
	 */
	public List<Measurement> getLoadInfo() {
		return loadInfo;
	}

	/* (non-Javadoc)
	 * @see eu.semagrow.stack.modules.utils.SelectedResource#setLoadInfo(java.util.List)
	 */
	public void setLoadInfo(List<Measurement> loadInfo) {
		this.loadInfo = loadInfo;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	/* (non-Javadoc)
	 * @see eu.semagrow.stack.modules.utils.SelectedResource#toString()
	 */
	@Override
	public String toString() {
		return "SelectedResource [endpoint=" + endpoint + ", vol=" + vol
				+ ", var=" + var + ", subject=" + subject
				+ ", subjectProximity=" + subjectProximity + ", predicate="
				+ predicate + ", predicateProximity=" + predicateProximity
				+ ", object=" + object + ", objectProximity=" + objectProximity
				+ ", loadInfo=" + loadInfo + "]";
	}
	

}
