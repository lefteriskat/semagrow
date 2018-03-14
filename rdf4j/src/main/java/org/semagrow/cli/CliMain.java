package org.semagrow.cli;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.impl.SimpleIRI;
import org.eclipse.rdf4j.query.*;
import org.eclipse.rdf4j.query.algebra.QueryRoot;
import org.eclipse.rdf4j.query.algebra.TupleExpr;
import org.eclipse.rdf4j.query.impl.EmptyBindingSet;
import org.eclipse.rdf4j.query.parser.ParsedTupleQuery;
import org.eclipse.rdf4j.query.parser.QueryParserUtil;
import org.eclipse.rdf4j.repository.sail.config.RepositoryResolver;
import org.semagrow.estimator.*;
import org.semagrow.plan.Plan;
import org.semagrow.plan.QueryCompiler;
import org.semagrow.plan.SimpleQueryCompiler;
import org.semagrow.repository.SemagrowRepositoryResolver;
import org.eclipse.rdf4j.query.resultio.*;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.RepositoryException;
import org.eclipse.rdf4j.repository.config.*;
import org.semagrow.sail.SemagrowSail;
import org.semagrow.sail.config.SemagrowSailFactory;
import org.semagrow.selector.*;
import org.semagrow.statistics.StatisticsProvider;
import org.semagrow.statistics.VOIDStatisticsProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by angel on 27/11/2015.
 */
public class CliMain {

    private static final Logger logger = LoggerFactory.getLogger(CliMain.class);

    private static RepositoryResolver resolver = new SemagrowRepositoryResolver();

    public static void main(String[] args) {

        // FIXME: argParser for the command-line arguments
        /*
           Usage: runSemagrow -c repository.ttl -q "SELECT..." -o output.json
         */
        String repositoryConfig = args[0];

        String queryString = "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>\n" +
                "SELECT ?category WHERE {" +
                "{<http://dbpedia.org/resource/Tamim_Bashir> skos:subject ?category .}\n" +
                "UNION {<http://dbpedia.org/resource/John_Pennington_Harman> skos:subject ?category .}\n" +
                "}";

        String resultFile = args[1];

        logger.debug("Using configuration from {}", repositoryConfig);
        logger.debug("Writing result to file {}", resultFile);

        Repository repository = null;
        try {
            repository = resolver.getRepository(repositoryConfig);

            repository.initialize();
            RepositoryConnection conn = repository.getConnection();

            ParsedTupleQuery q = QueryParserUtil.parseTupleQuery(QueryLanguage.SPARQL, queryString, "http://dbpedia.org");
            TupleQuery query = (TupleQuery) conn.prepareTupleQuery(QueryLanguage.SPARQL, queryString);

            SemagrowSailFactory sailFactory = new SemagrowSailFactory();
            SemagrowSail semagrowSail = (SemagrowSail) sailFactory.getSail(sailFactory.getConfig());
            Collection<IRI> include = new ArrayList<IRI>();
            Collection<IRI> exclude = new ArrayList<IRI>();
            QueryCompiler compiler = semagrowSail.getCompiler(include,exclude);

            Plan originalPlan = null;
            Plan tempPlan ;
            StringBuilder result = new StringBuilder();
            result.append("CurrEst\tMetric1\tMetric2\tMteric3\tMetric4\tMetric5\n");
            for(int i = 0; i<=5 ; i++) {
                if(i==0){
                    originalPlan = compiler.compile(new QueryRoot(q.getTupleExpr()),query.getDataset(),query.getBindings(),i);
                    result.append(originalPlan.getProperties().getCardinality().toString()+"\t\t");
                }else{
                    tempPlan = compiler.compile(new QueryRoot(q.getTupleExpr()),query.getDataset(),query.getBindings(),i);
                    if(tempPlan.equals(originalPlan)){
                        result.append("True "+tempPlan.getProperties().getCardinality().toString()+"\t");
                    }else{
                        result.append("False "+tempPlan.getProperties().getCardinality().toString()+"\t");
                    }
                }

            }

            System.out.println(result.toString());
            logger.debug("Closing connection");
            conn.close();

            logger.debug("Shutting down repository");
            repository.shutDown();

        } catch (RepositoryConfigException e) {
            e.printStackTrace();
        } catch (RepositoryException e) {
            e.printStackTrace();
        } catch (MalformedQueryException e) {
            e.printStackTrace();
        } catch (QueryEvaluationException e) {
            e.printStackTrace();
        } catch (TupleQueryResultHandlerException e) {
            e.printStackTrace();
        }

    }

    private static TupleQueryResultWriter getWriter(String resultFile) throws FileNotFoundException {

        OutputStream outStream = new FileOutputStream(resultFile);

        QueryResultFormat writerFormat = TupleQueryResultWriterRegistry.getInstance().getFileFormatForFileName(resultFile).get();
        TupleQueryResultWriterFactory writerFactory = TupleQueryResultWriterRegistry.getInstance().get(writerFormat).get();
        return writerFactory.getWriter(outStream);

    }
}
