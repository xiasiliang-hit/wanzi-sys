import java.util.*;
import java.util.regex.Pattern;

import play.modules.mongodb.jackson.MongoDB;
import net.vz.mongodb.jackson.JacksonDBCollection;
import net.vz.mongodb.jackson.Id;
import net.vz.mongodb.jackson.ObjectId;


public class Flight extends ItiItem
{
    public Flight()
    {}

    public String airline = ""; // 2-letter (IATA) or 3-letter (ICAO) code of the airline.
    public String airline_ID = ""; //Unique OpenFlights identifier for airline (see Airline).
    public String source_airport = ""; //3-letter (IATA) or 4-letter (ICAO) code of the source airport.
    public String source_airport_ID = ""; //Unique OpenFlights identifier for source airport (see Airport)
    public String destination_airport = ""; //3-letter (IATA) or 4-letter (ICAO) code of the destination airport.
    public String destination_airport_ID = ""; //Unique OpenFlights identifier for destination airport (see Airport)
    public String codeshare = "";  //"Y" if this flight is a codeshare (that is, not operated by Airline, but another carrier), empty otherwise.
    public String stops = ""; //Number of stops on this flight ("0" for direct)
    public String equipment = "";  //3-letter codes for plane type(s) generally used on this flight, separated by spaces


}
