package controllers;


import play.libs.Json;
import play.mvc.*;

import models.*;
import views.*;

import java.util.*;

import play.data.*;
import play.Configuration;

/**                                                                                                                                                                            
 * This controller contains an action to handle HTTP requests                                                                                                                  
 * to the application's home page.                                                                                                                                             
 */
public class ItineraryController extends Controller {
    public static Result itinerary()
    {
		return ok(views.html.itinerary.render());
    }
    
    public static Result onItinerarySave()
    {
		return null;
    }
    
}
