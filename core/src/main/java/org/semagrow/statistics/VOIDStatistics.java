package org.semagrow.statistics;

import org.semagrow.selector.Site;
import org.semagrow.selector.VOIDBase;
import org.eclipse.rdf4j.model.Resource;
import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.model.vocabulary.RDF;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.algebra.StatementPattern;
import org.eclipse.rdf4j.repository.Repository;

import java.util.*;

/**
 * Created by angel on 4/30/14.
 */
public class VOIDStatistics extends VOIDBase implements Statistics {

    private Site site;

    public VOIDStatistics(Site site, Repository voidRepository) {
        super(voidRepository);
        this.site = site;
    }


    public StatsItem getStats(final StatementPattern pattern, BindingSet bindings) {
        Value sVal = pattern.getSubjectVar().getValue();
        Value pVal = pattern.getPredicateVar().getValue();
        Value oVal = pattern.getObjectVar().getValue();
        IRI source = (IRI) site.getID();


        StatsItemImpl statsItem = new StatsItemImpl(pattern);




//        if (isTypeClass(pattern)) {
//            System.out.println("edw0");
//            Set<Resource> datasets = getMatchingDatasetsOfClass((IRI)pattern.getObjectVar().getValue());
//            if (!datasets.isEmpty()) {
//                statsItem.setPatternCount( getEntities(datasets));
//            }
//        }

        Set<Resource> datasets  = getMatchingDatasetsOfEndpoint(source);

        //Set<Resource> oDatasets = new HashSet<Resource>(datasets);

        if (datasets.isEmpty())
            return statsItem;

        Set<Resource> pDatasets = null;
        Set<Resource> sDatasets = null;
        Set<Resource> oDatasets = null;

        if (pVal != null) {
            pDatasets = getMatchingDatasetsOfPredicate((IRI) pVal);
            System.out.println("pDAtasets = " + pDatasets.size());
        }
        if (sVal != null && sVal instanceof IRI) {
            sDatasets = getMatchingDatasetsOfSubject((IRI) sVal);
            System.out.println("sDAtasets = " + sDatasets.size());
            //get the first (the deepest matching bucket (dataset));
//            if(sDatasets != null && sDatasets.size()>0){
//                Resource dataset = sDatasets.iterator().next();
//                sDatasets = new LinkedHashSet<Resource>();
//                sDatasets.add(dataset);
//            }
        }
        if(oVal != null){
            oDatasets = getMatchingDatasetsOfObject((IRI)oVal);
            System.out.println("oDAtasets = " + oDatasets.size());
            //get the first (the deepest matching bucket (dataset));
//            if(oDatasets != null && oDatasets.size()>0){
//                Resource dataset = oDatasets.iterator().next();
//                oDatasets = new LinkedHashSet<Resource>();
//                oDatasets.add(dataset);
//            }
        }


        Set<Resource> spoDatasets = null;
        if(sDatasets != null && sDatasets.size()>0)
            spoDatasets = new LinkedHashSet<Resource>(sDatasets);
        if(oDatasets != null && oDatasets.size()>0)
            spoDatasets = new LinkedHashSet<Resource>(oDatasets);
        if(spoDatasets!=null && pDatasets != null && pDatasets.size()>0)
            spoDatasets.retainAll(pDatasets);
        else if(pDatasets != null && pDatasets.size()>0)
            spoDatasets = new LinkedHashSet<Resource>(pDatasets);

        System.out.println(sVal+" "+" "+pVal+" "+oVal+spoDatasets.toString());



        if (spoDatasets!= null && !spoDatasets.isEmpty()) { // datasets that match both the predicate and subject

            statsItem.setDistinctSubjects(getDistinctSubjects(spoDatasets));
            statsItem.setDistinctPredicates(getDistinctPredicates(spoDatasets));
            statsItem.setDistinctObjects(getDistinctObjects(spoDatasets));
            statsItem.setMinSubjects(getMinSubjects(spoDatasets));
            statsItem.setMinPredicates(getMinPredicates(spoDatasets));
            statsItem.setMinObjects(getMinObjects(spoDatasets));
            statsItem.setMaxSubjects(getMaxSubjects(spoDatasets));
            statsItem.setMaxPredicates(getMaxPredicates(spoDatasets));
            statsItem.setMaxObjects(getMaxObjects(spoDatasets));

            long d = 1;
            long max = 1;
            long min = 1;
            if (oVal != null) {
                d *= statsItem.getDistinctObjects();
                min *= statsItem.getMinObjects();
                max *= statsItem.getMaxObjects();
            }
            if (pVal != null) {
                d *= statsItem.getDistinctPredicates();
                min *= statsItem.getMinPredicates();
                max *= statsItem.getMaxPredicates();
            }
            if(sVal!=null){
                d *= statsItem.getDistinctSubjects();
                min *= statsItem.getMinSubjects();
                max *= statsItem.getMaxSubjects();
            }
            statsItem.setCurrMin(min);
            statsItem.setCurrMax(max);
            statsItem.setPatternCount(getTriplesCount(spoDatasets) / d);
        }
        else {
            long d = 1;

            if (oVal != null)
                d *= getDistinctObjects(datasets);
            if (pVal != null)
                d *= getDistinctPredicates(datasets);
            if (sVal != null)
                d *= getDistinctSubjects(datasets);

            if (d > 0 )
                statsItem.setPatternCount(getTriplesCount(sDatasets) / d);
            else
                statsItem.setPatternCount(0);
        }
        if (sVal != null && pVal != null && oVal != null)
            statsItem.setPatternCount(1);
        return statsItem;
    }
    public long getTriplesCount() {

        // get all triples statistics from datasets with property sparqlEndpoint = source
        // and get the maximum
        return 0;
    }

