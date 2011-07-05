package com.indus;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.indus.dao.hibernate.Address;
import com.indus.dao.hibernate.Catalog;
import com.indus.dao.hibernate.Country;
import com.lbr.dao.specificdao.DaoUtilities;

public class IndusUtility {
	private static final Logger logger = Logger.getLogger(IndusUtility.class);
	  public static List<String> createAvailableSizeListForCatelog(Catalog catalog){
		  String availSizes = catalog.getSize();
		  availSizes = "S,M,L,XL,XXL";
		  List<String> eventLevelList = IndusUtility.convertUserPrefStringToList(availSizes);
		  return eventLevelList;
		}
	
		 public static void TestReferences(List<Integer> arr){
			 arr.add(new Integer(3));
			 arr = null;
		 }		  
		 public static List<String> convertUserPrefStringToList(String origStrRaw){
			 List<String> arr = new ArrayList<String>();
				 String origStrCleaned = origStrRaw;
				 StringTokenizer strtkn = new StringTokenizer(origStrCleaned);
				 while(strtkn.hasMoreElements()){
					 String str = strtkn.nextToken(",");
					 arr.add(str);
				 }
			return arr;
		 }	
		 
		 public static List<String> parseStringWithDelimiter(String address, String delimiter){
			 List<String> addressComponents = new ArrayList<String>();
			 StringTokenizer strtkn = new StringTokenizer(address, delimiter);
			 while(strtkn.hasMoreElements()){
				 String str = strtkn.nextToken();
				 addressComponents.add(str);
			 }
			 return addressComponents;
		 }
		 
		 public static List<SelectionBean> populateCountryDropdownList(){
		 	List<SelectionBean> countries = new ArrayList<SelectionBean>();
			countries =  new ArrayList<SelectionBean>();
			List<Country> countriesCached = (List<Country>)DaoUtilities.staticCache.get("ALL_COUNTRIES");
			for (Iterator iterator = countriesCached.iterator(); iterator.hasNext();) {
				Country country = (Country) iterator.next();
				countries.add(new SelectionBean(country.getCountryid(), country.getName()));
			}
			return countries;
		 }	 
		 
		 public static String printAddressHTML(Address loc){
			 StringBuffer sb = new StringBuffer();
			 if(loc!=null){
				 sb.append(loc.getName()+"<br/>"+loc.getLine1()+"<br/>"+loc.getLine2()+"<br/>"+loc.getCity()+"<br/>"+loc.getState()+"<br/>"+loc.getZip()+"<br/>"+loc.getCountry().getName());
			 }
			 return sb.toString();
		 }	
		 
		public static String generatePassword(String pass) {
			MessageDigest m  = null;
			try {
				m = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			byte[] data = pass.getBytes();
			m.update(data,0,data.length);
			BigInteger i = new BigInteger(1,m.digest());
			logger.debug("MD5 str: "+String.format("%1$032X", i));
			return String.format("%1$032X", i);
		}	 
    /**
     * Open a specific text/properties file containing 
     * parameters, and populate a corresponding Properties object.
     */
    public  static Properties loadProperties(String propFileName) {
        InputStream input = null;
        Properties properties = new Properties();
        try {
          //input = new FileInputStream( propFileName );
        	input = IndusUtility.class.getClass().getClassLoader().getResourceAsStream(propFileName);          	
            properties.load( input );
        }
        catch ( FileNotFoundException ex ){
            System.err.println( "ERROR: Cannot find Email properties file : "+ propFileName );
            //ex.printStackTrace();
          }           
        catch ( IOException ex ){
          System.err.println( "ERROR: Cannot open and load properties file : "+ propFileName );
          //ex.printStackTrace();
        }
        finally {
          try {
            if ( input != null ) input.close();
          }
          catch ( IOException ex ){
            System.err.println( "Cannot close properties file : "+ propFileName );
          }
        }
        return properties;
      }	
    
	 public static List<String> convertStringToList(String address, String token){
		 List<String> addressComponents = new ArrayList<String>();
		 StringTokenizer strtkn = new StringTokenizer(address, token);
		 while(strtkn.hasMoreElements()){
			 String str = strtkn.nextToken();
			 addressComponents.add(str);
		 }
		 return addressComponents;
	 }  
	 
	 public static boolean isValidEmailAddress(String emailAddress){  
		   String  expression="^[\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";  
		   CharSequence inputStr = emailAddress;  
		   Pattern pattern = Pattern.compile(expression,Pattern.CASE_INSENSITIVE);  
		   Matcher matcher = pattern.matcher(inputStr);  
		   return matcher.matches();  
		  
	 }
	 public static boolean isNumber(String str){
			try{
				Float.isNaN(new Float(str));
			}catch (Exception e) {
				return false;
			}
			return true;
		 }	 
}