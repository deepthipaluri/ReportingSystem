/*
 * To change this template, choose Tools | Templates
  * and open the template in the editor.
   */
   package jena.org.ws;
   
   import javax.jws.WebService;
   import javax.jws.WebMethod;
   import javax.jws.WebParam;
   import javax.ejb.Stateless;
   import com.hp.hpl.jena.ontology.OntModel;
   import com.hp.hpl.jena.ontology.OntModelSpec;
   import com.hp.hpl.jena.rdf.model.ModelFactory;
   import com.hp.hpl.jena.rdf.model.Property;
   import com.hp.hpl.jena.rdf.model.Resource;
   import com.hp.hpl.jena.query.Query;
   import com.hp.hpl.jena.query.QueryExecution;
   import com.hp.hpl.jena.query.QueryExecutionFactory;
   import com.hp.hpl.jena.query.QueryFactory;
   import com.hp.hpl.jena.query.QuerySolution;
   import com.hp.hpl.jena.query.ResultSet;
   import java.io.FileNotFoundException;
   import java.io.FileOutputStream;
   import java.io.PrintWriter;
   
   @WebService(serviceName = "NewWebService")
   public class NewWebService {
       
           private OntModel ontModel;
               private String currentClass;
               
                   @WebMethod(operationName = "getSubclasses")
                       public String getSubclasses(@WebParam(name = "name") String txt) {
                                   if (ontModel == null) {
                                                   ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
                                                               ontModel.read("file:///C://ReportUp.OWL");
                                   }
                                   
                                           currentClass = txt;
                                           
                                                   String queryString = " PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#> "
                                                                   + " PREFIX xmlns:<http://www.owl-ontologies.com/OntologyTeaRepo.owl#>"
                                                                                   + " SELECT ?x" + " WHERE { ?x" + " rdfs:subClassOf" + " xmlns:" + txt + " }";
                                                                                   
                                                                                           Query query = QueryFactory.create(queryString);
                                                                                                   QueryExecution qe = QueryExecutionFactory.create(query, ontModel);
                                                                                                           ResultSet results = qe.execSelect();
                                                                                                           
                                                                                                                   String returnStr = "";
                                                                                                                           while (results.hasNext()) {
                                                                                                                                           if (!returnStr.isEmpty()) {
                                                                                                                                                               returnStr = returnStr + ", ";
                                                                                                                                           }
                                                                                                                                                       QuerySolution soln = results.nextSolution();
                                                                                                                                                                   String xStr = soln.get("x").toString();
                                                                                                                                                                               String[] strArray = xStr.split("#");
                                                                                                                                                                                           returnStr = returnStr + strArray[1];
                                                                                                                           }
                                                                                                                                   qe.close();
                                                                                                                                           return returnStr;
                       }
   }
   