    private long getPatternCount(StatementPattern pattern, IRI source) {

        Value sVal = pattern.getSubjectVar().getValue();
        Value pVal = pattern.getPredicateVar().getValue();
        Value oVal = pattern.getObjectVar().getValue();

        System.out.println(sVal+ " "+pVal + " "+ oVal);

        if (sVal != null && pVal != null && oVal != null)
            return 1;


        if (isTypeClass(pattern)) {
            System.out.println("edw0");
            Set<Resource> datasets = getMatchingDatasetsOfClass((IRI)pattern.getObjectVar().getValue());
            if (!datasets.isEmpty()) {
                return getEntities(datasets);
            }
        }

        Set<Resource> datasets  = getMatchingDatasetsOfEndpoint(source);

        //Set<Resource> oDatasets = new HashSet<Resource>(datasets);

        if (datasets.isEmpty())
            return 0;

        Set<Resource> pDatasets = null;

        Set<Resource> sDatasets = null;

        if (pVal != null) {
            pDatasets = getMatchingDatasetsOfPredicate((IRI) pVal);
            System.out.println("pDAtasets = " + pDatasets.size());
        }
        if (sVal != null && sVal instanceof IRI) {
            sDatasets = getMatchingDatasetsOfSubject((IRI) sVal);
            System.out.println("sDAtasets = " + sDatasets.size());
        }
        //get the first (the deepest matching bucket (dataset));
        if(sDatasets.size()>0){
            Resource dataset = sDatasets.iterator().next();
            sDatasets = new LinkedHashSet<Resource>();
            sDatasets.add(dataset);
        }
        Set<Resource> spDatasets = new LinkedHashSet<Resource>(sDatasets);
        spDatasets.retainAll(pDatasets);

        if (!spDatasets.isEmpty()) { // datasets that match both the predicate and subject
            System.out.println("edw1");
            long d = 1;
            if (oVal != null)
                d *= getDistinctObjects(sDatasets);
            if (pVal != null)
                d *= getDistinctPredicates(sDatasets);
            if(sVal!=null)
                d *= getDistinctSubjects(sDatasets);
            return getTriplesCount(spDatasets) / d;
        } else if (pVal != null && !pDatasets.isEmpty()) {
            System.out.println("edw2");
            long d = 1;
            if (oVal != null)
                d *= getDistinctObjects(pDatasets);
            if (sVal != null)
                d *= getDistinctSubjects(pDatasets);

            return getTriplesCount(pDatasets) / d;
        } else if (sVal != null && !sDatasets.isEmpty()) {
            System.out.println("edw3");
            long d = 1;
            if (oVal != null)
                d *= getDistinctObjects(sDatasets);
            if (pVal != null)
                d *= getDistinctPredicates(sDatasets);
            if(sVal!=null)
                d *= getDistinctSubjects(sDatasets);
            return getTriplesCount(sDatasets) / d;
        }
        else {
            long d = 1;
            System.out.println("edw4");
            if (oVal != null)
                d *= getDistinctObjects(datasets);
            if (pVal != null)
                d *= getDistinctPredicates(datasets);
            if (sVal != null)
                d *= getDistinctSubjects(datasets);
            System.out.println ("Triples ="+getTriplesCount(datasets)+"dvc = "+d);
            System.out.println(getTriplesCount(datasets)/d);
            if (d > 0 )
                return getTriplesCount(datasets) / d;
            else
                return 0;
        }
    }

