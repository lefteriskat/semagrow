package org.semagrow.repository.impl;

import org.semagrow.repository.SemagrowRepository;
import org.semagrow.sail.SemagrowSail;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryException;
import org.eclipse.rdf4j.repository.base.RepositoryWrapper;
import org.eclipse.rdf4j.repository.sail.SailRepository;

/**
 * Created by angel on 6/10/14.
 */
public class SemagrowSailRepository
        extends RepositoryWrapper
        implements SemagrowRepository
{
    private SemagrowSail semagrowSail;

    public SemagrowSailRepository(SemagrowSail sail) {
        super(new SailRepository(sail));
        semagrowSail = sail;
    }

    @Override
    public SemagrowSailRepositoryConnection getConnection() throws RepositoryException {
        return new SemagrowSailRepositoryConnection(this, super.getConnection());
    }

    public Repository getMetadataRepository() { return semagrowSail.getMetadataRepository(); }
}
