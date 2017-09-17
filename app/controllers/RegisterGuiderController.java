package controllers;

import play.mvc.*;

import views.html.*;
import models.*;

import java.util.*;
//import javax.persistence.*;
//import play.data.*;
//import play.db.ebean.Ebean.*;
//import io.ebean.*;
//import io.ebean.*;

import play.*;
import play.api.*;
import play.data.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class RegisterGuiderController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    static String homepage = Global.homepage;
    /*
    @Inject
    final EbeanServer ebeanServer;

    public void createCustomer(String name) {

	Customer customer = new Customer();
	customer.setName(name);

	ebeanServer.save(customer);

    }
    */
    public static Result registerGuider() {

		String username = session("username");
		if (username != null)
			{
		return ok( views.html.registerguider.render());
			}
		else
			{
				return ok(views.html.loginPage.t.login.render());
			}

    }

	public static Result onRegisterGuider(){

		return ok (homepage);
	}


	public Result test()
    {
	return ok(registerguider.render());
    }
}

