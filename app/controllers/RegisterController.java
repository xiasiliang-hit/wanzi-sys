package controllers;

import play.mvc.*;
import play.twirl.api.*;
/*import static play.api.data.Form.*;
import static play.data.Form.form;
*/
import models.*;
import views.*;

import play.*;
import play.api.*;
//import play.api.data.*;
import play.api.data.Forms.*;
import play.data.*;

public class RegisterController extends Controller {
	
	static String homepage = Global.homepage;
	private AUser alluser  = new AUser();
	/*
    public static Result index() {
	return redirect(routes.Application.tasks());
    }
	*/

	public static Result register()
	{    

	    Form<AUser> userForm = Form.form(AUser.class);
		return ok(		views.html.loginPage.t.register.render(userForm) );
	}
	
    public static Result onRegister() {
		
		Form<AUser> filledForm = Form.form(AUser.class).bindFromRequest();

		if(filledForm.hasErrors()) {
			return badRequest(views.html.loginPage.t.register.render(filledForm)); 
		}
		else{

			AUser u = filledForm.get();
			createUser(u);

			return 	redirect(homepage);
		}
    }

	public static Result login()
	{

		return ok(views.html.loginPage.t.login.render());
	}

	public static Result onLogin()
	{
		Form<AUser> filledForm = Form.form(AUser.class).bindFromRequest();
		String em = filledForm.get().email;
		String pass = filledForm.get().password;
		//play.Logger.info(em);
		//play.Logger.info(pass);
		
		AUser u = AUser.verifyUser(em, pass) ;
				//		play.Logger.info(request().getQueryString("emmail"));
		if (em!=null && pass !=null && u != null)
			{
				session("username", u.name);
				session("userId",u.id);
				session("userType",u.type);
				return ok(views.html.index.render());
			}
		else
			return ok (views.html.loginPage.t.login.render());
	}

	public static Result onLogout(){
		session().remove("username");
		session().remove("userId");
		session().remove("userType");
		return ok(views.html.loginPage.t.login.render());
	}
	/*
	public static Result onStep1 (){
		return ok(views.html.loginPage.t.2.render());
	}
	
	public static Result onStep2 (){
		redirect(homepage);
	}
	*/
    public static void createUser(AUser u) {
		/*	Form<AUser> filledForm = userForm.bindFromRequest();

		if(filledForm.hasErrors()) {
	    return badRequest(
			      views.html.onregister.render()
			      );
				  } else
		*/
		{
			AUser.create(u);
			//		    redirect(homepage);
		}
    }

}

