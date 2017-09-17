package controllers;

import play.mvc.*;
import play.data.*;

import models.*;
//import views.t.*;

public class Application extends Controller {
	static String staticpage = "http://static.zouzouyouyou.info/";
	static String homepage = "http://www.zouzouyouyou.info/";

	public static Result externalstatic(String path)
	{
		return redirect(staticpage + path);
	}




	static Form<Task> taskForm = Form.form(Task.class); 



	
  public static Result index() {
    return redirect(routes.Application.tasks());
  }
  
  public static Result tasks() {
	  return ok(
			        views.html.index.render(Task.all(), taskForm)
    );
  }
  
  public static Result newTask() {
    Form<Task> filledForm = taskForm.bindFromRequest();
      if(filledForm.hasErrors()) {
        return badRequest(
          views.html.index.render(Task.all(), filledForm)
        );
      } else {
        Task.create(filledForm.get());
        return redirect(routes.Application.tasks());  
      }
  }
  
  public static Result deleteTask(String id) {
    Task.delete(id);
    return redirect(routes.Application.tasks());
  }
  
}
