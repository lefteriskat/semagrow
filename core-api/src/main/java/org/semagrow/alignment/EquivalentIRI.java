package org.semagrow.alignment;

import org.eclipse.rdf4j.model.IRI;

/**
 * For a URI this object holds its equivalent URI, the proximity of the
 * equivalent to the original URI and the schema URI that the equivalent URI
 * belongs to.
 * 
 * @author Giannis Mouchakis
 * 
 */
public interface EquivalentIRI {

    IRI getSourceURI();

    IRI getTargetURI();

    IRI getSourceSchema();

    IRI getTargetSchema();

    int getTransformationID();

	/**
	 * @return the equivalent URI
	 */
	//public URI getEquivalent_URI();

	/**
	 * @return the proximity to the original URI
	 */
	int getProximity();

	/**
	 * @return the identifier of the RDF schema of the equivalent URI
	 */
	//IRI getSchema();

}