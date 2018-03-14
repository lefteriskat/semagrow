package org.semagrow.model.vocabulary;

import org.eclipse.rdf4j.model.Namespace;
import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.model.impl.SimpleNamespace;
import org.eclipse.rdf4j.model.impl.SimpleValueFactory;

/**
 * Created by angel on 4/28/14.
 */
public final class VOID {

    public static final String NAMESPACE = "http://rdfs.org/ns/void#";

    public static final String PREFIX = "void";

    public static final Namespace NS = new SimpleNamespace(PREFIX, NAMESPACE);

    public final static IRI DATASET;

    public final static IRI LINKSET;

    public final static IRI SUBSET;
    public final static IRI PROPERTYPARTITION;
    public final static IRI CLASSPARTITION;
    public final static IRI SPARQLENDPOINT;

    public final static IRI TRIPLES;
    public final static IRI PROPERTIES;
    public final static IRI CLASSES;
    public final static IRI ENTITIES;
    public final static IRI DISTINCTSUBJECTS;
    public final static IRI DISTINCTOBJECTS;
    public final static IRI MINSUBJECTS;
    public final static IRI MINPROPERTIES;
    public final static IRI MINOBJECTS;
    public final static IRI MAXSUBJECTS;
    public final static IRI MAXPROPERTIES;
    public final static IRI MAXOBJECTS;
    public final static IRI URIREGEXPATTERN;
    public final static IRI PROPERTY;
    public final static IRI CLASS;
    public final static IRI URISPACE;

    static {
        ValueFactory factory = SimpleValueFactory.getInstance();
        DATASET = factory.createIRI(VOID.NAMESPACE, "Dataset");
        LINKSET = factory.createIRI(VOID.NAMESPACE, "Linkset");
        SUBSET = factory.createIRI(VOID.NAMESPACE, "subset");
        PROPERTYPARTITION = factory.createIRI(VOID.NAMESPACE, "propertyPartition");
        CLASSPARTITION = factory.createIRI(VOID.NAMESPACE, "classPartition");
        SPARQLENDPOINT = factory.createIRI(VOID.NAMESPACE, "sparqlEndpoint");
        TRIPLES = factory.createIRI(VOID.NAMESPACE, "triples");
        PROPERTIES = factory.createIRI(VOID.NAMESPACE, "properties");
        CLASSES = factory.createIRI(VOID.NAMESPACE, "classes");
        ENTITIES = factory.createIRI(VOID.NAMESPACE, "entities");
        DISTINCTSUBJECTS = factory.createIRI(VOID.NAMESPACE, "distinctSubjects");
        DISTINCTOBJECTS = factory.createIRI(VOID.NAMESPACE, "distinctObjects");
        MINSUBJECTS = factory.createIRI(VOID.NAMESPACE, "minSubjects");
        MINPROPERTIES = factory.createIRI(VOID.NAMESPACE, "minProperties");
        MINOBJECTS = factory.createIRI(VOID.NAMESPACE, "minObjects");
        MAXSUBJECTS = factory.createIRI(VOID.NAMESPACE, "maxSubjects");
        MAXPROPERTIES = factory.createIRI(VOID.NAMESPACE, "maxProperties");
        MAXOBJECTS = factory.createIRI(VOID.NAMESPACE, "maxObjects");
        URIREGEXPATTERN = factory.createIRI(VOID.NAMESPACE, "uriRegexPattern");
        PROPERTY = factory.createIRI(VOID.NAMESPACE, "property");
        CLASS = factory.createIRI(VOID.NAMESPACE, "class");
        URISPACE = factory.createIRI(VOID.NAMESPACE, "uriSpace");
    }

}
