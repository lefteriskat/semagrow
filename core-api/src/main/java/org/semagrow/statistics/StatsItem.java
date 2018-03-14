package org.semagrow.statistics;

/**
 * Created by angel on 7/5/2015.
 */
public interface StatsItem {

    long getCardinality();

    long getCardinality(int i);

    long getVarCardinality(String var);

}