    private long getDistinctObjects(StatementPattern pattern, IRI source) {
        Value pVal = pattern.getPredicateVar().getValue();
        Value sVal = pattern.getSubjectVar().getValue();
        Value oVal = pattern.getObjectVar().getValue();

        Set<Resource> datasets  = getMatchingDatasetsOfEndpoint(source);

        if (datasets.isEmpty())
            return 0;

        if (oVal == null)
            return 1;

        Set<Resource> pDatasets = new HashSet<Resource>(datasets);
        Set<Resource> sDatasets = new HashSet<Resource>(datasets);

        if (pVal != null)
            pDatasets.retainAll(getMatchingDatasetsOfPredicate((IRI) pVal));

        if (sVal != null && sVal instanceof IRI)
            sDatasets.retainAll(getMatchingDatasetsOfSubject((IRI)sVal));

        Set<Resource> spDatasets = new HashSet<Resource>(pDatasets);
        spDatasets.retainAll(sDatasets);

        if (!spDatasets.isEmpty()) { // datasets that match both the predicate and subject
            return getDistinctObjects(spDatasets);
        } else if (pVal != null && !pDatasets.isEmpty()) {
            return getDistinctObjects(pDatasets);
        } else if (sVal != null && !sDatasets.isEmpty()) {
            return getDistinctObjects(sDatasets);
        }
        else {
            return getDistinctObjects(datasets);
        }
    }

    private long getDistinctSubjects(StatementPattern pattern, IRI source) {
        Value pVal = pattern.getPredicateVar().getValue();
        Value sVal = pattern.getSubjectVar().getValue();

        if (isTypeClass(pattern)) {
            Set<Resource> datasets = getMatchingDatasetsOfClass(source);
            if (!datasets.isEmpty()) {
                return getEntities(datasets);
            }
        }

        Set<Resource> datasets  = getMatchingDatasetsOfEndpoint(source);

        if (datasets.isEmpty())
            return 0;

        if (sVal == null)
            return 1;

        Set<Resource> pDatasets = new HashSet<Resource>(datasets);

        if (pVal != null && pVal instanceof IRI)
            pDatasets.retainAll(getMatchingDatasetsOfPredicate((IRI)pVal));

        //TODO: check datasets that must subject uriRegexPattern
        //

        if (!pDatasets.isEmpty()) {
            return getDistinctSubjects(pDatasets);
        }else{
            return getDistinctSubjects(datasets);
        }
    }

    private long getDistinctPredicates(StatementPattern pattern, IRI source){
        Value pVal = pattern.getPredicateVar().getValue();
        Value sVal = pattern.getSubjectVar().getValue();

        Set<Resource> datasets  = getMatchingDatasetsOfEndpoint(source);

        if (datasets.isEmpty())
            return 0;

        if (pVal == null)
            return 1;


        Set<Resource> sDatasets = new HashSet<Resource>(datasets);


        if (sVal != null && sVal instanceof IRI)
            sDatasets.retainAll(getMatchingDatasetsOfSubject((IRI)sVal));


        if (sVal != null && !sDatasets.isEmpty()) {
            return getDistinctPredicates(sDatasets);
        }else{
            return getDistinctPredicates(datasets);
        }
    }

    private Long getTriplesCount(Collection<Resource> datasets) {
        long triples = 0;
        boolean realData = false;
        for (Resource dataset : datasets) {
            Long t = getTriples(dataset);
            if (t != null && triples <= t.longValue()) {
                realData = true;
                triples = t.longValue();
            }
        }
        if (realData)
            return triples;
        else
            return (long)0;
    }

