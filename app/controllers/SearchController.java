package controllers;

import play.mvc.*;
import play.data.*;
import play.*;
import play.api.libs.*;
//import play.Environment;
import play.libs.*;

import models.*;
import views.html.*;
import java.util.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.core.*;
//import org.codehaus.jackson.JsonFactory;
//import com.typesafe.config.ConfigFactory;
//import com.typesafe.config.Environment;
import java.io.*;
//import org.json.*;
import java.io.IOException;
//import org.codehaus.jackson.JsonNode;


public class SearchController extends Controller {

	public static Result onSearch(String key)
	{ 		
		return ok(      views.html.search.render(key) );
	}
	
	public static Result getSearch()
	{
		//		Environment env = System.getProperty("environment");
		try (InputStream is = Play.application().resourceAsStream("/app/statics/js/getSearch.json")) {
				/*
				JsonFactory factory = new JsonFactory();
				JsonParser jp = factory.createJsonParser(is);
								JsonNode json = jp.readValueAsTree();
				

				ObjectMapper mapper = new ObjectMapper();
				JsonParser jp = new JsonParser(is);
								JsonNode json = mapper.readTree(jp);
								is.close();
								//				final JsonNode json = Json.parse(is);
								*/
				return ok(views.html.getSearch.render());
			}
		catch (IOException e) {
			e.printStackTrace();
			return internalServerError("java getSesarch(): getsearch.json error");

		}
	  }


	public static Result getGuiderDetail(String userId)
	{
		AUser guider = AUser.getUserById(userId);
		return redirect("/index");
		//return ok(views.html.detail.render(guider));
	}

	//	public static Result
	
	
	/*
	public static Result getCountry(String: cid)
	{
		return ok(views.html.getcountry.render(cid));
	}
	*/
}
