<%@page import="eu.semagrow.commons.vocabulary.SEMAGROW"%>
<%@page import="org.eclipse.rdf4j.model.vocabulary.DCTERMS"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.HashMap"%>
<%@page import="org.eclipse.rdf4j.model.Value"%>
<%@page import="org.eclipse.rdf4j.model.URI"%>
<%@page import="org.eclipse.rdf4j.model.Resource"%>
<%@page import="org.eclipse.rdf4j.query.BindingSet"%>
<%@page import="org.eclipse.rdf4j.query.impl.MutableTupleQueryResult"%>
<%@page import="org.eclipse.rdf4j.query.TupleQueryResult"%>
<%@page import="eu.semagrow.stack.webapp.controllers.SparqlSamplesController"%>
<%@page import="eu.semagrow.commons.CONSTANTS"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

    <table id="sparqlSamples">
        <thead>
            <tr>
                <th>Title</th>
                <th>Description</th>
                <th>SPARQL Query</th>                
            </tr>
        </thead>
        <tbody>
        <%
            HashMap<Resource,HashMap<URI,HashSet<Value>>> sampleData = (HashMap<Resource,HashMap<URI,HashSet<Value>>>)request.getAttribute(SparqlSamplesController.ATTR_SAMPLES_RESULT);
            for(Resource r : sampleData.keySet()){
                %>
                <tr>
                    <td><%=sampleData.get(r).get(DCTERMS.TITLE)%></td>
                    <td><%=sampleData.get(r).get(DCTERMS.DESCRIPTION)%></td>
                    <% try { %>
                    <td><%=sampleData.get(r)
                            .get(SEMAGROW.SYSTEM.SPARQL_SAMPLES.SPARQL_SAMPLE_TEXT)
                            .iterator().next().stringValue().replaceAll("<","&lt;").replaceAll(">","&gt;")
                    %></td>
                    <% } catch(Exception e){ %>
                    <td><%=sampleData.get(r).get(SEMAGROW.SYSTEM.SPARQL_SAMPLES.SPARQL_SAMPLE_TEXT).iterator().next()%></td>
                    <% }%>
                </tr>
                <%
            }
        %>
        </tbody>
    </table>

            
                      