    private Long getDistinctSubjects(Collection<Resource> datasets) {
        long triples = 0;
        int i = 0;
        for (Resource dataset : datasets) {
            Long t = getDistinctSubjects(dataset);
            if (t != null) {
                triples += t.longValue();
                i++;
            }
        }
        return (i == 0) ? 1 : triples/i;
    }

    private Long getMinSubjects(Collection<Resource> datasets) {
        long triples = 0;
        int i = 0;
        for (Resource dataset : datasets) {
            Long t = getMinSubjects(dataset);
            if (t != null) {
                triples += t.longValue();
                i++;
            }
        }
        return (i == 0) ? 1 : triples/i;
    }

    private Long getMaxSubjects(Collection<Resource> datasets) {
        long triples = 0;
        int i = 0;
        for (Resource dataset : datasets) {
            Long t = getMaxSubjects(dataset);
            if (t != null) {
                triples += t.longValue();
                i++;
            }
        }
        return (i == 0) ? 1 : triples/i;
    }

    private Long getDistinctObjects(Collection<Resource> datasets) {
        long triples = 0;
        int i = 0;
        for (Resource dataset : datasets) {
            Long t = getDistinctObjects(dataset);
            if (t != null) {
                triples += t.longValue();
                i++;
            }
        }
        return (i==0) ? 1 : triples/i;
    }

    private Long getMinObjects(Collection<Resource> datasets) {
        long triples = 0;
        int i = 0;
        for (Resource dataset : datasets) {
            Long t = getMinObjects(dataset);
            if (t != null) {
                triples += t.longValue();
                i++;
            }
        }
        return (i==0) ? 1 : triples/i;
    }

    private Long getMaxObjects(Collection<Resource> datasets) {
        long triples = 0;
        int i = 0;
        for (Resource dataset : datasets) {
            Long t = getMaxObjects(dataset);
            if (t != null) {
                triples += t.longValue();
                i++;
            }
        }
        return (i==0) ? 1 : triples/i;
    }

    private Long getDistinctPredicates(Collection<Resource> datasets) {
        long triples = 0;
        int i = 0;
        for (Resource dataset : datasets) {
            Long t = getDistinctPredicates(dataset);
            if (t != null) {
                triples += t.longValue();
                i++;
            }
        }
        return (i == 0) ? 1 : triples/i;
    }

    private Long getMinPredicates(Collection<Resource> datasets) {
        long triples = 0;
        int i = 0;
        for (Resource dataset : datasets) {
            Long t = getMinPredicates(dataset);
            if (t != null) {
                triples += t.longValue();
                i++;
            }
        }
        return (i == 0) ? 1 : triples/i;
    }

    private Long getMaxPredicates(Collection<Resource> datasets) {
        long triples = 0;
        int i = 0;
        for (Resource dataset : datasets) {
            Long t = getMaxPredicates(dataset);
            if (t != null) {
                triples += t.longValue();
                i++;
            }
        }
        return (i == 0) ? 1 : triples/i;
    }

    private Long getEntities(Collection<Resource> datasets) {
        long triples = 0;
        int i = 0;
        for (Resource dataset : datasets) {
            Long t = getEntities(dataset);
            if (t != null) {
                triples += t.longValue();
                i++;
            }
        }
        return (i == 0) ? 1 : triples/i;
    }

    private boolean isTypeClass(StatementPattern pattern) {
        Value predVal = pattern.getPredicateVar().getValue();
        Value objVal = pattern.getObjectVar().getValue();

        if (predVal != null && objVal != null && predVal.equals(RDF.TYPE))
            return true;
        else
            return false;
    }

    private class StatsItemImpl implements StatsItem {

        private StatementPattern pattern;
        private long patternCount;
        private long distinctSubjects;
        private long distinctPredicates;
        private long distinctObjects;
        private long minSubjects;
        private long minPredicates;
        private long minObjects;
        private long maxSubjects;
        private long maxPredicates;
        private long maxObjects;
        private long currMin;
        private long currMax;


