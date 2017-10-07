package controllers;

import play.mvc.*;

import views.html.*;
import models.*;

import java.util.*;

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
        if (username != null) {
            return ok(views.html.registerguider.render());
        } else {
            return ok(views.html.registerguider.render());
        }
    }

    public static Result onRegisterGuider() {

        Form<AUser> filledForm = Form.form(AUser.class).bindFromRequest();
        Map<String, String[]> formData = request().body().asFormUrlEncoded();
        if (filledForm.hasErrors()) {
            return badRequest(views.html.registerguider.render());
        } else {
            AUser g = filledForm.get();
            g.type = "GUIDER";

            if (g.type_work.equals(AUser.EMPLOYEE)) {
                g.jobtitle = formData.get("job")[0];
            } else if (g.type_work.equals(AUser.STUDENT)) {
                g.jobtitle = formData.get("major")[0];
            }
            g.create(g);
            return ok(views.html.index.render());
        }
    }

}

