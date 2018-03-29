package controllers;

import models.AUser;
import models.DemandOrder;
import models.GroupOrder;
import models.Order;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import service.MailService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

public class AgencyController extends Controller {
	public static Result travelAgency() {
	    return ok(views.html.travelagency.render());
	}

        public static Result hotel() {
	    return ok(views.html.hotel.render());
        }

    

	/* copy from orderController, have not implemented */
	public static Result onAgencyQuery() {
		
	MailService mailService = new MailService();
	Map<String, String[]> formData = request().body().asFormUrlEncoded();
	GroupOrder order = new GroupOrder();
	order.setContent(formData.get("content")[0]);
	order.setFirmName(formData.get("firm_name")[0]);
	order.setPhone(formData.get("phone")[0]);
	order.setUserName(formData.get("user_name")[0]);
	order.setUserEmail(formData.get("user_email")[0]);
	String title = "团游定制_" + order.getFirmName() + "_" + order.getUserName();
	StringBuilder bodyBuilder = new StringBuilder();
	bodyBuilder.append("<html><body><p>企业名称：");
	bodyBuilder.append(order.getFirmName());
	bodyBuilder.append("</p><p>联系人：");
	bodyBuilder.append(order.getUserName());
	bodyBuilder.append("</p><p>联系电话：");
	bodyBuilder.append(order.getPhone());
	bodyBuilder.append("</p><p>邮箱：");
	bodyBuilder.append(order.getUserEmail());
	bodyBuilder.append("</p><p>需求内容：");
	bodyBuilder.append(order.getContent());
	bodyBuilder.append("</p></body><html>");
	mailService.sendGroupDemandMail(title, bodyBuilder.toString());
	Map<String, Object> result = new HashMap<>();
	result.put("code", 1000);
	return ok(Json.toJson(result));
	}
}
