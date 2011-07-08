package com.indus;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
	String propFile;
    /** 
     * Default Constructor 
     *  
     */  
    public PropertiesReader(String prop) {  
     this.propFile =  prop;
    }  
  
    /** 
     * Some Method 
     *  
     * @throws IOException 
     *  
     */  
    public Properties loadPropertiesFile() { 
    	Properties properties = new Properties();
        // Get the inputStream  
    	try{
	        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(propFile);  
	        //System.out.println("InputStream is: " + inputStream);  
	        // load the inputStream using the Properties  
	        properties.load(inputStream); 
	        //System.out.println("===================SUCCESS======");
    	}catch (Exception e) {
    		e.printStackTrace();
			// TODO: handle exception
		}
        // get the value of the property  
       // String propValue = properties.getProperty("abc");  
  
       // System.out.println("Property value is: " + propValue); 
        return properties;
    }  
  
}
