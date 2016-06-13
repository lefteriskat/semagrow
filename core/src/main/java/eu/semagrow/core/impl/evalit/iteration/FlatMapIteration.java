package eu.semagrow.core.impl.evalit.iteration;

import org.eclipse.rdf4j.common.iteration.CloseableIteration;
import org.eclipse.rdf4j.common.iteration.AbstractCloseableIteration;
import org.eclipse.rdf4j.common.iteration.Iteration;

/**
 * Created by angel on 12/4/14.
 */
public abstract class FlatMapIteration<T,S,X extends Exception> extends AbstractCloseableIteration<T,X> {

    private final Iteration<? extends S,? extends X> iter;

    private CloseableIteration<T,X> innerIter;

    public FlatMapIteration(Iteration<? extends S, ? extends X> iter) {
        this.iter = iter;
    }

    public boolean hasNext() throws X {
        return false;
    }

    public T next() throws X {
        return null;
    }

    public void remove() throws X {

    }

    public abstract CloseableIteration<T,X> convert(S elem);
}
