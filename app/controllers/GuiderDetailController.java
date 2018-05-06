package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import play.api.mvc.Session;
import play.libs.Json;
import play.mvc.*;

import util.LocationLoader;
import views.html.*;
import models.*;

import java.util.*;

import play.*;
import play.api.*;
import play.data.*;

public class GuiderDetailController extends Controller
{
    public static Result enterChatRoom(String userId, String anotherId) {

	
	if (userId == null )
	    {
		return ok(views.html.loginPage.t.login.render());
		
	    }
	
	else
	    {
	String url_string_suffix = "conversationId=" + userId +  anotherId + "&userId=" + userId + "&aontherId=" + anotherId;

	String port = Global.ChatServicePort;
	
	return redirect(Global.ChatServicePort + url_string_suffix);
	    }
    }


}