        public StatsItemImpl(StatementPattern pattern,
                             long patternCount,
                             long distinctSubjects,
                             long distinctPredicates,
                             long distinctObjects,
                             long minSubjects,
                             long minPredicates,
                             long minObjects,
                             long maxSubjects,
                             long maxPredicates,
                             long maxObjects)
        {
            this.pattern = pattern;
            this.patternCount = patternCount;
            this.distinctSubjects = distinctSubjects;
            this.distinctPredicates = distinctPredicates;
            this.distinctObjects = distinctObjects;
            this.minSubjects = minSubjects;
            this.minPredicates = minPredicates;
            this.minObjects = minObjects;
            this.maxSubjects = maxSubjects;
            this.maxPredicates = maxPredicates;
            this.maxObjects = maxObjects;

        }

        public StatsItemImpl(StatementPattern pattern){
            this.pattern = pattern;
            this.patternCount = 1;
            this.distinctSubjects = 1;
            this.distinctPredicates = 1;
            this.distinctObjects = 1;
            this.minSubjects = 1;
            this.minPredicates = 1;
            this.minObjects = 1;
            this.maxSubjects = 1;
            this.maxPredicates = 1;
            this.maxObjects = 1;
        }

        @Override
        public long getCardinality() { return patternCount; }

        @Override
        public long getCardinality(int i) {
            System.out.println("PatternCount "+patternCount+" CurrMax "+currMax+
                    " CurrMin "+currMin+" "+Math.log10((double)currMax/(double)patternCount));
            switch (i){
                case 0: return patternCount;
                case 1: double log2 = Math.log((double)currMax/(double)patternCount)/Math.log(2);
                        return patternCount +
                        Math.round((double)currMax*(log2/(double)100));
                case 2: return patternCount +
                        Math.round((double)currMax*(Math.log((double)currMax/(double)patternCount)/(double)100) );
                case 3: return patternCount+
                        Math.round((double)currMax*(Math.log10((double)currMax/(double)patternCount)/(double)100) );
//                case 1: return patternCount +
//                        Math.round( (double)currMax * 0.02 );
//                case 2: return patternCount +
//                        Math.round( (double)currMax * 0.05 );
//                case 3: return patternCount +
//                        Math.round( (double)currMax * 0.1 );
//                case 4: return patternCount +
//                        Math.round( (double)currMax * 0.2 );
//                case 5: return patternCount +
//                        Math.round( (double)currMax * 0.5 );
                default: return patternCount;
            }
        }

        @Override
        public long getVarCardinality(String var) {
            if (pattern.getSubjectVar().getName().equals(var))
                return distinctSubjects;

            if (pattern.getPredicateVar().getName().equals(var))
                return distinctPredicates;

            if (pattern.getObjectVar().getName().equals(var))
                return distinctObjects;

            return 0;
        }


        public void setCurrMin(long currMin) {
            this.currMin = currMin;
        }


        public void setCurrMax(long currMax) {
            this.currMax = currMax;
        }

        public void setPatternCount(long patternCount) {
            this.patternCount = patternCount;
        }

        public long getDistinctSubjects() {
            return distinctSubjects;
        }

        public void setDistinctSubjects(long distinctSubjects) {
            this.distinctSubjects = distinctSubjects;
        }

        public long getDistinctPredicates() {
            return distinctPredicates;
        }

        public void setDistinctPredicates(long distinctPredicates) {
            this.distinctPredicates = distinctPredicates;
        }

        public long getDistinctObjects() {
            return distinctObjects;
        }

        public void setDistinctObjects(long distinctObjects) {
            this.distinctObjects = distinctObjects;
        }

        public long getMinSubjects() {
            return minSubjects;
        }

        public void setMinSubjects(long minSubjects) {
            this.minSubjects = minSubjects;
        }

        public long getMinPredicates() {
            return minPredicates;
        }

        public void setMinPredicates(long minPredicates) {
            this.minPredicates = minPredicates;
        }

        public long getMinObjects() {
            return minObjects;
        }

        public void setMinObjects(long minObjects) {
            this.minObjects = minObjects;
        }

        public long getMaxSubjects() {
            return maxSubjects;
        }

        public void setMaxSubjects(long maxSubjects) {
            this.maxSubjects = maxSubjects;
        }

        public long getMaxPredicates() {
            return maxPredicates;
        }

        public void setMaxPredicates(long maxPredicates) {
            this.maxPredicates = maxPredicates;
        }

        public long getMaxObjects() {
            return maxObjects;
        }

        public void setMaxObjects(long maxObjects) {
            this.maxObjects = maxObjects;
        }

    }
}
