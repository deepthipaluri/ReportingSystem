/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jena.ws;

import com.hp.hpl.jena.mem.ModelMem;
import com.hp.hpl.jena.ontology.*;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.DC;
import com.hp.hpl.jena.vocabulary.RDF;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import static org.jena.ws.JenaTutorialMain.tutorialURI;


public class NewMain {
    static String tutorialURI = "http://www.owl-ontologies.com/OntologyTeaRepo.owl";
    static String Favourite = "Today I enjoyed playing with Legos";
    static String Eat = "Snack/Lunch";
    static String Nap = "1Hr to 2Hrs";
    static String output_filename = "C://Users/Deepthi/Desktop/ReportOr3.txt";
    public static void main( String[] args ) throws FileNotFoundException, IOException {
        
    
        OntModel m = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM, null );

        // we have a local copy of the wine ontology
         m.getDocumentManager().addAltEntry( "http://www.owl-ontologies.com/OntologyTeaRepo.owl#",
                                            "file:C://Program Files/Protege_3.4.8/ReportOr2.owl" );

        m.read( "http://www.owl-ontologies.com/OntologyTeaRepo.owl#" );

        new ClassHierarchy().showHierarchy( System.out, m );
    

 System.out.println("\n* * * * * * Update Ontology * * * * * * ");
  			Model model = new ModelMem();
		        Resource tutorial = model.createResource(tutorialURI); 	        //add properties to the resource
		        tutorial.addProperty(RDF.type, Favourite);
                       Resource tutorial1 = model.createResource("http://www.owl-ontologies.com/OntologyTeaRepo.owl#Child_1");   
		        tutorial1.addProperty(RDF.type, Eat);
		        tutorial1.addProperty(RDF.type, Nap);
		        model.write(new PrintWriter(System.out));
		        model.write(new PrintWriter(new FileOutputStream(output_filename)));

		        //******Print the added resource and property with ontology****//
	            FileReader  fr1 = new FileReader(output_filename);
	                   BufferedReader br1 = new BufferedReader(fr1);
		      		        String line1 = null;
		        		        FileWriter fw = null;
		        fw = new FileWriter("./Report.xml");  //the OutPut fileName

		        while((line1 = br1.readLine() ) != null){
		             fw.write(line1);
		             fw.write("\r\n");
		        }
		       		        fw.close();

           //***********************************************************************



		      //************* Task: Print out instance for the classes*****************//
		    System.out.println("\n* * * * * * Show instance for all Classes * * * * * * ");
		       String className = "";
		 	   String prefix = m.getNsPrefixURI("");

		 	  OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
		 	  ontModel.read("file:C://Program Files/Protege_3.4.8/ReportOr2.owl"); // read owl file, load the model

		 	  // Show classes in the model
		 	  for (Iterator j = ontModel.listClasses(); j.hasNext();) {
		 		  OntClass c = (OntClass) j.next();

		 		  if (!c.isAnon()) {     // if this class is not a anonymous class
		 			  System.out.println("Class : "+c.getLocalName());

		 			 className = c.getLocalName().toString();
		 			  OntClass oc = ontModel.getOntClass(prefix+className);
		 	      if (!oc.listInstances().hasNext())
		 	    	 System.out.println("-- No Instance");
		 		  for (Iterator k = oc.listInstances(); k.hasNext();) {
			            Individual kc = (Individual) k.next();
			  	        System.out.println("    Instance : "+kc.getLocalName());
			 	  }
		 	    }
		 	  }

	    }

	//***** aim at drawing the tabular format******//
	protected static void Printblank(int len,int width) {

		 for (int i = 0;  i < width-len; i++)
	        {
	        	System.out.print(" ");
	        }

	    }
   




}
  
