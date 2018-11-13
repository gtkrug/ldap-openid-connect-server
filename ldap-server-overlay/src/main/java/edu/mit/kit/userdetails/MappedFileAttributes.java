package edu.mit.kit.userdetails;

import java.util.Set;
import java.util.HashMap;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * Reads user attributes/claims from a delimited file.
 * 
 * @author krug
 *
 */

public class MappedFileAttributes {

   private HashMap<String, String> hmap = new HashMap<String, String>();
   private static final Logger log = LoggerFactory.getLogger(MappedFileAttributes.class);

   public MappedFileAttributes (String attrFile) {
   try {
     File inputFile = new File(attrFile);
     BufferedReader in = new BufferedReader(new FileReader(inputFile));
     String strNextLine = "";

     while ( (strNextLine = in.readLine()) != null )
     {
       String[] tokens = strNextLine.split(" :: ");
         
       if (tokens.length != 2)
       {
         log.debug ("Delimiter error when parsing attribute line: " + strNextLine);
       }
       else
       {
          String strAttrName  = tokens[0];
          String strAttrValue = tokens[1];
          log.debug ("Attr  = " + strAttrName + "\nValue = " + strAttrValue + "\n");
//          System.out.println ("Attr  = " + strAttrName + "\nValue = " + strAttrValue + "\n");
          hmap.put(strAttrName,strAttrValue);
       }
     }
    } catch (java.io.FileNotFoundException name) {
      log.error ("Error reading Attribute File: " + attrFile);
    } catch (java.io.IOException e) { 
      log.error ("Error reading Attribute File: " + attrFile);
    }
  }

  public String get (String attr) {
     if (hmap.containsKey (attr)) {
        return hmap.get(attr);
     } else {
        return null;
     }
  }

}
