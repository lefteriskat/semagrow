package eu.semagrow.core.impl.sparql;

import eu.semagrow.core.source.Site;
import eu.semagrow.core.source.SiteConfig;
import eu.semagrow.core.source.SiteFactory;
import org.eclipse.rdf4j.model.IRI;

/**
 * Created by angel on 6/4/2016.
 */
public class SPARQLSiteFactory implements SiteFactory {

    @Override
    public String getType() { return SPARQLSiteConfig.TYPE; }

    @Override
    public SiteConfig getConfig() { return new SPARQLSiteConfig(); }

    @Override
    public Site getSite(SiteConfig config) {
        return new SPARQLSite();
    }

    @Override
    public Site getSite(IRI endpoint) { return new SPARQLSite(endpoint); }

}
