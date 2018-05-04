package models;


import java.util.*;
import java.util.regex.Pattern;
import java.io.*;

import play.modules.mongodb.jackson.MongoDB;
import net.vz.mongodb.jackson.JacksonDBCollection;
import net.vz.mongodb.jackson.Id;
import net.vz.mongodb.jackson.ObjectId;
import net.vz.mongodb.jackson.DBQuery;
import play.Logger;


public class Airport {

    public Airport(){
    }

    public Airport(String[] fields){

	String index = fields[0]; //no use
	name = fields[1];
	city = fields[2];
	country = fields[3];
	short_name = fields[4];
	unknown1 = fields[5]; // "ZSPD"                                                                                       
	latitude  = fields[6];
	longitude =fields[7];
	unknown2 = fields[8]; // "U"                                                                                           
	continent_city = fields[9];
	airport = fields[10];
	our_airport = fields[11];
    }
    public String name;
    public String city;
    public String country;
    public String short_name;
    public String unknown1; // "ZSPD"
    public String latitude;
    public String longitude ;
    public String unknown2; // "U"
    public String continent_city;
    public String airport;
    public String our_airport;

    private static JacksonDBCollection<Airport, String> coll = MongoDB.getCollection("airport", Airport.class, String.class);

    //invoke once    
    public static Boolean writeToDB()
    {
	Boolean flag =true;
	ArrayList airports = new ArrayList();
	BufferedReader br = null; 
	try {
	    br = new BufferedReader(new FileReader("/root/airports-extended.dat"));
	    String line = br.readLine();

	    while (line != null) {
		
		StringBuilder sb = new StringBuilder(line);

		String line_string = sb.toString();
		//				Logger.info(line_string);
		String[] fields = line.split(",");
		Airport air = new Airport(fields);
		
		airports.add(air);
		//		sb.append(line);
		//sb.append(System.lineSeparator());
		line = br.readLine();
	    }
	    //String everything = sb.toString();
	    
	    coll.insert(airports);
	    br.close();
	}
	catch (Exception e)
	{
	    Logger.info(e.toString());
	     flag = false;
	} 
	finally {
	}

	return flag;
    }
    public static Airport getAirportByShortName(String sName)
    {
	Airport a;
	a = coll.findOne(DBQuery.is("short_name",sName ));
	return a;
    }

}
