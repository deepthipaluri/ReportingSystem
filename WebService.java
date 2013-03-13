/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jena.ws;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import java.util.StringTokenizer;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

/**
 *
 * @author Deepthi
 */
@WebService(serviceName = "Sample")

public class Sample {

    


       @WebMethod(operationName = "GetValuebySPARQL")
    public String GetSubClassInfo(@WebParam(name = "SCName") // Get the type of input individual
    String ClassName) {

     
        //*******************************
  // Create an ontology model, query directly on the owl file
 OntModel m = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);


   m.read("file:///C://Program Files/Protege_3.4.8/ReportOr2.owl"); //point to your ontology directory

  


  //******************* Use individual and property to do the SPARQL **************************//
   String queryString = " PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#> "
          + " PREFIX xmlns:<http://www.owl-ontologies.com/OntologyTeaRepo.owl#>"
   + " SELECT ?y " + " WHERE { " + "xmlns:"+ ClassName + " " + "xmlns:Teaches ?y" +" }"; //import the input key word

  
  // create a query
  Query query = QueryFactory.create(queryString);
  // execute the query, get the result
  QueryExecution qe = QueryExecutionFactory.create(query, m);
  ResultSet results = qe.execSelect();
   System.out.println("The result is " + Boolean.toString(results.hasNext()));
  
 ClassName = "";
 

  while (results.hasNext())
{
        QuerySolution soln = results.nextSolution();
        String str = soln.get("x").toString();
        StringTokenizer token = new StringTokenizer(str,"#");
        str = token.nextToken();
        str = token.nextToken();
        //System.out.println(token.nextToken());
         System.out.println(str);

         
             ClassName = ClassName + str;
   }
           
  qe.close();
      

        return ClassName;
    }


}

